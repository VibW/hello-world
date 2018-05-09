package gov.oman.edrms.modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;
import com.auxilii.msgparser.attachment.Attachment;
import com.auxilii.msgparser.attachment.FileAttachment;
import com.filenet.api.core.Document;
import com.ibm.jarm.api.constants.RMRefreshMode;
import com.ibm.jarm.api.core.Record;

public class FetchData {

	private static void  fetchEmailData() {
		MsgParser msgp = new MsgParser();
		Message msg = null;
		InputStream stream=null;
		try {
			stream = new FileInputStream(new File("D:\\NRAA MOCK REPORT.msg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

		/*
		 * record.getProperties().putStringValue("Subject", subject);
		 * record.getProperties().putStringValue("EmailBody", body);
		 * record.save(RMRefreshMode.Refresh);
		 */
		System.out.println("--------->From Name"+fromName);
		System.out.println("From :" + fromName + " <" + fromEmail + ">");
		System.out.println("Subject :" + subject);
		System.out.println("");
		
		System.out.println(body);
		System.out.println("");
		FileOutputStream stream1 = null;
		try {
			stream1 = new FileOutputStream("D://temp.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Attachment> atts = msg.getAttachments();
		for (Attachment att : atts) {
			if (att instanceof FileAttachment) {
				FileAttachment file = (FileAttachment) att;
				byte data[] = file.getData();
				System.out.println("Attachment : " + file.getLongFilename());
				System.out.println("Mime Type---------->"+file.getExtension());
				 /*final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
				 System.out.println("------------FMT"+fileTypeMap.getContentType(data.toString()));*/
				// you get the actual attachment with
				/*
				try {
					stream1.write(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
			}

		}
	}
	public static void main(String[] args) {
		fetchEmailData();
	}
}
