package com.smartconnect.applicationlist.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.smartconnect.applicationlist.Adapter.TabAdapter;
import com.smartconnect.applicationlist.Database.LocationDB;
import com.smartconnect.applicationlist.Fragments.AnalyticsFragment;
import com.smartconnect.applicationlist.Fragments.ApplicationFragment;
import com.smartconnect.applicationlist.Fragments.SystemInstalledappFragment;
import com.smartconnect.applicationlist.Fragments.UserInstalledappFragment;
import com.smartconnect.applicationlist.R;
import com.smartconnect.applicationlist.Service.SocketConnectionService;
import com.smartconnect.applicationlist.Service.SocketService;

import java.io.File;
import java.io.IOException;

public class MainActivity  extends AppCompatActivity {
Context context;
    final SocketService mSocketservice = new SocketService();

    BottomNavigationView bottomNavigationView;
    Context con=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SocketConnectionService.instance().Expermemnt(this);
//        SocketService.instance().Expermemnt(this);
//        if(SocketService.instance().isConnected == true){
//            Toast.makeText(MainActivity.this,"Socket  connected",Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(MainActivity.this,"Socket not able connect",Toast.LENGTH_SHORT).show();
//        }
       // mSocketservice.stream.connect();
      //  SocketService.instance().startService(Sock);


        //  toolbar = getSupportActionBar();
        bottomNavigationView= findViewById(R.id.navigation);

        // load the store fragment by default
//        toolbar.setTitle("Shop");
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new ApplicationFragment());
        //database

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_analytics:
                    fragment = new AnalyticsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_app:
                    fragment = new ApplicationFragment();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    private boolean loadFragment(Fragment fragment) {
        // load fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}