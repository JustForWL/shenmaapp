package com.example.jiaxiaotong.activity;
import java.util.ArrayList;
import java.util.Date;

import com.example.jiaxiaotong.R;

import android.R.id;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jiaxiaotong.bean.ChatMessageBean;
import com.example.jiaxiaotong.utils.SharePreferencesUtil;
import com.example.jiaxiaotong.utils.Util;
/**
 * 聊天界面
 * @author Arthur
 *
 */
public class ChatActivity extends Activity{
	private TextView talkTo = null;
	private static final String TALK = "talkTo";
	private static final String ACCOUNT = "account";
	private ListView messageView = null;
	private MessageAdapter messageAdapter = null;
	private ArrayList<ChatMessageBean> messages = null;
	private Menu menu;
	private String talktoacc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActionBar actionBar = getActionBar();
		actionBar.show();
		actionBar.setHomeButtonEnabled(true);
		setContentView(R.layout.activity_chat);
		//setContentView(R.layout.chatting_item_msg_text_left);
		//test();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.chat, menu);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			String talk = bundle.getString(TALK);
			String account = bundle.getString(ACCOUNT);
			//talkTo.setText(talk);
			MenuItem Chat2Item = menu.findItem(R.id.chat_name);
			Chat2Item.setTitle(talk);
			talktoacc = talk;
		}
		return super.onCreateOptionsMenu(menu);
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initView(Menu menu) {
//		talkTo = (TextView) findViewById(R.id.talkTo);

		
	}

	private void test() {
		TextView tv_sendTime = (TextView) findViewById(R.id.tv_sendtime);
		ImageView iv_userhead = (ImageView) findViewById(R.id.iv_userhead);
		TextView tv_content = (TextView) findViewById(R.id.tv_chatcontent);
		TextView tv_username = (TextView) findViewById(R.id.tv_username);
		ChatMessageBean message = new ChatMessageBean();
		tv_sendTime.setText(Util.formatDate(new Date()));
		//iv_userhead.setBackground(Util.getBackground("ic_launcher.png"));
		tv_content.setText("大家好,我是名侦探毛利小五郎，我非常喜欢喝啤酒。");
		tv_username.setText("毛利小五郎");
	}
	
	class MessageAdapter extends BaseAdapter {
        private ArrayList<ChatMessageBean> messages = null;
		private LayoutInflater layoutInflater = null;
		private Context context = null;
		private int COME = 1; //发送的消息
		private int TO = 0; //接收的消息
		
		public MessageAdapter(Context context, ArrayList<ChatMessageBean> messages) {
			this.messages = messages;
			this.layoutInflater = LayoutInflater.from(context);
			this.context = context;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.messages.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return this.messages.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		
		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			ChatMessageBean message = this.messages.get(position);
			if(message.getFrom().equals(SharePreferencesUtil.readLoginAccount(context))){
				return TO;
			}else {
				return COME;
			}
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ChatMessageBean message = messages.get(position);
			ViewHolder viewHolder = null;
			if(null == convertView) {
				if(message.getFrom().equals(SharePreferencesUtil.readLoginAccount(context))){ //当前登录账号发送的消息显示在右方
					convertView = this.layoutInflater.inflate(
							R.layout.chatting_item_msg_text_right, null);
				}else { //发送给当前登录账号的消息显示在左方
					convertView = this.layoutInflater.inflate(R.layout.chatting_item_msg_text_left, null);
				}
				viewHolder = new ViewHolder();
				viewHolder.sendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
				viewHolder.userHead = (ImageView) convertView.findViewById(R.id.iv_userhead);
				viewHolder.content = (TextView) convertView.findViewById(R.id.tv_chatcontent);
				viewHolder.userName = (TextView) convertView.findViewById(R.id.tv_username);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
				viewHolder.sendTime.setText(Util.formatDate(message.getDate()));
				viewHolder.content.setText(message.getContent());
				viewHolder.userName.setText(message.getFrom());
				viewHolder.userHead.setBackground(Util.getBackground(message.getFrom()+".png"));
			}
			return convertView;
		}
		
		
	}
	
	static class ViewHolder {
		public TextView sendTime;
		public ImageView userHead;
		public TextView content;
		public TextView userName;
	}
	
}
