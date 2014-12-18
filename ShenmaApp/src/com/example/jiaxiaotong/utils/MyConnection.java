package com.example.jiaxiaotong.utils;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

import com.example.jiaxiaotong.constants.App;

/**
 * connect to server 
 * @author arthur
 *
 */
public class MyConnection {
	private static XMPPConnection myConnection = null;
	private static ConnectionConfiguration config = null;
	static{
		config = new ConnectionConfiguration(App.HOST, App.PORT);
		config.setSASLAuthenticationEnabled(false);
	}
	
	public static XMPPConnection getInstance(){
		if(null == myConnection){
			try{
				myConnection = new XMPPConnection(config);
				myConnection.connect();
			} catch(Exception e){
				Logger.i("login server error");
				return null;
			}
		}
		return myConnection;
	}
}
