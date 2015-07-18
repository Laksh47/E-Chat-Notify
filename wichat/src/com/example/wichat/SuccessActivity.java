package com.example.wichat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SuccessActivity extends Activity {
	TextView tv,inf;
	EditText et1;
	String uname="";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		tv=(TextView)findViewById(R.id.textView4);
		inf=(TextView)findViewById(R.id.textView5);
		et1=(EditText)findViewById(R.id.editText3);
		Bundle bundle = getIntent().getExtras();
	    uname=bundle.getString("uname");
	    tv.setText(uname);
	}

	public void notify(View clickedbutton)
	{
		String res="",line="";
		String msg=""+et1.getText();
		HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://10.0.2.2/Web/send.php");
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("uname",uname));
        nameValuePairs.add(new BasicNameValuePair("msg", msg));
        
        try 
    	{
        
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	    HttpResponse response = httpclient.execute(httppost);
    	    if(response != null) 
    	    {
    	    	InputStream inputstream = response.getEntity().getContent();
    	        line = convertStreamToString(inputstream);
    	        JSONObject jObj = new JSONObject(line);
                res = jObj.getString("result");
                UpdateGUI(res);
    	    } else 
    	    {
    	        UpdateGUI("Unable to process!");
    	    }
    	
    	} 
    	catch (ClientProtocolException e) 
    	{
    		UpdateGUI("client protocol exc");
    	}
    	catch (IOException e) 
    	{
    		UpdateGUI("IOException");
    	} 
    	catch (Exception e) 
    	{
    		UpdateGUI("Exception");
    	}
	}
	
	void UpdateGUI(String a)
	{
		inf.setText("Info:"+a);
	}
	private String convertStreamToString(InputStream is) 
	{
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try 
		{
			while ((line = rd.readLine()) != null) 
			{
				total.append(line);
			}
		} 
		catch (Exception e) 
		{
			UpdateGUI("Stream conversion Exception");
		}
		return total.toString();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.success, menu);
		return true;
	}

}
