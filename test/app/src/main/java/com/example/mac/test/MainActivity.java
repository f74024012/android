package com.example.mac.test;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.mac.test.R.drawable.ic_drawer;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView lstDrawer;
    private String[] drawer_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = new FragmentA();
        fm.beginTransaction().replace(R.id.content, f).commit();

        lstDrawer=(ListView)findViewById(R.id.lv_drawer);
        drawer_menu=this.getResources().getStringArray(R.array.side);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.drawer_list_item,drawer_menu);
        lstDrawer.setAdapter(adapter);
        lstDrawer.setOnItemClickListener(listener);


    }

    private ListView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    };

    private void selectItem(int position){
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = null;
        switch (position){
            case 0:
                f = new FragmentA();
                fm.beginTransaction().replace(R.id.content, f).commit();
                break;
            case 1:
                f = new FragmentB();
                fm.beginTransaction().replace(R.id.content, f).commit();
                break;
        }
        lstDrawer.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(lstDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }
}
