package com.example.saad_kamaal.bankingagent.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saad_kamaal.bankingagent.activities.R;

/**
 * Created by saad_kamaal on 7/5/2017.
 */

public class PersonalLoanFragment extends Fragment {
    private View view;
    private Button EligibilityChk;
    private TextView tvIncome,tvError,tvAge,tvLoanAmount,tvTenure;
    private RadioButton govtEmployee;
    private RadioButton commisionedNo;
    private RadioButton armedEmployee;
    private RadioButton privateEmployee;
    private RadioButton bps17Yes,bps17no,sixMonthYes,sixMonthNo,threeMonthNo,OneYearNO;
    private RadioButton commisionedYes;
    private RadioButton SelfEmployeed,SalariedEmployee;
    private RadioButton cincNo,dualNationalityYes,dualNationalityNo;
    private RadioGroup rgEmployementSelection,rgBps17,rgCommissionedOfficer,rgSixMonthService,rgPrivateSelection,rgOneYearService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_personal_loan, container,false);
        initView();
        return  view;
    }

    public void initView(){

                //getSupportActionBar().setTitle("Personal Loan");
        tvAge=(TextView) view.findViewById(R.id.tvAge);
        tvIncome = (TextView) view.findViewById(R.id.tvIncome);
        tvTenure = (TextView) view.findViewById(R.id.tvTenure);
        tvError = (TextView) view.findViewById(R.id.tvNotEligible);
        tvLoanAmount = (TextView) view.findViewById(R.id.tvLoanAmount);
        cincNo = (RadioButton) view.findViewById(R.id.cnic_no_radio);
        dualNationalityYes = (RadioButton) view.findViewById(R.id.dual_nationality_yes);
        govtEmployee = (RadioButton) view.findViewById(R.id.govt_employee_radio);
        armedEmployee = (RadioButton) view.findViewById(R.id.armed_employee_radio);
        privateEmployee = (RadioButton) view.findViewById(R.id.private_employee_radio);
        bps17Yes = (RadioButton) view.findViewById(R.id.bps_17_yes_radio);
        commisionedYes = (RadioButton)  view.findViewById(R.id.commissioned_officer_yes_radio);
        commisionedNo = (RadioButton)  view.findViewById(R.id.commissioned_officer_no_radio);
        SalariedEmployee = (RadioButton) view.findViewById(R.id.salaried_radio);
        SelfEmployeed = (RadioButton) view.findViewById(R.id.self_employeed_radio);
        dualNationalityNo = (RadioButton) view.findViewById(R.id.dual_nationality_no);
        bps17no = (RadioButton) view.findViewById(R.id.bps_17_no_radio);
        sixMonthYes = (RadioButton) view.findViewById(R.id.bps_17_6_month_yes_radio);
        sixMonthNo =  (RadioButton) view.findViewById(R.id.bps_17_6_month_no_radio);
        threeMonthNo = (RadioButton) view.findViewById(R.id.Three_month_no);
        OneYearNO = (RadioButton) view.findViewById(R.id.one_year_relation_no_radio);
        EligibilityChk = (Button) view.findViewById(R.id.Check_Personal_eligibilty_button);



        EligibilityChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double half_pay = Integer.parseInt(tvIncome.getText().toString().trim());
                int tenureDuration = Integer.parseInt(tvTenure.getText().toString().trim());
                int numberOinstallment = tenureDuration*12;
                half_pay/=2.0;
                double interest = 0;//0.0183,0.022,0.0067,0.0083
                if(SelfEmployeed.isChecked())
                    interest = 0.0183;
                else if (govtEmployee.isChecked()) {
                    interest=0.0183;

                }

                else if(SalariedEmployee.isChecked())
                    interest = 0.022;
                else if(armedEmployee.isChecked()) {
                    if (tenureDuration > 3)
                        interest = 0.0083;
                    else interest=0.0067;
                }

                int loanAmount = Integer.parseInt(tvLoanAmount.getText().toString().trim());
                double x = Math.pow((1+interest),numberOinstallment) ;
                double xx =interest*x;
                double yy = (1+interest);
                 //(interest*(1+interest),numberOinstallment);
                double y = (Math.pow (yy,(numberOinstallment))-1);   //((1+interest),(numberOinstallment-1));
                double monthlyInstall= (xx/y)*loanAmount;
               // double monthlyInstall =loanAmount*(Math.pow(interest*(1+interest),numberOinstallment)/(Math.pow((1+interest),(numberOinstallment-1))));
                if(half_pay<monthlyInstall) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("You are not eligible because your income half is less than installment");
                }
                else {
                    tvError.setText("");
                    Toast.makeText(getActivity(), "Eligible for The Loan \n your installment is : " + monthlyInstall, Toast.LENGTH_SHORT).show();

                }
            }
        });


        cincNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Error, You must be CNIC holder");
                }
                else{
                    tvError.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        dualNationalityYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Error, Dual nationality is not allowed");
                } else {
                    tvError.setVisibility(View.GONE);
                }
            }
        });

        dualNationalityNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.employment_selection_layout).setVisibility(View.VISIBLE);
                }
            }
        });

        sixMonthNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Must have done 6 month service");
                }
            }
        });

        threeMonthNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Must have 3 month Relation With Bank");
                }
            }
        });

        OneYearNO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Must have 1 year Relation with Bank");
                }
            }
        });


        tvAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !(tvAge.getText().toString().isEmpty())) {
                    int age = Integer.parseInt(tvAge.getText().toString().trim());
                    if (age < 22 || age > 60) {
                        tvAge.setError("Error! Age must be between 21 and 60");
                    }
                }

            }

        });

        tvLoanAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(tvLoanAmount.getText().toString().isEmpty())) {
                    int amount = Integer.parseInt(tvLoanAmount.getText().toString().trim());
                    String stAmount = tvLoanAmount.getText().toString().trim();
                    if (amount < 50000 || amount > 500000 || stAmount.equals("")) {
                        tvLoanAmount.setError("Error! Loan Amount Must be between 50000 and 500000");
                    }
                }
            }
        });


        tvTenure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(tvTenure.getText().toString().isEmpty())) {
                    int duration = Integer.parseInt(tvTenure.getText().toString().trim());
                    String stDuration = tvTenure.getText().toString().trim();
                    if (duration < 1 || duration > 5 || stDuration.equals("")) {
                        tvTenure.setError("Duration must be between 1 and 5");
                    }
                }
            }
        });




        privateEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.private_selection_layout).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.private_selection_layout).setVisibility(View.GONE);

                }
            }

        });
        govtEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.one_year_relation_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.Three_month_relation_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.bps_17_layout).setVisibility(View.VISIBLE);

                } else {
                    view.findViewById(R.id.bps_17_layout).setVisibility(View.GONE);
                }
            }
        });

        armedEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                    view.findViewById(R.id.commissioned_officer_layout).setVisibility(View.VISIBLE);
                    int income = Integer.parseInt(tvIncome.getText().toString().trim());
                    if (income < 25000) {
                        tvIncome.setError("Error! Minimum 25000 income Require For Army");
                    }
                } else {
                    view.findViewById(R.id.commissioned_officer_layout).setVisibility(View.GONE);
                }
            }
        });


        bps17Yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.six_months_service).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
            }
        });

        bps17no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Must be a BPS-17");
                }
            }
        });

        commisionedYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    view.findViewById(R.id.commissioned_officer_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
            }
        });



        commisionedNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Should be a commsissioned officer");
                }
                else tvError.setVisibility(View.GONE);
            }
        });





        SelfEmployeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    int income = Integer.parseInt(tvIncome.getText().toString().trim());
                    if (income < 70000) {
                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Error! Minimum 70000 income Require");
                    } else {
                        tvError.setVisibility(View.GONE);
                    }
                    view.findViewById(R.id.one_year_relation_layout).setVisibility(View.VISIBLE);
                }
                else
                    view.findViewById(R.id.one_year_relation_layout).setVisibility(View.GONE);
                tvError.setVisibility(View.GONE);
            }
        });


        SalariedEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    int income = Integer.parseInt(tvIncome.getText().toString().trim());
                    if (income < 25000) {
                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Error! Minimum 25000 income Require");
                    }
                    view.findViewById(R.id.Three_month_relation_layout).setVisibility(View.VISIBLE);
                }
                else
                    view.findViewById(R.id.Three_month_relation_layout).setVisibility(View.GONE);
            }
        });
                super.onActivityCreated(savedInstanceState);
    }

    }

