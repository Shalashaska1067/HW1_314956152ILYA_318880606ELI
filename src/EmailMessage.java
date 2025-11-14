import java.util.ArrayList;

public class EmailMessage extends Message implements IDigital {
	String subject;
	ArrayList<File> attachments;

	public void setSubject(String subject) throws IllegalArgumentException {
		if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Please enter subject");
        }
        this.subject = subject;
	}
	
	public void setAttachments(ArrayList<File> attachments) {
        if (attachments == null) {
            this.attachments = new ArrayList<>();
        } else {
            this.attachments = attachments;
        }
    }
	
	public String getSubject() {
        return "The subject of the message is: " + subject;
    }
	
	public ArrayList<File> getAttachments() {
	    if (attachments == null) {
	        attachments = new ArrayList<File>();
	    }
	    return attachments;
	}
	
	public EmailMessage(String sender, String content, String senDate, String receiver,String sub, ArrayList<File> attch) {
		super(sender, content, senDate, receiver);
		setSubject(sub);
		setAttachments(attch);
	}
	
	public EmailMessage (String sender, String content, String receiver, String sub) {
    	super(sender, content, receiver);
		setSubject(sub);
    }
	
	@Override
	public String toString() {
		return super.toString() + "\n" + getSubject() + "\n" + getAttachments();
	}
	
	@Override
	public String printCommunicationMethod() {
		return "Sent via Email";
	}
	
	@Override
	public String generatePreview() {
		return "[Email] Subject: " + subject + " | From: " + sender;
    }
	
	public void addAttachment(File file) throws IllegalArgumentException {
	    if (file == null)
	        throw new IllegalArgumentException("Attachment file cannot be null");
	    
	    if (attachments == null)
	        attachments = new ArrayList<File>();
	    
	    attachments.add(file);
	}
	
	public void removeAttachment(File file) throws AttachmentException {
        if (file == null) {
            throw new IllegalArgumentException("File to remove cannot be null");
        }

        if (attachments == null || attachments.isEmpty()) {
            throw new AttachmentException("No attachments in this email");
        }

        boolean removed = false;

        for (int i = attachments.size() - 1; i >= 0; i--) {
            if (attachments.get(i).equals(file)) {
                attachments.remove(i);
                removed = true;
            }
        }

        if (!removed) {
            throw new AttachmentException("Attachment not found in attachments list");
        }
    }
	
}
