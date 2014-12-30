package com.example.jiaxiaotong.menu;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;

import com.example.jiaxiaotong.utils.Logger;

public class onRefreshItemSelected {
	
	private static String Url = "http://192.168.1.200:8000/AndroidMessage";
	
	public static void RefreshAction(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(Url);
					List<NameValuePair> postmsg = new ArrayList<NameValuePair>();
					postmsg.add(new BasicNameValuePair("pair", "HttpClient_post"));
					HttpEntity httpEntity = new UrlEncodedFormEntity(postmsg, "utf-8");
					httpPost.setEntity(httpEntity);
					Logger.i(postmsg.toString());
					HttpResponse httpResponse = httpClient.execute(httpPost);
					Logger.i(httpResponse.getStatusLine().toString());
					Logger.i(httpResponse.getStatusLine().getStatusCode() + "");
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || 
							httpResponse.getStatusLine().getStatusCode() == 201){
						String strResult = EntityUtils.toString(httpResponse.getEntity());
						Logger.i(strResult);
					}
					else{
						Logger.i("There is a big mistake");
					}
					
				}catch(ClientProtocolException e){
					Logger.i(e.getMessage().toString());
				}catch(IOException e){
					Logger.i(e.getMessage().toString());
				}catch(Exception e){
					Logger.i(e.getMessage().toString());
				}
			}
			
		}).start();;
	}
}

