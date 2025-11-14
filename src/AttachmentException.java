public class AttachmentException extends Exception {

    public AttachmentException(String message) {
        super(message);
    }

    public AttachmentException() {
        super("Attachment not found in attachments list");
    }
}