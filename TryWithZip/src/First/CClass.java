package First;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Scanner;

public class CClass {
	public final static int PORT = 5501;
	public final static String dir = "C:\\\\Users\\\\kristian\\\\Desktop\\\\dir2";
	public final static String SERVER = "127.0.0.1";

	public static void main(String[] args) {
		Socket sock = null;
		try {
			sock = new Socket(SERVER, PORT);
			System.out.println("Connecting...");
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
			String fileList = "";
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
			// Read the string with files
			DataInputStream dIn = new DataInputStream(sock.getInputStream());
			boolean done = false;
			String fileList2 = "";
			while (!done) {
				fileList2 = dIn.readUTF();
				done = true;
			}

			String filesNeed = "";
			String filesDelete = "";
			Scanner sc = new Scanner(fileList2);
			while (sc.hasNextLine()) {
				String curr = sc.nextLine();
				if (!fileList.contains(curr)) {
					filesNeed += curr + "\n";
				}
			}
			sc.close();
			Scanner sc2 = new Scanner(fileList);
			while (sc2.hasNextLine()) {
				String curr = sc2.nextLine();
				if (!fileList2.contains(curr)) {
					filesDelete += curr + "\n";
				}
			}
			sc2.close();
			// Send the needed files
			DataOutputStream dOut = new DataOutputStream(sock.getOutputStream());
			dOut.writeUTF(filesNeed);
			dOut.flush();
			// System.out.println("1to " + filesNeed);
			// System.out.println("2to " + filesDelete);
			// Receive file

			String answer = "";
			while (done) {
				answer = dIn.readUTF();
				done = false;
			}
			System.out.println("styga li do tuk");
			
			int size =0;
			size= dIn.readInt();
			
			saveFile(sock, (dir + "\\" + "opazip.zip"), size);
			
			
			del(filesDelete,dir);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveFile(Socket sock, String s, int size) throws IOException {
		DataInputStream dis = new DataInputStream(sock.getInputStream());
		FileOutputStream fos = new FileOutputStream(s);
		byte[] buffer = new byte[4096];
		System.out.println("eto tuka e " + size);
	
		int read = 0;
		int totalRead = 0;
		int remaining = size;
		while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}

		fos.close();
		//dis.close();

	}
	
	public static void del(String s,String dir) {
		
		Scanner sc = new Scanner(s);
		
		while (sc.hasNextLine()) {
			String curr = sc.nextLine();
			System.out.println("shte triem"+curr);
			try {
			    Files.delete(Paths.get(dir+"\\"+curr));
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", s);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", s);
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			}
		}
		sc.close();
		
	}
}
