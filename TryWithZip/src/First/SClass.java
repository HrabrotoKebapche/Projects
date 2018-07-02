package First;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SClass {
	public final static int PORT = 5501;
	public final static String dir = "C:\\\\Users\\\\kristian\\\\Desktop\\\\dir1";

	public static void main(String[] args) {
		ServerSocket serversock = null;
		Socket sock = null;
		try {
			serversock = new ServerSocket(PORT);
			while (true) {
				sock = serversock.accept();
				System.out.println("Connected");
				String fileList = "";
				File folder = new File(dir);
				File[] listOfFiles = folder.listFiles();
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						// System.out.println("File " + listOfFiles[i].getName());
						fileList += listOfFiles[i].getName() + "\n";
					} else if (listOfFiles[i].isDirectory()) {
						// System.out.println("Directory " + listOfFiles[i].getName());
						fileList += listOfFiles[i].getName() + "\n";
					}
				}
				System.out.println(fileList);
				DataOutputStream dOut = new DataOutputStream(sock.getOutputStream());
				dOut.writeUTF(fileList);
				dOut.flush();
				DataInputStream dIn = new DataInputStream(sock.getInputStream());
				String filesNeeded="";
				filesNeeded = dIn.readUTF();
				
				System.out.println(filesNeeded);
				Scanner sc = new Scanner(filesNeeded);
				dOut.writeUTF("send");
				FileOutputStream fos = new FileOutputStream(dir+"\\"+"test.zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				while (sc.hasNextLine()) {
					String curr = sc.nextLine();
					if (curr.equals("") || curr.equals(" ") || curr.equals("\n") || curr == null) {
						break;
					}
					addToZipFile(dir+"\\"+curr,zos);

				}
				sc.close();
				zos.close();
				fos.close();
				System.out.println("napylni zipa");
				File f = new File("test.zip");
				int size = (int) f.length();
				dOut.writeInt(size);
				dOut.flush();
				sendFile(sock,dir+"\\"+"test.zip");
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendFile(Socket s,String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		dos.flush();
		fis.close();
		//dos.close();
		
			
}
	
	public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

		System.out.println("Writing '" + fileName + "' to zip file");

		File file = new File(fileName);
		System.out.println((int)file.length());
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}
