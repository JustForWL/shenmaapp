package com.example.jiaxiaotong;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ParentFrame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_frame);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab tab = actionBar
                .newTab()
                .setText(R.string.Infos)
                .setTabListener(new TabListener<InfoFragment>(this, "公告信息",
                        InfoFragment.class));
        actionBar.addTab(tab);
        tab = actionBar
                .newTab()
                .setText(R.string.stu_class)
                .setTabListener(new TabListener<StuFragment>(this, "班级学生",
                        StuFragment.class));
        actionBar.addTab(tab);
        tab = actionBar
                .newTab()
                .setText(R.string.tcher_info)
                .setTabListener(new TabListener<TchFragment>(this, "教师信息",
                        TchFragment.class));
        actionBar.addTab(tab);
        tab = actionBar
                .newTab()
                .setText(R.string.my_kid)
                .setTabListener(new TabListener<KidFragment>(this, "我的孩子",
                        KidFragment.class));
        actionBar.addTab(tab);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prime_frame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_refresh:

                return true;
            case R.id.action_search:

                return true;

        }

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
