import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class TrTry {
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Tr root = new Tr();

		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		boolean flag = false;

		PdfReader reader = new PdfReader("C:\\Users\\kristian\\Downloads\\Crime_and_Punishment.pdf");
		int n = reader.getNumberOfPages();
		for (int i = 5; i < n; i++) {
			String page = PdfTextExtractor.getTextFromPage(reader, i);
			String[] parts = page.split(" ");
			for (String s : parts) {
				String temp = "";
				char[] charArray = s.toCharArray();

				for (char c : charArray) {

					if (Character.isLetter(c)) {
						temp = temp + c;
					}

				}

				char[] charArray2 = temp.toCharArray();
				for (char c : charArray2) {
					if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
						
						
					}else {
						flag = true;
						//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					}
				}

				if (!flag) {
					System.out.println(temp);
					root.insert(temp.toLowerCase(), root);
				}

				flag = false;

			}
		}

		FileOutputStream fout = new FileOutputStream("tree.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(root);
		oos.close();

		FileInputStream fin = new FileInputStream("tree.ser");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Tr tre = (Tr) ois.readObject();
		
		
		File f = new File("C:\\Users\\kristian\\Downloads\\words2.txt");
		File f2 = new File("D:\\WordsT.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		String finitto = "";
		while ((line = br.readLine()) != null) {
			finitto += line.toLowerCase();
			finitto += " ";
			finitto += tre.search(line.toLowerCase(), tre);
			finitto += System.getProperty("line.separator");
			
		}
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f2));
	    writer.write(finitto);
	    writer.close();
		br.close();

		System.out.println(tre.search("pleasure", tre));

	}
}