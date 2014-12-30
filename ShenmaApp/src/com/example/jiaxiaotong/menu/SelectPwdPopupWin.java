package com.example.jiaxiaotong.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.jiaxiaotong.R;
public class SelectPwdPopupWin extends PopupWindow{
	private EditText prev_Pwd;
	private EditText cur_Pwd;
	private EditText confirm_Pwd;
	private Button Pwd_Change;
	private Button Pwd_Reset;
	
	private View mMenuView;
	
	public SelectPwdPopupWin(Activity pwdActivity){
		super(pwdActivity);
		LayoutInflater inflater = (LayoutInflater) pwdActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.custom_popup_window, null);
		prev_Pwd = (EditText) mMenuView.findViewById(R.id.prev_Pwd);
		cur_Pwd = (EditText) mMenuView.findViewById(R.id.cur_Pwd);
		confirm_Pwd = (EditText) mMenuView.findViewById(R.id.confirm_Pwd);
		Pwd_Change = (Button) mMenuView.findViewById(R.id.Pwd_Change);
		Pwd_Reset = (Button) mMenuView.findViewById(R.id.Pwd_Reset);
		Pwd_Change.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Pwd_Reset.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		// set popup window view
		this.setContentView(mMenuView);
		//set popup window width
		this.setWidth(LayoutParams.FILL_PARENT);
		//set popup window height
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//set popup window focusable
		this.setFocusable(true);
		//set popup window animation
		this.setAnimationStyle(R.style.AnimBottom);
		// set color to transculent
		ColorDrawable cd = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(cd);
		mMenuView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// if touch is out of the frame,
				// dismiss the frame
				int height = mMenuView.findViewById(R.id.pop_layout).getHeight();
				int y=(int) event.getY();
				if(event.getAction() == MotionEvent.ACTION_UP){
					if(y < height){
						dismiss();
					}
				}
				return false;
			}
			
		});
	}
}
