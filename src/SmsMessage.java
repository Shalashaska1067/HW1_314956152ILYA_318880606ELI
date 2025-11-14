public class SmsMessage extends Message implements IDigital {

    private String phoneNumber;

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {
    	if (phoneNumber == null || phoneNumber.isBlank()) {
    		throw new PhoneNumberException("Phone number cannot be empty");
    	}
    	
    	this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber() {
    	return "The phone number is: " + phoneNumber;
    }
    
    public SmsMessage(String sender, String content, String senDate,String receiver, String phoneNumber) throws PhoneNumberException {
        super(sender, content, senDate, receiver);
        setPhoneNumber(phoneNumber);
    }

    public SmsMessage(String sender, String content,String receiver, String phoneNumber) throws PhoneNumberException {
        super(sender, content, receiver);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via SMS";
    }

    @Override
    public String generatePreview() {
    	String msg;
    	if (content.length() <= 15)
    		msg = content;
    	else {
			msg = content.substring(0,15) + "...";
		}
    	return "[SMS] " + phoneNumber + ": " + msg;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + getPhoneNumber();
    }
}
