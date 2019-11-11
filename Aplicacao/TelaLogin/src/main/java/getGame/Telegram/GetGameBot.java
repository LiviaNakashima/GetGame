package getGame.Telegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetGameBot {

    public void apiTelegram() throws MalformedURLException, IOException {

        String urlString = "https://api.telegram.org/bot1026640277:AAFj-j5tPswMT-X5g2zTCexbvOcMxiPG3i8/sendMessage?chat_id=-1001232076680&text=Eduardo corno";

        String apiToken = "1026640277:AAFj-j5tPswMT-X5g2zTCexbvOcMxiPG3i8";
        String chatId = "1232076680";
        String text = "Hello world!";

        urlString = String.format(urlString, apiToken, chatId, text);

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        String response = sb.toString();
    }
}
