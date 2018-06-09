package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.SoldOutItems;
import twitter4j.JSONException;

public class URL_get {
	static StringBuilder sb = new StringBuilder();
	static HttpURLConnection connection = null;


	public static String getAPIString(String apiUrl) throws JSONException {
		try {
			URL url = new URL(apiUrl);
			int i =0;

			//make pattern
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
						StandardCharsets.UTF_8);
						BufferedReader reader = new BufferedReader(isr)) {
					String line;
					while ((line = reader.readLine()) != null) {
							sb.append(line);
					}
				}
			}
		} catch (IOException e) {

		}
		return sb.toString();
	}


	public static void main(String args[]) throws JSONException {
		String url = "http://so2-api.mutoys.com/master/item.json";

		ObjectMapper mapper = new ObjectMapper();
		try {
			SoldOutItems item = mapper.readValue(getAPIString(url), SoldOutItems.class);
			System.out.println("");
		} catch (JsonParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
