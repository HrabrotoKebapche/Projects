import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Brute {
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {

		int a, b, c, d, e, f, g, h = 32;
		char z, y, x, w, v, u, t, s;
		String str = "";
		int i = 4;
		//PrintWriter writer = new PrintWriter("C:\\Users\\kristian\\Desktop\\aaa.txt", "UTF-8");

		for (i = 4; i < 9; i++) {

			for (a = 32; a < 127; a++) {
				z = (char) a;

				for (b = 32; b < 127; b++) {
					y = (char) b;

					for (c = 32; c < 127; c++) {
						x = (char) c;

						for (d = 32; d < 127; d++) {
							w = (char) d;

							if (i > 4) {

								for (e = 32; e < 127; e++) {
									v = (char) e;
									if (i > 5) {

										for (f = 32; f < 127; f++) {
											u = (char) f;
											if (i > 6) {

												for (g = 32; g < 127; g++) {
													t = (char) g;
													if (i > 7) {

														for (h = 32; h < 127; h++) {
															s = (char) h;

															str += s + "" + t
																	+ "" + u
																	+ "" + v
																	+ "" + w
																	+ "" + x
																	+ "" + y
																	+ "" + z;
															//System.out.println(str);
															str = "";
														
															// writer.println(str);
														}

													}
													str += t + "" + u + "" + v
															+ "" + w + "" + x
															+ "" + y + "" + z;
													//System.out.println(str);
													str = "";
												}
											}
											str += u + "" + v + "" + w + "" + x
													+ "" + y + "" + z;
											//System.out.println(str);
											str = "";
										}
									}
									str += v + "" + w + "" + x + "" + y + ""
											+ z;
									//System.out.println(str);
									str = "";
								}
							}
							str += w + "" + x + "" + y + "" + z;
							//System.out.println(str);
							str = "";
						}
					}
				}
			}
		}
		//writer.close();
	}
}
