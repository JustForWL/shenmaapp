package com.example.jiaxiaotong.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.R.id;
import com.example.jiaxiaotong.bean.ChatMessageBean;
/*
 * this is a class extends BaseAdapter
 * to make a Customed Android ListView Adapter
 * Can be a WhatsApp ListView form
 * 
 * Here aims to implement Teacher ListView
 * 
 * By Chen John
 */
public class CustomAdapter extends BaseAdapter{
	
	List<ChatMessageBean> chatMessageBean;
	private LayoutInflater layoutInflater;
	
	/* adapter has two info, one is context, 
	 * another is user info
	 *
	 */
	CustomAdapter(Context context, List<ChatMessageBean> chatMessageBean){
		layoutInflater = LayoutInflater.from(context);
		this.chatMessageBean = chatMessageBean;
	}
	
	//The number of list biew
	public int getCount(){
		return chatMessageBean.size();
	}
	
	public Object getItem(int position){
		return chatMessageBean.get(position);
	}
	
	public long getItemId(int position){
		return chatMessageBean.indexOf(getItem(position));
	}
	
	
	//private view holder class
	private class ViewHolder{
		ImageView profile_pic;
		TextView member_name;
		TextView status;
		TextView contactType;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		//LayoutInflater mInflater = (LayoutInflater) context.getSystemService
		//		(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null){
			convertView = layoutInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			
			holder.member_name = (TextView) convertView.findViewById(R.id.name);
			holder.profile_pic = (ImageView) convertView.findViewById(R.id.pic);
			holder.status = (TextView) convertView.findViewById(R.id.sta);
			holder.contactType = (TextView) convertView.findViewById(R.id.contact);
			
			ChatMessageBean TeaUser = chatMessageBean.get(position);
			
			holder.profile_pic.setBackgroundResource(R.drawable.ic_launcher);
			holder.member_name.setText(TeaUser.getFrom());
			holder.status.setText(TeaUser.getLastWords());
			holder.contactType.setText(TeaUser.getDate().toString());
			
			convertView.setTag(holder);
			
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
}
