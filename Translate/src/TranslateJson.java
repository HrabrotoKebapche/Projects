import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TranslateJson {
	public static void main(String[] args) throws IOException {
		String url = "http://online.ectaco.com/main.jsp?do=e-services-dictionaries-word_translate1&direction=1&status=translate&lang1=23&lang2=bg&source=";
		String know = "big";
		String url2 = url + know;

		Document document = Jsoup.connect(url2).get();

		String el = document.select("td").get(59).text();
		String[] w = el.split(",");
		String first = w[0];

		System.out.println(first);

	}
}
