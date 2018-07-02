package First;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Instant;
import java.util.Scanner;

public class SClassThread extends Thread {
	public Socket sock;
	public String dir;

	public SClassThread(Socket sock, String dir) {
		this.sock = sock;
		this.dir = dir;

	}

	public void run() {
		try {
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
			String filesNeeded = "";
			filesNeeded = dIn.readUTF();

			System.out.println(filesNeeded);
			Scanner sc = new Scanner(filesNeeded);
			dOut.writeUTF("send");

			while (sc.hasNextLine()) {
				String curr = sc.nextLine();
				if (curr.equals("") || curr.equals(" ") || curr.equals("\n") || curr == null) {
					break;
				}
				File f = new File(dir + "\\" + curr);
				int size = (int) f.length();
				dOut.writeInt(size);
				dOut.flush();
				sendFile(sock, (dir + "\\" + curr), size);
			}
			sc.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void sendFile(Socket s, String file, int refSize) throws IOException {
		long start = System.nanoTime();
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);

		byte[] buffer = new byte[4096];

		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}

		dos.flush();
		fis.close();
		// dos.close();
		long elapsedTime = System.nanoTime() - start;
		status("sent", refSize, refSize, elapsedTime);

	}

	public static void status(String stat, int size, int totalRead, long time) {
		try (FileWriter fw = new FileWriter("C:\\Users\\kristian\\Desktop\\myfile2.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.print("Current session - ");
			out.print(Instant.now() + "|");
			out.print(stat + "|");
			out.print("size:" + Math.abs(size) + "|");
			out.print("Out:" + Math.abs(totalRead) + "|");
			out.print("time:" + time + "|");
			out.print("files:" + 1 + "|");
			out.print("\n");
		} catch (IOException e) {

		}
	}
}
