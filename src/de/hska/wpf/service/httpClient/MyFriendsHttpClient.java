package de.hska.wpf.service.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class MyFriendsHttpClient {
	
	private HttpClient httpClient;
	
	public MyFriendsHttpClient(){
		this.httpClient = new DefaultHttpClient();
	}
	
	 /**
     * 
     * @param uri
     * @return The response as a String or null
     * @throws UnsupportedEncodingException 
     */
	public String executeHttpRequest(String uri) throws UnsupportedEncodingException{
		HttpContext context = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(uri);
		
		String response = null;
        InputStream inputStream = null;
        try {
            // Execute the call.
            HttpResponse httpResponse = this.httpClient.execute(httpGet, context);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
              System.err.println("Method failed: " + httpResponse.getStatusLine());
            }

            // Read the response body.
            inputStream = httpResponse.getEntity().getContent();
            response = this.inputStreamToString(inputStream);
        }catch (ClientProtocolException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        }catch (IllegalStateException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Closing IS will trigger connection release.
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //TODO do proper exception handling
                    System.err.println("Could not close connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        
        return response;
	}
	
	/**
     * 
     * @param inputStream
     * @return Converted IS or null.
     * @throws IOException
     */
    private String inputStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            }finally {
                inputStream.close();
            }
            return writer.toString();
        } else {       
            return null;
        }
    }

}
