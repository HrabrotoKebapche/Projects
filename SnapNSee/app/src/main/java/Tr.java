import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Serializable;

public class Tr implements Serializable {

    final int ALPHABET_SIZE = 26;
    static String url = "http://online.ectaco.com/main.jsp?do=e-services-dictionaries-word_translate1&direction=1&status=translate&lang1=23&lang2=bg&source=";
    boolean isEndOfWord;
    String bg;
    Tr[] children = new Tr[ALPHABET_SIZE];

    public Tr() {
        isEndOfWord = false;
        String bg = "---";
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            children[i] = null;
        }
    }

    public void insert(String key, Tr pCrawl) throws IOException {
        int level;
        int length = key.length();
        int index;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new Tr();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.bg = Translate(key);
        pCrawl.isEndOfWord = true;
    }

    String search(String key, Tr pCrawl) {
        int level;
        int length = key.length();
        int index;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return "---";

            pCrawl = pCrawl.children[index];
        }

        return pCrawl.bg;
    }

    static String Translate(String word) throws IOException {
        String url2 = url + word;
        Document document;
        document = Jsoup.connect(url2).get();
        String el = document.select("td").get(59).text();
        String[] w = el.split(",");
        String first = w[0];
        if(first.contains("Part of speech")) {
            return "---";
        }

        return first;

    }

}