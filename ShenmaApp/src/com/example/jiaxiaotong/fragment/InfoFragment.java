package com.example.jiaxiaotong.fragment;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.activity.ChatActivity;
import com.example.jiaxiaotong.bean.ChatMessageBean;
import com.example.jiaxiaotong.dao.ChatMessageDB;

/**
 * Created by John on 12/15/2014.
 */
public class InfoFragment extends Fragment {
	private View view;
	private ChatMessageDB messageDB;
	private ArrayList<ChatMessageBean> Messages;
	private CustomAdapter messageAdapter;
	private ListView message_view;
	private Date date = new Date(2014, 12, 21);
	private Context context;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.activity_info_fragment, container, false);
		message_view = (ListView) view.findViewById(R.id.info_listview);
		messageDB = new ChatMessageDB(getActivity());
		Messages = new ArrayList<ChatMessageBean>();
		//test();
		for(int i = 0; i < 6; i++){
			ChatMessageBean message = new ChatMessageBean();
			message.setContent("this is a test");
			message.setDate(date);
			message.setFrom("from");
			message.setFromAccount("from Account");
			message.setIsMulticast(1);
			message.setLastWords("this is a test");
			message.setTo("To");
			message.setToAccount("toAccount");
			message.setFrom_group("from_group");
			message.setIsRead(0);
			Messages.add(message);
		}
		CustomAdapter customAdapter = new CustomAdapter(this.getActivity(), Messages);
		message_view.setAdapter(customAdapter);
		message_view.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
				String member_position = Messages.get(position).toString();
				Toast.makeText(getActivity(), "hehe"  + member_position, Toast.LENGTH_LONG).show();
				Bundle bundle = new Bundle();
				bundle.putString("talkto", (String) Messages.get(position).getToAccount());
				bundle.putString("account", Messages.get(position).getFromAccount());
				Intent intent = new Intent(getActivity(), ChatActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		return view;
	}
	private void initData(){
		Messages = messageDB.getChatMessages();
//		messageAdapter = new CustomAdapter(this.getActivity(), Messages);
		message_view.setAdapter(messageAdapter);
	}
	
	private void test(){
		@SuppressWarnings("deprecation")
		Date date = new Date(2014,12,24);
		ChatMessageBean c1 = new ChatMessageBean();

		@SuppressWarnings("deprecation")
		ChatMessageBean c2 = new ChatMessageBean();
		c2.setContent("this is a test2");
		c2.setDate(date);
		c2.setFrom("from2");
		c2.setFromAccount("from Account2");
		c2.setIsMulticast(1);
		c2.setLastWords("this is a test2");
		c2.setTo("To2");
		c2.setToAccount("toAccount2");
		c2.setFrom_group("from_group");
		c2.setIsRead(0);	
		}

}
