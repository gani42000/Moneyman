package accountManagement.fileOperation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

class FileAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	public FileAlreadyExistsException (String message) {
		super(message);
	}
}

public class  FileOps {	
	/* Creating a logging instance */
	private static final Logger LOGGER = Logger.getLogger(FileOps.class.getName());
	
	
	private String filepath;
	private File fileObj;
	private String fileName;
	
	//getters 
	public String getFilePath() {
		return this.filepath;
	}
	File getFileObj() {
		return  this.fileObj;
	}
	public String getFileName() {
		return this.fileName;
	}
	//setters
	public void setFilePath(String filepath) {
		this.filepath = filepath;
	}
	void setFileObj(File fileObj) {
		this.fileObj = fileObj;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//establishing file IO connection
	
	public void createConnection() throws FileAlreadyExistsException {
		try {
			fileObj = new File(this.getFilePath() + "/" + this.getFileName());
			if(fileObj.createNewFile()) {
				LOGGER.info("File created Successfully");		
			}
			else {
				LOGGER.info("File already exists");
				throw new FileAlreadyExistsException("File already exists please create a new account with different name and account number");
			}
		}
		catch(IOException e) {
			LOGGER.info("Unknown IO exception");
			System.out.println("Unknown IO exception");
		}
	}
	public void writeToFile(String jsonString) {
		try {
			FileWriter fileWriter = new FileWriter(this.fileObj);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}





