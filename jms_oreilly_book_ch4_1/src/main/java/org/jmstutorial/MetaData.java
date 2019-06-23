package org.jmstutorial;

import java.util.Enumeration;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
to execute on CL:
cd C:\Users\J\Documents\Development\intellij\jms\jms_oreilly_book_ch4_1
java -cp target\classes;C:\Users\J\.m2\repository\javax\jms\javax.jms-api\2.0.1\javax.jms-api-2.0.1.jar;C:\Users\J\.m2\repository\org\apache\activemq\activemq-all\5.15.9\activemq-all-5.15.9.jar org.jmstutorial.MetaData
 */
public class MetaData {

    public static void main(String[] args) {    	
    	try {
			// Connect to the provider and get the JMS connection
			Context ctx = new InitialContext();
			QueueConnectionFactory qFactory = (QueueConnectionFactory)
				ctx.lookup("QueueCF");
			QueueConnection qConnect = qFactory.createQueueConnection();
			ConnectionMetaData metadata = qConnect.getMetaData();
			System.out.println("JMS Version:  " + metadata.getJMSMajorVersion() + "." + metadata.getJMSMinorVersion());
			System.out.println("JMS Provider: " + metadata.getJMSProviderName());
			System.out.println("JMS Provider Version: " + metadata.getProviderMajorVersion());
			System.out.println("JMSX Properties Supported: ");
			Enumeration e = metadata.getJMSXPropertyNames();
			while (e.hasMoreElements()) {
				System.out.println("   " + e.nextElement());
			}
			
		} catch (JMSException jmse) {
			jmse.printStackTrace( ); 
			System.exit(1);
		} catch (NamingException jne) {
		    jne.printStackTrace( ); 
		    System.exit(1);
		}
    }
	
}
