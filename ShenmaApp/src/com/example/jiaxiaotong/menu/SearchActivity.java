package com.example.jiaxiaotong.menu;

import java.util.ArrayList;

import com.example.jiaxiaotong.R;
import com.example.jiaxiaotong.R.id;
import com.example.jiaxiaotong.R.layout;
import com.example.jiaxiaotong.R.menu;
import com.example.jiaxiaotong.activity.ChatActivity;
import com.example.jiaxiaotong.utils.Logger;
import com.example.jiaxiaotong.utils.T;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener, OnItemClickListener {
	
    //Here comes definition for search
    private ListView listView;
    private Object[] names;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> namelist = new ArrayList<String>();
    private SearchView searchView;
    
    
    public Object[] loadData() {  
        namelist.add("aa");  
        namelist.add("ddfa");  
        namelist.add("qw");  
        namelist.add("sd");  
        namelist.add("fd");  
        namelist.add("cf");  
        namelist.add("re");  
        return namelist.toArray();  
    } 

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initActionBar();
        names = loadData();
        listView = (ListView) findViewById(R.id.searchlist);
        listView.setAdapter(new ArrayAdapter<Object>(getApplicationContext(), 
        		android.R.layout.simple_expandable_list_item_1, names));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener((OnItemClickListener) this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
	}

	private void initActionBar() {
		// Set my ActionBar
        getActionBar().setDisplayShowHomeEnabled(true);  
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);  
        getActionBar().setDisplayShowCustomEnabled(true);  
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View mTitleView = mInflater.inflate(R.layout.custom_search_action,  
                null);  
        getActionBar().setCustomView(  
                mTitleView,  
                new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT,  
                        LayoutParams.WRAP_CONTENT));  
        searchView = (SearchView) mTitleView.findViewById(R.id.search_view); 
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		
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

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(newText)){
			listView.clearTextFilter();
		}else{
			listView.setFilterText(newText.toString());
		}
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Logger.i(query);
		return false;
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		String test = names[position].toString();
		int positiont = (int)id;
		Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
		Bundle bundle = new Bundle();
		bundle.putString("talkto", (String) test);
		Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);

	}
}
