package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class URL_get {
	public static void getURL() {
		try {
			URL url = new URL("https://so2-api.mutoys.com/json/people/all.json");

			HttpsURLConnection connection = null;

			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			System.out.println(connection.getResponseCode());
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
						StandardCharsets.UTF_8);
						BufferedReader reader = new BufferedReader(isr)) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
				}
			}
		} catch (IOException e) {

		}
	}


	public static void main(String args[]) {
		getURL();
	}
}
