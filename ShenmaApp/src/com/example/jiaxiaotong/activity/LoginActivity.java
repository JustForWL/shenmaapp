package com.example.jiaxiaotong.activity;

import org.jivesoftware.smack.XMPPConnection;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.constants.AllTypes;
import com.example.jiaxiaotong.constants.App;
import com.example.jiaxiaotong.utils.LoadingDialog;
import com.example.jiaxiaotong.utils.Logger;
import com.example.jiaxiaotong.utils.MyConnection;
import com.example.jiaxiaotong.utils.NetUtil;
import com.example.jiaxiaotong.utils.SharePreferencesUtil;
import com.example.jiaxiaotong.utils.T;


import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends BaseActivity implements OnClickListener{
	
	private TextView userName = null;
	private TextView userPass = null;
	private Button loginBtn = null;
	private LoadingDialog loadingDialog = null;
	private Handler handler = new Handler();
	private Runnable connectionTimeoutCallBack = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (loadingDialog != null) {
				loadingDialog.dismiss();
			}
			T.showShort(LoginActivity.this, "登录超时，请重试");
		}
		
	};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
    }
	
	public void initView() {
		userName = (TextView) findViewById(R.id.login_user_edit);
		userPass = (TextView) findViewById(R.id.login_passwd_edit);
		loginBtn = (Button) findViewById(R.id.login_login_btn);
		loginBtn.setOnClickListener(this);
		loadingDialog = new LoadingDialog();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String userAccount = userName.getText().toString();
		String userPassword = userPass.getText().toString();
		if(userAccount.equals("") || userPassword.equals("")){
			T.showLong(this, "用户名或密码不能为空");
			return;
		}else{
			if(!NetUtil.isNetConnected(this)){
				T.showShort(this, R.string.net_disconnect_hint);
				return;
			}
			handler.postDelayed(connectionTimeoutCallBack, App.TIME_DELAY_LOADING);
			loadingDialog.show(getFragmentManager(), "登录中");
			loadingDialog.setCancelable(false);
			XMPPConnection myConnection = MyConnection.getInstance();
			try{
				myConnection.login(userAccount, userPassword);
				SharePreferencesUtil.writeIsFirstLogin(this);
				SharePreferencesUtil.writeLoginAccount(this, userAccount);
				if(userAccount.startsWith("p")) {
					openActivity(ParentFrame.class);
					SharePreferencesUtil.writeLoginType(this, AllTypes.PARENTS.toString());
				}else if(userAccount.startsWith("t")) {
					openActivity(TeacherFrame.class);
					SharePreferencesUtil.writeLoginType(this, AllTypes.TEACHERS.toString());
				}else if(userAccount.startsWith("h")){
					openActivity(HeadTeacherFrame.class);
					SharePreferencesUtil.writeLoginType(this, AllTypes.HEADETEACHER.toString());
				}else{
					T.showLong(this, "无效的用户名");
					return;
				}
				this.finish();
			} catch(Exception e){
				Logger.i("登录失败");
				return;
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
}
