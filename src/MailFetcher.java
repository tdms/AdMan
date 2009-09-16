
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;
import javax.activation.*;

class ShowMail extends JFrame{
	
	private JTextArea mailBody;
    private JScrollPane scroll1;
    private JButton readButton;
    private Box box;
    private ListenButtonHandler listenButtonHandler;
    private String mailActualcontent;
    
    ShowMail(String mailContent)
    {
    
    	super("New Mail");
    	box = Box.createHorizontalBox();
    	
    	       
    	mailBody=new JTextArea(mailContent,25,100);
    	mailBody.setLineWrap(true);
    	mailBody.setWrapStyleWord(true);
    	
    	scroll1 = new JScrollPane(mailBody);
    	box.add(scroll1);
    	
    	readButton=new JButton("Listen");
    	box.add(readButton);
    	
    	listenButtonHandler=new ListenButtonHandler();
    	readButton.addActionListener(listenButtonHandler);
    	
    	add(box);
    	mailActualcontent = mailContent;
    }
    
    private class ListenButtonHandler implements ActionListener{
    	
		public void actionPerformed(ActionEvent event)
		{
			T2SConverter t2s = new T2SConverter();
			t2s.speak(mailActualcontent);
		}
    }
}

public class MailFetcher{
    private String protocol = "imaps";
    static String host = "imap.gmail.com";
    static String user = null;
    static String password = null;
    private String advisorEmail[];
    private int advisorEmailLength;
    
    private int index;
    
    static String mbox = null;
    static String url = null;
    static int port = -1;
    static boolean showStructure = false;
    static boolean saveAttachments = false;    
    static int attnum = 1;
    static int newMessagesCount = 10;

    //info for a particular mail

    static String mailFrom = null;
    static String mailSubject = null;
    static String mailSendDate = null;
    static String mailFlags = null;
    static String mailContent = null;
    
    private Folder folder = null;
    private Store store = null;
   	int msgnum = -1;
   	private String alreadyNotifiedList = "a";

    private Scanner input;
    private ShowMail showMail;
    private String totalMsg;
    
    MailFetcher(String userID, String password1)
    {    
       	System.out.println("In mailfetcher"+userID+password1);
    	
    	user=userID;
    	password=password1;
    	
    	
		init();
    }
    
    void init()
    {
       	
        totalMsg=new String();
        
    	InputStream msgStream = System.in;
    	
	    // Get a Properties object
	    Properties props = System.getProperties();

	    // Get a Session object
	    Session session = Session.getInstance(props, null);
	    try{
		    try{
		    	
			    store = session.getStore(protocol);
			    store.connect(host, port, user, password);
			    
			    folder = store.getDefaultFolder();
			    		    
			    if (folder == null) {
					System.out.println("No default folder");
					System.exit(1);
			    }
		    }
		    catch(Exception e)
		    {
		    	System.out.println("Caught Exception.");
		    }
		    
		    
		    if (mbox == null)
				mbox = "INBOX";
		    
	        folder = folder.getFolder(mbox);
			folder.open(Folder.READ_ONLY);

		    if (folder == null)
		    {
		    	System.out.println("Invalid folder");
		    	System.exit(1);
		    }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    //checkMail();
    }
	void checkMail()
	{
		advisorEmail=new String[100];
		index=0;
		
    	try{
			input=new Scanner(new File("enable.txt"));
			
			while(input.hasNext())
			{
				advisorEmail[index]=input.next();
				index++;
			}
		}
		catch(Exception e)
		{
			
		}
		advisorEmailLength=index;
		
		
		try{
		   int totalMessages = 0;
		    
		    try {
				//folder.open(Folder.READ_WRITE);
				//folder.open(Folder.READ_ONLY);
				totalMessages = folder.getMessageCount();
				System.out.println("Total messages: " + totalMessages);
		
		    } 
		    catch (MessagingException ex) {
		    		System.out.println("Folder openning problem.");
			 }
		    
		    
		    if (totalMessages == 0) {
				System.out.println("Empty folder");
				folder.close(false);
				store.close();
				System.exit(1);
			}

	        //fetching new mails
		    if (msgnum == -1) {
				
			Message[] msgs = folder.getMessages();
	
			// Use a suitable FetchProfile
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.FLAGS);
			fp.add("X-Mailer");
			folder.fetch(msgs, fp);
			
			int startingIndex = msgs.length - newMessagesCount;
			if(startingIndex < 0)
				startingIndex = 0;
	
			//actual message printing
			for (int i = startingIndex; i < msgs.length; i++) {
			    System.out.println("--------------------------");
			    System.out.println("MESSAGE #" + (i + 1) + ":");
			    dumpEnvelope(msgs[i]);
			    dumpPart(msgs[i]);
			    
			    System.out.println("FROM: " + mailFrom);
			    System.out.println("SUBJECT:" + mailSubject);
			    System.out.println("DATE:" + mailSendDate);
			    System.out.println("FLAGS: " + mailFlags);
			    System.out.println("BODY:\n");
			    System.out.println("=======================================\n");
			    System.out.println(mailContent);
			    System.out.println("=======================================\n");
			   
			    System.out.println("Advisor email length:" + advisorEmailLength);
			    
			    for(int j = 0; j < advisorEmailLength; j ++)
			    {
			    	if (mailFrom.contains(advisorEmail[j]) && alreadyNotifiedList!= null && !alreadyNotifiedList.contains(mailFrom+mailSubject+mailSendDate)){
			    		
			    		alreadyNotifiedList = alreadyNotifiedList + mailFrom + mailSubject + mailSendDate;
			    		
			    		System.out.println("********NOTIFY NOW !!!!******************");

					    totalMsg="";
					    totalMsg+="FROM: " + mailFrom+"."+"\n";
					    totalMsg+="SUBJECT: " + mailSubject+ "." + "\n";
					    totalMsg+="DATE: " + mailSendDate + "." + "\n";
					    totalMsg+="BODY: \n" + mailContent+"\n";

					    showMail=new ShowMail(totalMsg);
					    showMail.setSize( 400, 300 ); 
					    showMail.setLocationRelativeTo(null);
					    showMail.setVisible( true );

			    	}
			    }
			    
			    
			    
			}
		    } else {
			System.out.println("Getting message number: " + msgnum);
			Message m = null;
			
			try {
			    m = folder.getMessage(msgnum);
			    dumpPart(m);
			    
			    System.out.println("FROM: " + mailFrom);
			    System.out.println("SUBJECT:" + mailSubject);
			    System.out.println("DATE:" + mailSendDate);
			    System.out.println("FLAGS: " + mailFlags);
			    System.out.println("BODY: " + mailContent);
			    		    		
			} catch (IndexOutOfBoundsException iex) {
			    System.out.println("Message number out of range");
			}
		    }
		    
						
		    //folder.close(false);
		    //store.close();
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception caught.");
	    	e.printStackTrace();
	    }
	    
		    
    }

	public static void dumpPart(Part p) throws Exception {
		if (p instanceof Message)
		    dumpEnvelope((Message)p);
	
		/** Dump input stream .. 
	
		InputStream is = p.getInputStream();
		// If "is" is not already buffered, wrap a BufferedInputStream
		// around it.
		if (!(is instanceof BufferedInputStream))
		    is = new BufferedInputStream(is);
		int c;
		while ((c = is.read()) != -1)
		    System.out.write(c);
	
		**/
	
		String ct = p.getContentType();
		try {
		    pr("CONTENT-TYPE: " + (new ContentType(ct)).toString());
		} catch (ParseException pex) {
		    pr("BAD CONTENT-TYPE: " + ct);
		}
		String filename = p.getFileName();
		if (filename != null)
		    pr("FILENAME: " + filename);
	
		/*
		 * Using isMimeType to determine the content type avoids
		 * fetching the actual content data until we need it.
		 */
		if (p.isMimeType("text/plain")) {
		    pr("This is plain text");
		    pr("---------------------------");
		    if (!showStructure && !saveAttachments)
			//System.out.println((String)p.getContent());
		    pr((String)p.getContent());
		    
		    mailContent = (String)p.getContent();
		    return;
		    
		} else if (p.isMimeType("multipart/*")) {
		    pr("This is a Multipart");
		    pr("---------------------------");
		    Multipart mp = (Multipart)p.getContent();
		    level++;
		    int count = mp.getCount();
		    for (int i = 0; i < count; i++)
			dumpPart(mp.getBodyPart(i));
		    level--;
		} else if (p.isMimeType("message/rfc822")) {
		    pr("This is a Nested Message");
		    pr("---------------------------");
		    level++;
		    dumpPart((Part)p.getContent());
		    level--;
		} else {
		    if (!showStructure && !saveAttachments) {
			/*
			 * If we actually want to see the data, and it's not a
			 * MIME type we know, fetch it and check its Java type.
			 */
			Object o = p.getContent();
			if (o instanceof String) {
			    pr("This is a string");
			    pr("---------------------------");
			    //System.out.println((String)o);		//change?
			    
			} else if (o instanceof InputStream) {
			    pr("This is just an input stream");
			    pr("---------------------------");
			    InputStream is = (InputStream)o;
			    int c;
			    while ((c = is.read()) != -1)
				System.out.write(c);
			} else {
			    pr("This is an unknown type");
			    pr("---------------------------");
			    pr(o.toString());
			}
		    } else {
			// just a separator
			pr("---------------------------");
		    }
		}
	
		/*
		 * If we're saving attachments, write out anything that
		 * looks like an attachment into an appropriately named
		 * file.  Don't overwrite existing files to prevent
		 * mistakes.
		 */
		if (saveAttachments && level != 0 && !p.isMimeType("multipart/*")) {
		    String disp = p.getDisposition();
		    // many mailers don't include a Content-Disposition
		    if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
			if (filename == null)
			    filename = "Attachment" + attnum++;
			pr("Saving attachment to file " + filename);
			try {
			    File f = new File(filename);
			    if (f.exists())
				// XXX - could try a series of names
				throw new IOException("file exists");
			    ((MimeBodyPart)p).saveFile(f);
			} catch (IOException ex) {
			    pr("Failed to save attachment: " + ex);
			}
			pr("---------------------------");
		    }
		}
    }


	public static void dumpEnvelope(Message m) throws Exception {
		pr("This is the message envelope");
		pr("---------------------------");
		Address[] a;
		// FROM 
		if ((a = m.getFrom()) != null) {
		    for (int j = 0; j < a.length; j++)
			pr("FROM: " + a[j].toString());
		}
	    mailFrom = a[0].toString();
		
		// REPLY TO
		if ((a = m.getReplyTo()) != null) {
		    for (int j = 0; j < a.length; j++)
			pr("REPLY TO: " + a[j].toString());
		}
	
		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
		    for (int j = 0; j < a.length; j++) {
			pr("TO: " + a[j].toString());
			InternetAddress ia = (InternetAddress)a[j];
			if (ia.isGroup()) {
			    InternetAddress[] aa = ia.getGroup(false);
			    for (int k = 0; k < aa.length; k++)
				pr("  GROUP: " + aa[k].toString());
			}
		    }
		}
	
		// SUBJECT
		pr("SUBJECT: " + m.getSubject());
		mailSubject = m.getSubject();
		
		// DATE
		Date d = m.getSentDate();
		pr("SendDate: " +
		    (d != null ? d.toString() : "UNKNOWN"));
	
		mailSendDate = d.toString();
		
		// FLAGS
		Flags flags = m.getFlags();
		StringBuffer sb = new StringBuffer();
		Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags
	
		boolean first = true;
		String s = null;
		for (int i = 0; i < sf.length; i++) {
		    
		    Flags.Flag f = sf[i];
		    if (f == Flags.Flag.ANSWERED)
			s = "\\Answered";
		    else if (f == Flags.Flag.DELETED)
			s = "\\Deleted";
		    else if (f == Flags.Flag.DRAFT)
			s = "\\Draft";
		    else if (f == Flags.Flag.FLAGGED)
			s = "\\Flagged";
		    else if (f == Flags.Flag.RECENT)
			s = "\\Recent";
		    else if (f == Flags.Flag.SEEN)
			s = "\\Seen";
		    else
			continue;	// skip it
		    if (first)
			first = false;
		    else
			sb.append(' ');
		    sb.append(s);
		}
		mailFlags = s;
	
		String[] uf = flags.getUserFlags(); // get the user flag strings
		for (int i = 0; i < uf.length; i++) {
		    if (first)
			first = false;
		    else
			sb.append(' ');
		    sb.append(uf[i]);
		}
		pr("FLAGS: " + sb.toString());
	
		// X-MAILER
		String[] hdrs = m.getHeader("X-Mailer");
		if (hdrs != null)
		    pr("X-Mailer: " + hdrs[0]);
		else
		    pr("X-Mailer NOT available");
	    }
	
	    static String indentStr = "                                               ";
	    static int level = 0;
	
	    /**
	     * Print a, possibly indented, string.
	     */
	    public static void pr(String s) {
		if (showStructure)
		    System.out.print(indentStr.substring(0, level * 2));
		//System.out.println(s);
    }



}
