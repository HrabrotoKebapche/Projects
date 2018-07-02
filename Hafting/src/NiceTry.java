
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

public class NiceTry {

	final static String img1 = "wholeImage.jpg";
	final static String img2 = "croppedImage.jpg";
	final static String img3 = "half.jpg";

	public static void main(String[] args) {
		try {
			BufferedImage originalImgage = ImageIO.read(new File(img1));
			
			System.out.println(
					"Original Image " + originalImgage.getWidth() + "x" + originalImgage.getHeight());

			showImage(img1);
			
			
			//edit for example 50 /n 50 /n 600 /n 600
			Scanner sc = new Scanner(System.in);
			int[] arr = new int[4];
			for (int i = 0; i < 4; i++) {
				arr[i] = sc.nextInt();
			}
			sc.close();
			BufferedImage SubImgage = originalImgage.getSubimage(arr[0], arr[1], arr[2], arr[3]);
			System.out.println("Cropped Image " + SubImgage.getWidth() + "x" + SubImgage.getHeight());

			File outputfile = new File(img2);
			ImageIO.write(SubImgage, "jpg", outputfile);

			System.out.println("Image cropped successfully: " + outputfile.getPath());
			showImage(img2);

			toBlackAndWhitePre(SubImgage);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showImage(String s) {

		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(s);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
	

	public static void toBlackAndWhitePre(BufferedImage img) throws IOException {
		//the second parameter can be changed for different edge
		toBlackAndWhite(img, 30);
	}

	public static void toBlackAndWhite(BufferedImage img, int precision) throws IOException {
		int w = img.getWidth();
		int h = img.getHeight();

		precision = (0 <= precision && precision <= 100) ? precision : 50;

		int limit = 255 * precision / 100;

		for (int i = 0, j; i < w; ++i) {
			for (j = 0; j < h; ++j) {
				Color color = new Color(img.getRGB(i, j));
				if (limit <= color.getRed() || limit <= color.getGreen() || limit <= color.getBlue()) {
					img.setRGB(i, j, Color.WHITE.getRGB());
				} else {
					img.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}
		File outputfile = new File(img3);
		ImageIO.write(img, "jpg", outputfile);
		showImage(img3);
	}

}