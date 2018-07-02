package First;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Scanner;

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
				SClassThread t = new SClassThread(sock,dir);
				Gui g = new Gui(t,"C:\\Users\\kristian\\Desktop\\myfile.txt");
				System.out.println("Connected");
				
			
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
