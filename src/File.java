public class File {
	String fileName;
	String fileType;
	
	
	public void setFileName(String name) throws IllegalArgumentException {
		if (name == null || name.isBlank()) 
			throw new IllegalArgumentException("Please give a name to the file");
		
		fileName = name;
	}
	
	public void setFileType(String type) throws IllegalArgumentException {
		if (type == null || type.isBlank())
			throw new IllegalArgumentException("Please give a type to the file");
		
		fileType = type;
	}
	
	public String getFileName() {
		return "Name of file: "+ fileName;
	}
	
	public String getFileType() {
		return "Type of file: "+ fileType;
	}
	
	public File(String name, String type) {
		setFileName(name);
		setFileType(type);
	}
	
	@Override
	public String toString () {
		return getFileName() + "\n" + getFileType();
	}
	
	
	
}
