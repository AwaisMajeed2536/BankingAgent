package com.example.saad_kamaal.bankingagent.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saad_kamaal.bankingagent.activities.R;

/**
 * Created by saad_kamaal on 7/9/2017.
 */

public class InvestFragment extends Fragment implements View.OnClickListener {
    private View view;
    private int childCount = 0, stockCount = 0, BondCount = 0, validchk = 0;
    private RadioButton marriedYes, govtJobYes, ownBussYes, beaRiskYes, PurposDependents, investMore1000, investLess1000;
    private RadioButton HaveInvestYes, investLessMoreEarnYes, planChildF, monthProfitYes, longYes;
    private TextView tvEligibility;
    private Button btnEligibility;
    private TextView tvError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.invest_activity_questions, container, false);
        initializeView(view);
        return view;
    }

    public void initializeView(View view) {

        longYes = (RadioButton) view.findViewById(R.id.long_yes);
        tvError = (TextView) view.findViewById(R.id.tvNotEligible);
        btnEligibility = (Button) view.findViewById(R.id.Check_Invest_eligibilty_button);
        marriedYes = (RadioButton) view.findViewById(R.id.married_yes_radio);
        govtJobYes = (RadioButton) view.findViewById(R.id.govtJob_yes_radio);
        ownBussYes = (RadioButton) view.findViewById(R.id.business_yes_radio);
        beaRiskYes = (RadioButton) view.findViewById(R.id.bear_yes_risk);
        PurposDependents = (RadioButton) view.findViewById(R.id.for_depend);
        investLessMoreEarnYes = (RadioButton) view.findViewById(R.id.yes_lessMore_earn);
        planChildF = (RadioButton) view.findViewById(R.id.child_fut_yes);
        HaveInvestYes = (RadioButton) view.findViewById(R.id.yes_invest_before);
        monthProfitYes = (RadioButton) view.findViewById(R.id.yes_month);
        investLess1000 = (RadioButton) view.findViewById(R.id.no_more1000);
        investMore1000 = (RadioButton) view.findViewById(R.id.yes_more1000);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        btnEligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BondCount = childCount = stockCount = 0;
                validchk = 0;
                if (marriedYes.isChecked()) {
                    childCount++;
                    validchk++;
                } else validchk++;

                if (govtJobYes.isChecked()) {
                    BondCount++;
                    validchk++;
                } else validchk++;

                if (ownBussYes.isChecked()) {
                    stockCount++;
                    validchk++;
                } else validchk++;

                if (beaRiskYes.isChecked()) {
                    stockCount++;
                    validchk++;
                } else validchk++;

                if (PurposDependents.isChecked()) {
                    childCount++;
                    validchk++;
                } else validchk++;

                if (longYes.isChecked()) {
                    BondCount++;
                    validchk++;
                } else validchk++;

                if (investLessMoreEarnYes.isChecked()) {
                    stockCount++;
                    validchk++;
                } else validchk++;

                if (planChildF.isChecked()) {
                    childCount++;
                    validchk++;

                } else validchk++;

                if (HaveInvestYes.isChecked()) {
                    BondCount++;
                    validchk++;
                } else validchk++;
                if (monthProfitYes.isChecked()) {
                    childCount++;
                    validchk++;
                } else validchk++;

                if (investMore1000.isChecked()) {
                    BondCount++;
                    validchk++;

                } else {
                    validchk++;
                    stockCount++;
                }

                Decission();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void Decission() {
        String message = "";
        if (validchk == 11) {
            if (stockCount > childCount) {
                if (stockCount > BondCount) {
                    message = "You Should invest in stock market";
                } else if (stockCount < BondCount) {
                    message = "You Should invest in Bonds";
                } else
                    message = "You Should invest in Stock ";
            } else if (stockCount < BondCount) {
                message = "You Should invest in National saving Accounts";
            } else
                message = "You Should invest in stocks";
        } else {
            message = "First fill the choices then Check for Eligibility";
        } ;
        new AlertDialog.Builder(getActivity()).setTitle("Message").setMessage(message)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }
}


