package com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleHelper {
	static ObjectMapper obj = new ObjectMapper();

	public static String getAccessToken(String authcode) throws IOException {
		
		System.out.println("into helper");
		URL url = new URL("https://www.googleapis.com/oauth2/v4/token");
		String params = "code=" + authcode + "&client_id=" + Credentials.clientId + "&client_secret="
				+ Credentials.clientSecret
				+ "&redirect_uri=http://localhost:8989/oauthWithGoogle/response&grant_type=authorization_code";
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
		writer.write(params);
		writer.flush();
		String line, outputString = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		while ((line = reader.readLine()) != null) {
			outputString += line;
		}
		System.out.println("Google response is "+outputString);
		GoogleAccessToken googleToken = obj.readValue(outputString, GoogleAccessToken.class);
		System.out.println("google refresh token is "+googleToken.getRefresh_token());
		System.out.println("google id token is "+ googleToken.getId_token());
		System.out.println("access token expires in"+googleToken.getExpires_in());
		return googleToken.getAccess_token();
		}

	public static String getUserInfo(String accesstoken) throws IOException {

		URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/primary?access_token=" + accesstoken);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		String line, outputString = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		while ((line = reader.readLine()) != null) {
			outputString += line;
		}
		/*UserPojo info = obj.readValue(outputString, UserPojo.class);*/

		return outputString;

	}
}
