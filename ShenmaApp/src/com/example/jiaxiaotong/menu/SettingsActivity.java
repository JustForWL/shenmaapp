package com.example.jiaxiaotong.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.R.id;
import com.example.jiaxiaotong.R.layout;
import com.example.jiaxiaotong.R.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SettingsActivity extends Activity {
	
	private Button takePicBtn;
	private Button choosePicBtn;
	private ImageView accountPic;
	private static final int SELECT_PICTURE = 3;
	private Button ChgPwdMainBtn;

	private Uri imageUri;
	protected SelectPwdPopupWin menuWindow;
	private static final int TAKE_PHOTO = 1;
	private static final int CROP_PHOTO = 2;
	private View pwd_view;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		takePicBtn = (Button) findViewById(R.id.takePicBtn);
		accountPic = (ImageView) findViewById(R.id.accountPic);
		String acctPicSavePath = Environment.getExternalStorageDirectory() + "/acctPic/head.png";
		File acctPicImgSave = new File(acctPicSavePath);
		if(acctPicImgSave.exists()){
			Bitmap acctPicSaveBit = BitmapFactory.decodeFile(acctPicSavePath);
			Drawable acctPicdrawable = new BitmapDrawable(acctPicSaveBit);
			accountPic.setImageDrawable(acctPicdrawable);
		}else{
			Drawable pictmp = getResources().getDrawable(R.drawable.mini_avatar_shadow);
			accountPic.setImageDrawable(pictmp);
		}
		choosePicBtn = (Button) findViewById(R.id.chsPicBtn);
		pwd_view = (View)this.findViewById(R.id.pwd_view); 
		takePicBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// This is the place where lick button can 
				// take a picture
				//
				//Here mainly for create a file object, to store the taken picture
				
				File outputImage = new File(Environment.getExternalStorageDirectory(), "takenImage.jpg");
				try{
					if(outputImage.exists()){
						outputImage.delete();
					}
					outputImage.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
				
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media,action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
				
			}
			
		});
		choosePicBtn.setOnClickListener(new OnClickListener(){


			@Override
			public void onClick(View arg0) {
				// This is for changing the
				// press choose image button, and can browse the gallery
				//Goodluck...
				// This is where to store the chosen image
				Intent intent = new Intent(Intent.ACTION_PICK, 
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				//intent.putExtra("return-data", true);
				//intent.putExtra("scale", true);
				//intent.putExtra(MediaStore.EXTRA_OUTPUT, SELECT_PICTURE);
				startActivityForResult(Intent.createChooser(intent, "SelectImage"),  SELECT_PICTURE);
				
			}
			
		});
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//Here I can set a password change button
		ChgPwdMainBtn = (Button) this.findViewById(R.id.chgPwdMainBtn);
		ChgPwdMainBtn.setOnClickListener(new OnClickListener(){


			@Override
			public void onClick(View v) {
				// What can I do if I press
				// Password Change Button
				View pwdview = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.activity_settings, null);
				menuWindow = new SelectPwdPopupWin(SettingsActivity.this);
				menuWindow.showAsDropDown(pwd_view);
				//menuWindow.showAtLocation(pwdview, 
				//		Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				
			}
			
		});
	}
	
	
	//This method is used for determining how the activity response to the request code
	//1 is for take picture
	//2 is for crop picture
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch (requestCode){
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);
			}
			break;
		case CROP_PHOTO :
			try{
				Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
				accountPic.setImageBitmap(bitmap);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			break;
		case SELECT_PICTURE:
			Uri selectedImageUri = data.getData();
			String tempPath = getPath(selectedImageUri, SettingsActivity.this);
			Bitmap bm;
			BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
			bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
			accountPic.setImageBitmap(bm);
			String saveDir = Environment.getExternalStorageDirectory() + "/acctPic/head.png";
			FileOutputStream acctPic = null;
			File save = new File(saveDir);
			try{
				if(save.exists()){
					save.delete();
				}
				save.createNewFile();
				acctPic = new FileOutputStream(save, false);
				bm.compress(Bitmap.CompressFormat.PNG, 100, acctPic);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		default:
			break;	
		}
	}

	private String getPath(Uri uri,
			SettingsActivity settingsActivity) {
		// TODO SettingsActivity, get image data index
		
		String[] Projection = {MediaColumns.DATA};
		Cursor cursor = getContentResolver().query(uri, Projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
		}else if (id == android.R.id.home){
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
