package First;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Scanner;

public class CClassThread extends Thread {
	public Socket sock;
	public String dir;

	public CClassThread(Socket sock, String dir) {
		this.sock = sock;
		this.dir = dir;

	}

	public void run() {
		try {
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
		int repeated = 0;
		String filesNeed = "";
		String filesDelete = "";
		Scanner sc = new Scanner(fileList2);
		while (sc.hasNextLine()) {
			String curr = sc.nextLine();
			if (!fileList.contains(curr)) {
				filesNeed += curr + "\n";
			}else {
				repeated++;
			}
		}
		status(repeated);
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
		del(filesDelete, dir);
		System.out.println(answer);
		Scanner sc3 = new Scanner(filesNeed);
		while (sc3.hasNextLine()) {
			String curr = sc3.nextLine();
			int size = 0;
			// here is the problem...read more characters than its needed
			size = dIn.readInt();

			if (curr.equals("") || curr.equals(" ") || curr.equals("\n") || curr == null) {
				break;
			}
			saveFile(sock, (dir + "\\" + curr), size);
		}
		sc3.close();
		//Here, it deletes the files which are not in the server`s folder
		del(filesDelete, dir);
	}catch(IOException e) {
		System.out.println(e);
	}
	}

	public static void saveFile(Socket sock, String s, int size) throws IOException {
		new Reminder(3);
		long start = System.nanoTime();  
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
		// dis.close();
		long elapsedTime = System.nanoTime() - start;
		status("finished",size,totalRead,elapsedTime);

	}

	public static void del(String s, String dir) {

		Scanner sc = new Scanner(s);

		while (sc.hasNextLine()) {
			String curr = sc.nextLine();
			System.out.println("shte triem" + curr);
			try {
				long start = System.nanoTime();  
				File f = new File(dir + "\\" + curr);
				
				int size = (int)f.length();
				long elapsedTime = System.nanoTime() - start;
				Files.delete(Paths.get(dir + "\\" + curr));
				status("deleted",size,size,elapsedTime);
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
	
	public static void status(String stat,int size,int totalRead, long time) {
		try(FileWriter fw = new FileWriter("C:\\Users\\kristian\\Desktop\\myfile.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.print(Instant.now() + "|");
			    out.print(stat + "|");
			    out.print("size:"+size + "|");
			    out.print("In:"+totalRead + "|");
			    out.print("time:"+time + "|");
			    out.print("files:" + 1 +"|");
			    out.print("\n");
			} catch (IOException e) {

			}
	}
	public static void status(int rep) {
		try(FileWriter fw = new FileWriter("C:\\Users\\kristian\\Desktop\\myfile.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.print("Current Session - " + rep+ " repeating"+"|" );
			} catch (IOException e) {

			}
	}
}
