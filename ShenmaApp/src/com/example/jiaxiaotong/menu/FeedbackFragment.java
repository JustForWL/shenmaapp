package com.example.jiaxiaotong.menu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.R.id;
import com.example.jiaxiaotong.R.layout;
import com.example.jiaxiaotong.R.menu;
import com.example.jiaxiaotong.utils.Logger;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class FeedbackFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == android.R.id.home){
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.feedback_fragement,
					container, false);
			
			Button feedback_btn = (Button) rootView.findViewById(R.id.sendfeedback_btn);
			final EditText feedback_text = (EditText) rootView.findViewById(R.id.feed_back_text);
			feedback_btn.setOnClickListener(new OnClickListener(){
				public void onClick(View view){
					Thread newthread = new Thread(new Runnable(){
						public void run(){
							try{
								String strUrl = "http://192.168.1.200:6011/AndroidMessage";
								URL url = null;
								try{
									url = new URL(strUrl);
									HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
									String feedbackText = feedback_text.getText().toString();
									Logger.i(feedbackText);
									HttpEntity requestHttpEntity = new StringEntity(feedbackText);
									HttpPost httpPost = new HttpPost(strUrl);
									httpPost.setEntity(requestHttpEntity);
									HttpClient httpClient = new DefaultHttpClient();
									HttpResponse httpResponse = httpClient.execute(httpPost);
									Logger.i(httpResponse.toString());
									getActivity().finish();
								}catch(MalformedURLException e){
									e.printStackTrace();
								}catch(IOException e){
									e.printStackTrace();
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}	
					});
					newthread.start();
				}
			});
			return rootView;
		}
	}
}
