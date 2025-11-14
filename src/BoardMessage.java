public class BoardMessage extends Message {
	PriorityType priority;
	String title;
	
	public void setPriority(String pri) throws IllegalArgumentException {
		if (pri == null || pri.isBlank())
			throw new IllegalArgumentException("Please enter priority type");
		
		pri = pri.trim().toUpperCase();
		try {
			priority = PriorityType.valueOf(pri);
		}catch (Exception e) {
			throw new IllegalArgumentException("Priority must be LOW, REGULAR or URGENT");
		}
	}
	
	public void setTitle(String title) throws IllegalArgumentException {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Please enter title");
        }
        this.title = title;
    }

    public String getPriority() {
        return "The priority type is: " + priority.toString();
    }
    
    public String getTitle() {
        return "The title is: " + title;
    }
    
    public BoardMessage(String sender, String content, String senDate, String receiver, String pri, String title) {
    	super(sender, content, senDate, receiver);
    	setPriority(pri);
    	setTitle(title);
    }
    
    public BoardMessage (String sender, String content, String receiver, String title) {
    	super(sender, content, receiver);
    	setTitle(title);
    	priority = PriorityType.REGULAR;
    }
    
    @Override
    public String toString() {
    	return super.toString() + "\n" + getPriority() + "\n" + getTitle();
    }
    
    public boolean isUrgent() {
        return priority == PriorityType.URGENT;
    }
    
	@Override
    public String generatePreview() {
    	String msg;
    	if (content.length() <= 15)
    		msg = content;
    	else {
			msg = content.substring(0,15) + "...";
		}
    	return "[Board]" + sender +": "+ msg;
    }
}
