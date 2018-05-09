package gov.oman.edrms.modules;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;
import com.auxilii.msgparser.attachment.Attachment;
import com.auxilii.msgparser.attachment.FileAttachment;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.engine.EventActionHandler;
import com.filenet.api.events.ObjectChangeEvent;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.util.Id;
import com.ibm.jarm.api.constants.DomainType;
import com.ibm.jarm.api.constants.RMClassName;
import com.ibm.jarm.api.constants.RMPropertyName;
import com.ibm.jarm.api.constants.RMRefreshMode;
import com.ibm.jarm.api.core.ContentItem;
import com.ibm.jarm.api.core.RMFactory;
import com.ibm.jarm.api.core.RMFactory.RMPermission;
import com.ibm.jarm.api.core.RMLink;
import com.ibm.jarm.api.core.Record;
import com.ibm.jarm.api.core.RecordContainer;
import com.ibm.jarm.api.property.RMProperties;
import com.ibm.jarm.api.util.P8CE_Convert;

import gov.oman.edrms.util.ConnectionProvider;

public class EmailDeclareModule implements EventActionHandler {

	ConnectionProvider connection = new ConnectionProvider();
	/** The Constant ID. */
	private static final String ID = "Id";

	@Override
	public void onEvent(ObjectChangeEvent event, Id arg1) throws EngineRuntimeException {
		String docID = null;
		System.out.println("Event received Email Record ");

		System.out.println("event source object id  Email Record " + event.get_SourceObjectId());

		final com.ibm.jarm.api.core.FilePlanRepository repository = connection.getFilePlanRepositiory();
		String recordId = event.get_SourceObjectId().toString();
		Record record = RMFactory.Record.fetchInstance(repository, recordId, null);
		// renameEmailField(record);
		/*
		 * Document document =
		 * Factory.Document.fetchInstance(event.getObjectStore(),
		 * event.get_SourceObjectId(), null); if
		 * (document.getClassName().equalsIgnoreCase("Email")) {
		 * declareEmailRecord(document); }
		 */
		for (ContentItem contentItem : record.getAssociatedContentItems()) {
			docID = contentItem.getProperties().getGuidValue(ID);
		}
		Document document = Factory.Document.fetchInstance(event.getObjectStore(), docID, null);
		if (document.getClassName().equalsIgnoreCase("Email")) {
			fetchEmailData(document, record);
		}
	}

	private void declareEmailRecord(Document document, Record parentRecord) {
		String recParentId = parentRecord.getSecurityFolder().getProperties().getGuidValue(ID);
		RecordContainer primaryContainer = RMFactory.RecordFolder.fetchInstance(connection.getFilePlanRepositiory(),
				recParentId, null);
		ContentItem targetDocument = P8CE_Convert.fromP8CE(document);
		String classIdent = RMClassName.EmailRecord;
		// ElectronicRecordInfo
		// Define properties for the new record.
		RMProperties jarmProps = RMFactory.RMProperties.createInstance(DomainType.P8_CE);
		jarmProps.putStringValue(RMPropertyName.DocumentTitle, document.get_Name());
		/*
		 * jarmProps.putStringValue("SecurityCategory", parentSecurity);
		 * jarmProps.putStringValue("RecordID", "1");
		 */
		// No additional permission specified.
		List<com.ibm.jarm.api.security.RMPermission> jarmPerms = null;
		// No additional record filings needed.
		List<RecordContainer> additionalContainers = null;
		// Collection of content to declare for.
		List<ContentItem> targetDocuments = new ArrayList<ContentItem>(1);
		targetDocuments.add(targetDocument);
		// Ready to perform new email record declaration.
		Record newRecord = primaryContainer.declare(classIdent, jarmProps, jarmPerms, additionalContainers,
				targetDocuments);
		linkRecords(parentRecord, newRecord);

	}

	private void linkRecords(Record headRecord, Record tailRecord) {
		String linkClass = RMClassName.RecordSeeAlsoLink;
		RMLink newLink = RMFactory.RMLink.createInstance(connection.getFilePlanRepositiory(), linkClass);
		// Define the required head and tail property values and
		// any other property values that might be desired.
		RMProperties linkProps = newLink.getProperties();
		linkProps.putObjectValue(RMPropertyName.Head, headRecord);
		linkProps.putObjectValue(RMPropertyName.Tail, tailRecord);
		linkProps.putStringValue(RMPropertyName.LinkName, "My Link Name");
		// Finally persist the new link instance to the repository.
		newLink.save(RMRefreshMode.Refresh);
		System.out.printf("Created new RMLink instance: %s%n", newLink.getObjectIdentity());
	}

	private void fetchEmailData(Document document, Record parentRecord) {
		MsgParser msgp = new MsgParser();
		Message msg = null;
		InputStream stream = document.accessContentStream(0);
		try {
			msg = msgp.parseMsg(stream);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fromEmail = msg.getFromEmail();
		String fromName = msg.getFromName();
		String subject = msg.getSubject();
		String body = msg.getBodyText();

		parentRecord.getProperties().putStringValue("Subject", subject);
		parentRecord.getProperties().putStringValue("EmailBody", body);
		parentRecord.save(RMRefreshMode.Refresh);
		System.out.println("From :" + fromName + " <" + fromEmail + ">");
		System.out.println("Subject :" + subject);
		System.out.println("");

		System.out.println(body);
		System.out.println("");

		List<Attachment> atts = msg.getAttachments();
		for (Attachment att : atts) {
			if (att instanceof FileAttachment) {
				FileAttachment file = (FileAttachment) att;

				System.out.println("Attachment : " + file.getFilename());
				// you get the actual attachment with
				byte data[] = file.getData();
				InputStream docStream = new ByteArrayInputStream(data);
				final com.filenet.api.core.Folder folder = Factory.Folder.fetchInstance(connection.getCEObjectStore(),
						"/NRAA Documents", null);// Mississauga//NRAA
				// Documents
				final Document doc1 = Factory.Document.createInstance(connection.getCEObjectStore(), null);
				final ContentElementList cel = Factory.ContentElement.createList();
				final ContentTransfer cnt = Factory.ContentTransfer.createInstance();
				cnt.set_RetrievalName(file.getLongFilename());
				if (file.getExtension().equals(".pdf")) {
					cnt.set_ContentType("application/pdf");
					doc1.set_MimeType("application/pdf");
				}
				cnt.setCaptureSource(docStream);
				cel.add(cnt);

				doc1.set_ContentElements(cel);
				doc1.checkin(AutoClassify.AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
				final com.filenet.api.property.Properties prop = doc1.getProperties();// DocumentTitle
				prop.putValue("DocumentTitle", file.getLongFilename());
				
				
				doc1.save(RefreshMode.REFRESH);

				final ReferentialContainmentRelationship rc = folder.file(doc1, AutoUniqueName.AUTO_UNIQUE,
						file.getLongFilename(), DefineSecurityParentage.DEFINE_SECURITY_PARENTAGE);

				String docId = String.valueOf(doc1.get_Id());
				rc.save(RefreshMode.REFRESH);
				declareEmailRecord(doc1, parentRecord);

			}

		}
	}
}
