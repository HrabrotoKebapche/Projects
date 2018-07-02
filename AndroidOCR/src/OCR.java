import java.io.File;
import java.util.Scanner;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();

		String str = crackImage(s);
		System.out.println(str);
		sc.close();

	}

	public static String crackImage(String filePath) {

		File imageFile = new File(filePath);

		ITesseract instance = new Tesseract();

		try {

			String result = instance.doOCR(imageFile);

			return result;

		} catch (TesseractException e) {

			System.err.println(e.getMessage());

			return "Error while reading image";

		}
	}

	public static String crackImageFile(File imageFile) {

		ITesseract instance = new Tesseract();

		try {

			String result = instance.doOCR(imageFile);

			return result;

		} catch (TesseractException e) {

			System.err.println(e.getMessage());

			return "Error while reading image";

		}
	}

}