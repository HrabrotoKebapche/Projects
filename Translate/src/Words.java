import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Words {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		Tr root = new Tr();
		String file = "C:\\Users\\kristian\\Downloads\\words2.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {

				root.insert(line.toLowerCase(), root);
				System.out.println(line);
			}
		}
		
		
		
		
		
		FileOutputStream fout = new FileOutputStream("tree.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(root);
		oos.close();

		String eng = "how are you what are you doing";
		
		FileInputStream fin = new FileInputStream("tree.ser");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Tr tre = (Tr) ois.readObject();
		
		 String[] parts = eng.split(" ");
	        String full = "";

	        for(String s : parts){
	            full += tre.search(s.toLowerCase(), tre);
	            full += " ";
	            
	        }
		System.out.println(full);
		
	}
}
