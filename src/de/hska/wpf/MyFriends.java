package de.hska.wpf;

import de.hska.wpf.service.WebService;
import de.hska.wpf.service.WebServiceImpl;
import android.app.Activity;
import android.os.Bundle;

public class MyFriends extends Activity {
	
	private WebService webService;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        webService = WebServiceImpl.getInstance();
        
    }
}