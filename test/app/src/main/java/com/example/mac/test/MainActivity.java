package com.example.mac.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}
