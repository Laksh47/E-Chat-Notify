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
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText et1,et2;
	TextView tv;
	String line="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText)findViewById(R.id.editText1);
		et2=(EditText)findViewById(R.id.editText2);
		tv=(TextView)findViewById(R.id.textView3);
	}
	
	public void signin(View c)
	{
		//Toast.makeText(MainActivity.this, "I am clicked",Toast.LENGTH_SHORT).show();
		//tv.setText(tv.getText()+" I am clicked");
		String s=dobgcheck();
		if(!s.equals("fail"))
        {
			tv.setText(" I am exec4");
        	Intent in=new Intent(MainActivity.this,SuccessActivity.class);
        	//in.putExtra("name", s);
        	tv.setText(" I am exec5");
        	in.putExtra("uname", ""+et1.getText().toString());
        	tv.setText(" I am exec6");
        	startActivity(in);        	
        	//startActivity(new Intent(this, Success.class));
        }
        else
        {
        	UpdateGUI("Invalid Username or password");
        }
	}
	
	String dobgcheck()
	{
		String res="";
		String s1=""+et1.getText();
		String s2=""+et2.getText();
		//Toast.makeText(MainActivity.this, "I am in bg,assigned",Toast.LENGTH_SHORT).show();
		//tv.setText(tv.getText()+" I am here");
		tv.setText(tv.getText()+s1+s2);
		HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://10.0.2.2/Web/login.php");
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("val", s1.toString()));
        nameValuePairs.add(new BasicNameValuePair("vall",s2.toString()));
        try 
    	{        
        	//tv.setText(tv.getText()+" I am there");
        	//Toast.makeText(MainActivity.this, "I am in",Toast.LENGTH_SHORT).show();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            tv.setText(tv.getText()+" I am exec1");
    	    HttpResponse response = httpclient.execute(httppost);
    	    tv.setText(tv.getText()+" I am exec2");
    	    //Toast.makeText(MainActivity.this, "I am executed",Toast.LENGTH_SHORT).show();
    	    if(response != null) 
    	    {
    	    	InputStream inputstream = response.getEntity().getContent();
    	        line = convertStreamToString(inputstream);
    	        tv.setText(" I am exec3");
    	        //Toast.makeText(MainActivity.this, "I am converted",Toast.LENGTH_SHORT).show();
    	        JSONObject jObj = new JSONObject(line);
                res = jObj.getString("result");                
    	    }
    	    else 
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
    		Toast.makeText(MainActivity.this, ""+e.toString(),Toast.LENGTH_LONG).show();
    		UpdateGUI("IOException"+e.toString());
    	} 
    	catch (Exception e) 
    	{
    		UpdateGUI("Exception");
    	}
        return res;
	}
	
	void UpdateGUI(String a)
	{
		tv.setText(tv.getText()+" Info:"+a);
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
		//Toast.makeText(MainActivity.this, "I am successfully streamed",Toast.LENGTH_SHORT).show();
		tv.setText(tv.getText()+"Hey in stream");
		return total.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
