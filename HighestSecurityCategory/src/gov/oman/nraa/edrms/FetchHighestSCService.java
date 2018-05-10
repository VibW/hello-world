package gov.oman.nraa.edrms;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.jarm.api.collection.PageableSet;
import com.ibm.jarm.api.constants.EntityType;
import com.ibm.jarm.api.core.Container;
import com.ibm.jarm.api.core.FilePlanRepository;
import com.ibm.jarm.api.core.RMFactory;
import com.ibm.jarm.api.core.Record;
import com.ibm.jarm.api.core.RecordContainer;
import com.ibm.jarm.api.property.RMPropertyFilter;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * Provides an abstract class that is extended to create a class implementing
 * each service provided by the plug-in. Services are actions, similar to
 * servlets or Struts actions, that perform operations on the IBM Content
 * Navigator server. A service can access content server application programming
 * interfaces (APIs) and Java EE APIs.
 * <p>
 * Services are invoked from the JavaScript functions that are defined for the
 * plug-in by using the <code>ecm.model.Request.invokePluginService</code>
 * function.
 * </p>
 * Follow best practices for servlets when implementing an IBM Content Navigator
 * plug-in service. In particular, always assume multi-threaded use and do not
 * keep unshared information in instance variables.
 */
public class FetchHighestSCService extends PluginService {
	private static final String SPACE = "\t";
	private static final String SECURITY_CATEGORY = "SecurityCategory";
	private static final String BOLD_OPEN = "<b>";
	private static final String BOLD_CLOSE = "</b>";
	private static int TOP_SECRET = 4;
	private static int RESTRICTED = 3;
	private static int CONFIDENTIAL = 2;
	private static int UNCLASSIFIED = 1;
	private ConnectionProvider connection = new ConnectionProvider();
	private Map<String, Integer> categoryMap = new LinkedHashMap<String, Integer>();

	/**
	 * Returns the unique identifier for this service.
	 * <p>
	 * <strong>Important:</strong> This identifier is used in URLs so it must
	 * contain only alphanumeric characters.
	 * </p>
	 * 
	 * @return A <code>String</code> that is used to identify the service.
	 */
	public String getId() {
		return "FetchHighestSCService";
	}

	/**
	 * Returns the name of the IBM Content Navigator service that this service
	 * overrides. If this service does not override an IBM Content Navigator
	 * service, this method returns <code>null</code>.
	 * 
	 * @returns The name of the service.
	 */
	public String getOverriddenService() {
		return null;
	}

	/**
	 * Performs the action of this service.
	 * 
	 * @param callbacks
	 *            An instance of the <code>PluginServiceCallbacks</code> class
	 *            that contains several functions that can be used by the
	 *            service. These functions provide access to the plug-in
	 *            configuration and content server APIs.
	 * @param request
	 *            The <code>HttpServletRequest</code> object that provides the
	 *            request. The service can access the invocation parameters from
	 *            the request.
	 * @param response
	 *            The <code>HttpServletResponse</code> object that is generated
	 *            by the service. The service can get the output stream and
	 *            write the response. The response must be in JSON format.
	 * @throws Exception
	 *             For exceptions that occur when the service is running. If the
	 *             logging level is high enough to log errors, information about
	 *             the exception is logged by IBM Content Navigator.
	 */
	public void execute(PluginServiceCallbacks callbacks, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final JSONObject jsonObject = new JSONObject();
		final PrintWriter writer = response.getWriter();
		final String id = request.getParameter("aggregationId");
		final String rmType = request.getParameter("rmType");
		Integer entityType = Integer.valueOf(rmType);
		try {
			JSONArray scArray = checkEntity(entityType);
			response.addHeader("Cache-Control", "no-cache");
			response.setContentType("applications/json");
			response.setCharacterEncoding("UTF-8");
			jsonObject.put("scArrayObject", scArray);
			writer.write(jsonObject.toString());
		} catch (Exception e) {
			System.out.println("Some error is there while fetch SC");
			e.printStackTrace();
		}

	}

	private void navigateDownForCheckingRecords(final FilePlanRepository fpRepos, final Container currentParent) {
		System.out.println(
				"---------------------------Navigate Down For Checking Records Method Starts-------------------------");

		final PageableSet recResultSet = ((RecordContainer) currentParent).getRecords(null);
		System.out.println("------------------Check record set--------------" + recResultSet.isEmpty());
		if (!recResultSet.isEmpty()) {
			final Iterator recordItr = recResultSet.iterator();

			while (recordItr.hasNext()) {
				final Record rec = (Record) recordItr.next();
				if (rec.getProperties().getStringValue(SECURITY_CATEGORY).equalsIgnoreCase("Top Secret")) {
					categoryMap.put(rec.getName(), TOP_SECRET);
				} else if (rec.getProperties().getStringValue(SECURITY_CATEGORY).equalsIgnoreCase("Confidential")) {
					categoryMap.put(rec.getName(), CONFIDENTIAL);
				} else if (rec.getProperties().getStringValue(SECURITY_CATEGORY).equalsIgnoreCase("Restricted")) {
					categoryMap.put(rec.getName(), RESTRICTED);
				} else if (rec.getProperties().getStringValue(SECURITY_CATEGORY).equalsIgnoreCase("Unclassified")) {
					categoryMap.put(rec.getName(), UNCLASSIFIED);
				}
			}

		}

		final PageableSet subContainers = currentParent.getImmediateSubContainers(null);

		final Iterator iter = subContainers.iterator();
		while (iter.hasNext()) {
			final Container firstChildContainer = (Container) iter.next();

			if ((EntityType.Record.getIntValue() != firstChildContainer.getEntityType().getIntValue())) {
				navigateDownForCheckingRecords(fpRepos, firstChildContainer);
			}
		}

		System.out.println(
				"---------------------------Navigate Down For Checking Records Method End-------------------------");
	}

	private JSONArray formMessage() {
		JSONArray array = new JSONArray();

		int maxValueInMap = (Collections.max(categoryMap.values()));
		for (Entry<String, Integer> entry : categoryMap.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				System.out.println(entry.getKey() + SPACE + entry.getValue());
				StringBuffer message = new StringBuffer(entry.getKey());
				message.append(BOLD_CLOSE).append(SPACE).append(entry.getValue());
				array.add(message.toString());
			}
		}
		return array;
	}

	private JSONArray checkEntity(Integer entityType) {

		Integer RMType = entityType;
		EntityType containerType = EntityType.getInstanceFromInt(entityType);
		String containerIdent = null;
		final RMPropertyFilter filter = null;

		if (RMType.equals(EntityType.RecordCategory.getIntValue())) {

			final Container rm = RMFactory.Container.fetchInstance(connection.getFilePlanRepositiory(), containerType,
					containerIdent, filter);
			navigateDownForCheckingRecords(connection.getFilePlanRepositiory(), rm);

		} else if (RMType.equals(EntityType.RecordFolder.getIntValue())
				|| RMType.equals(EntityType.ElectronicRecordFolder.getIntValue())
				|| RMType.equals(EntityType.HybridRecordFolder.getIntValue())
				|| RMType.equals(EntityType.PhysicalContainer.getIntValue())
				|| RMType.equals(EntityType.PhysicalRecordFolder.getIntValue())
				|| RMType.equals(EntityType.RecordVolume.getIntValue())) {

			final Container rm = RMFactory.Container.fetchInstance(connection.getFilePlanRepositiory(), containerType,
					containerIdent, filter);
			navigateDownForCheckingRecords(connection.getFilePlanRepositiory(), rm);

		}
		return formMessage();

	}
}
