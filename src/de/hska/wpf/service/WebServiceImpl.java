package de.hska.wpf.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.util.Log;
import de.hska.wpf.service.httpClient.MyFriendsHttpClient;

public class WebServiceImpl implements WebService{
	
	private static WebService instance = null;
	private MyFriendsHttpClient httpClient;
	
	public static synchronized WebService getInstance(){
		if(instance == null){
			instance = new WebServiceImpl();
		}
		return instance;
	}
	
	private WebServiceImpl(){
		Log.i("MyFriends - WebServiceImpl", "Initialize WebService");
		this.httpClient = new MyFriendsHttpClient();
	}

	@Override
	public List<String> getWeatherInfoByCity(String city) throws UnsupportedEncodingException {
		
		if(city != null){
			city = city.replaceAll("\\s+$", "+");
			String uri = "http://www.google.com/ig/api?weather=" + city;
			
			try{
				String xmlResponse = httpClient.executeHttpRequest(uri);
				System.out.println(xmlResponse);
			}catch (IOException e) {
                // TODO do proper exception handling
                System.err.println("Exception: " + e.getMessage());
                e.printStackTrace();
            }
		}
		return null;
	}

}
