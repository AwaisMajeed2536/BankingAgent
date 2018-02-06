package com.example.saad_kamaal.bankingagent.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.saad_kamaal.bankingagent.activities.R;
import com.example.saad_kamaal.bankingagent.adapters.SlideAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by saad_kamaal on 7/31/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.bank01,R.drawable.bank2,R.drawable.bank3,R.drawable.bank4,R.drawable.bank5,R.drawable.bank6,R.drawable.bank7,R.drawable.bank8,R.drawable.bank9,R.drawable.bank01,R.drawable.bank11,R.drawable.bank12,R.drawable.bank13};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private Button loanButton;
    private Button investButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        loanButton = (Button) view.findViewById(R.id.loan_button);
        investButton = (Button) view.findViewById(R.id.invest_button);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        loanButton.setOnClickListener(this);
        investButton.setOnClickListener(this);
        setSlider();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loan_button){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoanFragment())
                    .addToBackStack(null).commit();
        } else if(v.getId() == R.id.invest_button){
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new InvestFragment())
                    .addToBackStack(null).commit();
        }
    }

    private void setSlider() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);
        mPager.setAdapter(new SlideAdapter(getActivity(),XMENArray));
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
        },3000, 3000);
    }
}
