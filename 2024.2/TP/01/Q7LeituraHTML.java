import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q7LeituraHTML {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        String[] vowels = {"a", "e", "i", "o", "u", "á", "é", "í", "ó", "ú", "à", "è", "ì", "ò", "ù", "ã", "õ", "â", "ê", "î", "ô", "û"};
        Map<String, Integer> counts = new HashMap<>();
        for (String vowel : vowels) {
            counts.put(vowel, 0);
        }
        counts.put("consoante", 0);
        counts.put("<br>", 0);
        counts.put("<table>", 0);

        String pageName;
        while (!(pageName = MyIO.readLine()).equals("FIM")) {
            String urlAddress = MyIO.readLine();
            URL url = new URL(urlAddress);
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder contentBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
                
                String content = contentBuilder.toString();
                String text = decodeHtmlEntities(content);

                for (String vowel : vowels) {
                    int index = 0;
                    while ((index = text.indexOf(vowel, index)) != -1) {
                        counts.put(vowel, counts.get(vowel) + 1);
                        index += vowel.length();
                    }
                }
                for (char c : text.toCharArray()) {
                    if (Character.isLetter(c)) {
                        String charStr = String.valueOf(c);
                        if (!counts.containsKey(charStr)) {
                            counts.put("consoante", counts.get("consoante") + 1);
                        }
                    }
                }
                counts.put("<br>", countOccurrences(content, "<br>"));
                counts.put("<table>", countOccurrences(content, "<table>"));
            }
            System.out.println(formatOutput(counts) + " " + pageName);
            for (String vowel : vowels) {
                counts.put(vowel, 0);
            }
            counts.put("consoante", 0);
            counts.put("<br>", 0);
            counts.put("<table>", 0);
        }
        scan.close();
    }

    private static int countOccurrences(String content, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = content.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }

    private static String decodeHtmlEntities(String input) {
        return input.replace("&aacute;", "á")
                    .replace("&eacute;", "é")
                    .replace("&iacute;", "í")
                    .replace("&oacute;", "ó")
                    .replace("&uacute;", "ú")
                    .replace("&agrave;", "à")
                    .replace("&egrave;", "è")
                    .replace("&igrave;", "ì")
                    .replace("&ograve;", "ò")
                    .replace("&ugrave;", "ù")
                    .replace("&atilde;", "ã")
                    .replace("&otilde;", "õ")
                    .replace("&acirc;", "â")
                    .replace("&ecirc;", "ê")
                    .replace("&icirc;", "î")
                    .replace("&ocirc;", "ô")
                    .replace("&ucirc;", "û");
    }

    private static String formatOutput(Map<String, Integer> counts) {
        return String.format(
            "a(%d) e(%d) i(%d) o(%d) u(%d) á(%d) é(%d) í(%d) ó(%d) ú(%d) à(%d) è(%d) ì(%d) ò(%d) ù(%d) ã(%d) õ(%d) â(%d) ê(%d) î(%d) ô(%d) û(%d) consoante(%d) <br>(%d) <table>(%d)",
            counts.get("a"), counts.get("e"), counts.get("i"), counts.get("o"), counts.get("u"),
            counts.get("á"), counts.get("é"), counts.get("í"), counts.get("ó"), counts.get("ú"),
            counts.get("à"), counts.get("è"), counts.get("ì"), counts.get("ò"), counts.get("ù"),
            counts.get("ã"), counts.get("õ"), counts.get("â"), counts.get("ê"), counts.get("î"),
            counts.get("ô"), counts.get("û"), counts.get("consoante"), counts.get("<br>"), counts.get("<table>")
        );
    }
}
