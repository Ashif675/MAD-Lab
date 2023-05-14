import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.juet.ac.in/";
        String result = "";

        try {
            URL websiteUrl = new URL(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(websiteUrl.openStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();

            // Log the contents of the result variable
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
