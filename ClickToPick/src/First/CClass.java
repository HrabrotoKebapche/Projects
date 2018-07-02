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
			CClassThread t = new CClassThread(sock, dir);
			t.start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
