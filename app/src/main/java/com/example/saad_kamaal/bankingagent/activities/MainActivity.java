package com.example.saad_kamaal.bankingagent.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.saad_kamaal.bankingagent.adapters.SlideAdapter;
import com.example.saad_kamaal.bankingagent.fragments.HomeFragment;
import com.example.saad_kamaal.bankingagent.fragments.InvestFragment;
import com.example.saad_kamaal.bankingagent.fragments.LoanFragment;
import com.example.saad_kamaal.bankingagent.fragments.StatsGraphFragment;
import com.example.saad_kamaal.bankingagent.helper.UtilHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.bank01,R.drawable.bank2,R.drawable.bank3,R.drawable.bank4,R.drawable.bank5,R.drawable.bank6,R.drawable.bank7,R.drawable.bank8,R.drawable.bank9,R.drawable.bank10,R.drawable.bank11,R.drawable.bank12,R.drawable.bank13};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        setSupportActionBar(toolbar);
//        findViewById(R.id.loan_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,LoanFragment.class);
//                startActivity(intent);
//            }
//        });
//        findViewById(R.id.invest_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,InvestFragment.class);
//                startActivity(intent);
//            }
//        });
//        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_loan) {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoanFragment()).commit();
        } else if (id == R.id.nav_invest) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InvestFragment()).commit();
        } else if (id == R.id.nav_Stats) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsGraphFragment()).commit();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        } else if (id  == R.id.nav_chatter){
            startActivity(new Intent(this, ChatterBoxActivity.class));
        } else if (id == R.id.nav_logout){
            UtilHelper.endLoginSessiong(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(MainActivity.this,XMENArray));
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    public void ReplaceFragment(Fragment frag){

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).addToBackStack("").commit();
    }
}
