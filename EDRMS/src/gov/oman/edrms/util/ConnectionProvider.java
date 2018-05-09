package gov.oman.edrms.util;

import javax.security.auth.Subject;

import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Factory.Connection;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;
import com.ibm.jarm.api.core.RMFactory;
import com.ibm.jarm.api.core.RMFactory.DomainConnection;
import com.ibm.jarm.api.core.RMFactory.FilePlanRepository;
import com.ibm.jarm.api.property.RMPropertyFilter;
import com.ibm.jarm.api.util.P8CE_Convert;

public class ConnectionProvider {

	private static final String USER_NAME = "p8admin";
	private static final String PASSWORD = "filenet@123";
	private static final String OBJSTORE = "FPOS";
	private static final String URL = "http://10.0.10.149:9083/wsi/FNCEWS40MTOM";
	private static final String STANZA = "FileNetP8WSI";

	public com.filenet.api.core.Connection getCEConnection() {

		final com.filenet.api.core.Connection conn = Factory.Connection.getConnection(URL);
		final Subject subject = UserContext.createSubject(conn, USER_NAME, PASSWORD, STANZA);
		UserContext.get().pushSubject(subject);

		return conn;
	}

	public Domain getCEDomain() {

		return Factory.Domain.fetchInstance(getCEConnection(), null, null);
	}
	
	public ObjectStore getCEObjectStore() {
		

		return Factory.ObjectStore.fetchInstance(getCEDomain(), OBJSTORE, null);
	}

	public com.ibm.jarm.api.core.DomainConnection getJarmConnection() {

		return P8CE_Convert.fromP8CE(getCEConnection());
	}

	public com.ibm.jarm.api.core.RMDomain getRMDomain() {
		final com.ibm.jarm.api.core.DomainConnection conn = getJarmConnection();
		final String domainIdent = null; // For P8, null indicates 'local'
											// domain.
		final RMPropertyFilter filter = RMPropertyFilter.MinimumPropertySet;
		final com.ibm.jarm.api.core.RMDomain jarmDomain = RMFactory.RMDomain.fetchInstance(conn, domainIdent, filter);
		jarmDomain.fetchCurrentUser();

		return jarmDomain;
	}

	public com.ibm.jarm.api.core.FilePlanRepository getFilePlanRepositiory() {

		return RMFactory.FilePlanRepository.fetchInstance(getRMDomain(), OBJSTORE, null);
	}
}
