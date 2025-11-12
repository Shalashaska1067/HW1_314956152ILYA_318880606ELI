import java.sql.Date;

public class Message {
	String sender;
	String content;
	String receiver;
	Date senDate;
	
	
	public void setSender(String sender) throws IllegalArgumentException {
		if (sender == null || sender.isBlank())
			throw new IllegalArgumentException("Please enter the name of the sender");
		this.sender = sender;	
	}
	
	public void setContent(String content) throws IllegalArgumentException {
		if (content == null || content.isBlank())
			throw new IllegalArgumentException("Please add content to the message");
		this.content = content;
	}
	
	public void setSenDate(String date)throws IllegalArgumentException {
		Date newDate;
		try {
		newDate = Date.valueOf(date);
		}
		catch (Exception e) { 
			throw new IllegalArgumentException("Please use the correct Date format for the send date");
		}
		Date today = new Date(System.currentTimeMillis());
		if(newDate.after(today))
			throw new IllegalArgumentException("Send date cannot be in the future");
		senDate = newDate;
	}
	
	public void setReceiver(String receiver) throws IllegalArgumentException {
		if (receiver == null || receiver.isBlank())
			throw new IllegalArgumentException("Please enter the name of the receiver");
		this.receiver = receiver;	
	}
	
	public String getSender() {
		return ("The name of the sender is:"+ sender);
	}
	
	public String getContent() {
		return ("The content of the message was: "+ content);
		
	}
	
	public String getSenDate() {
		return ("The date of the message was: "+ senDate.toString());
	}
	
	public String gMessageType() {
		return("The name of the receiver is: "+ receiver);
		
	}
	
	public Message(String sender, String content, String senDate, String receiver) {
		setSender(sender);
		setContent(content);
		setSenDate(senDate);
		setReceiver(receiver);
	}

	public Message(String sender, String content, String receiver) {
		setSender(sender);
		setContent(content);
		senDate = new Date(System.currentTimeMillis());
		setReceiver(receiver);		
	}
	
	@Override
	public String toString () {
		return ("The name of the sender is: "+ sender +"\nThe name of the receiver is: "+ receiver +"\nThe content of the message was: "+ content +"\nThe date of the message was: "+ senDate.toString());
	}
}
