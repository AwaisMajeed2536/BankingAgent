package com.example.saad_kamaal.bankingagent.fragments;

import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.saad_kamaal.bankingagent.activities.R;


/**
 * Created by saad_kamaal on 7/6/2017.
 */

public class CarLoanFragment extends Fragment {
    public String ImpawnTextToChange;
    private TextView tvError,tvAge,Impawntv;
    private EditText tvIncome;
    private EditText TenureDuration,CarPrice,DownPay;
    private View view;
    private RadioButton cnicNo,dualNationalityYes;
    private RadioButton govtEmployee;
    private RadioButton armedEmployee;
    private RadioButton privateEmployee;
    private RadioButton bps14Yes,bps14No,ImpawnNo;
    private RadioButton brachSelfEmployeed,salariedEmployee;
    private RadioButton sixMonthsNo,commisionedOfficerNo;
    private RadioButton newCar,usedCar,importedCar,olderYes,olderNo,coBorrowerYes,coBorrowerNo,coBorrowerEmployeedYes,coBorrowerEmployeedNo,relation,others;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_car_loan, container,false);
        initView();
        return  view;
    }

    public void initView(){

        //getSupportActionBar().setTitle("Car Loan");
        tvError = (TextView) view.findViewById(R.id.tvNotEligible);
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        tvIncome = (EditText) view.findViewById(R.id.tvIncome);
        cnicNo = (RadioButton) view.findViewById(R.id.cnic_no_radio);
        newCar = (RadioButton) view.findViewById(R.id.new_car_radio);
        usedCar = (RadioButton) view.findViewById(R.id.used_car_radio);
        importedCar = (RadioButton) view.findViewById(R.id.imported_car_radio);
        olderYes = (RadioButton) view.findViewById(R.id.older_than_7_yes_radio);
        olderNo = (RadioButton) view.findViewById(R.id.older_than_7_no_radio);
        coBorrowerYes = (RadioButton) view.findViewById(R.id.coborrower_yes_radio);
        coBorrowerNo = (RadioButton) view.findViewById(R.id.coborrower_no_radio);
        coBorrowerEmployeedYes = (RadioButton) view.findViewById(R.id.coborrower_employeed_yes_radio);
        coBorrowerEmployeedNo = (RadioButton) view.findViewById(R.id.coborrower_employeed_no_radio);
        relation = (RadioButton) view.findViewById(R.id.co_borrower_relation_radio);
        others = (RadioButton) view.findViewById(R.id.co_borrower_others_radio);
        dualNationalityYes = (RadioButton) view.findViewById(R.id.dual_nationality_yes);
        govtEmployee = (RadioButton) view.findViewById(R.id.govt_employee_radio);
        armedEmployee = (RadioButton) view.findViewById(R.id.armed_employee_radio);
        privateEmployee = (RadioButton) view.findViewById(R.id.private_employee_radio);
        bps14No = (RadioButton) view.findViewById(R.id.bps_14_no_radio);
        bps14Yes = (RadioButton) view.findViewById(R.id.bps_14_yes_radio);
        brachSelfEmployeed = (RadioButton) view.findViewById(R.id.branch_self_employeed_radio);
        sixMonthsNo = (RadioButton) view.findViewById(R.id.bps_14_6_month_no_radio);
        TenureDuration = (EditText) view.findViewById(R.id.etTenure);
        DownPay = (EditText) view.findViewById(R.id.etDwnPayCar);
        CarPrice = (EditText) view.findViewById(R.id.etLoanAmountCAr);
        salariedEmployee = (RadioButton) view.findViewById(R.id.branch_salaried_radio);
        commisionedOfficerNo =(RadioButton) view.findViewById(R.id.commissioned_officer_no_radio);
        ImpawnNo = (RadioButton) view.findViewById(R.id.Impawn_no_radio);
        Impawntv = (TextView) view.findViewById(R.id.Impawn_tv);
        ImpawnTextToChange = Impawntv.getText().toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        newCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    double loanAmount = Double.parseDouble(CarPrice.getText().toString().trim());
                    loanAmount=loanAmount*(20.0/100.0);
                    Impawntv.setText(ImpawnTextToChange + loanAmount);
                    view.findViewById(R.id.Impawn_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.co_borrower_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.older_than_7layout).setVisibility(View.GONE);
                }
                else{
                    view.findViewById(R.id.co_borrower_layout).setVisibility(View.GONE);
                }
            }
        });

        usedCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    double loanAmount = Double.parseDouble(CarPrice.getText().toString().trim());
                    loanAmount=loanAmount*(25.0/100.0);
                    Impawntv.setText(ImpawnTextToChange + loanAmount);
                    view.findViewById(R.id.Impawn_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.older_than_7layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.co_borrower_relation_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.bps_14_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
            }
        });

        importedCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    double loanAmount = Double.parseDouble(CarPrice.getText().toString().trim());
                    loanAmount=loanAmount*(30.0/100.0);
                    Impawntv.setText(ImpawnTextToChange + loanAmount);
                    view.findViewById(R.id.Impawn_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.older_than_7layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.co_borrower_relation_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.bps_14_layout).setVisibility(View.GONE);
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
            }
        });

        olderYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Error!Not Applicable");
                }
                else{
                    tvError.setVisibility(View.GONE);
                }
            }
        });

        olderNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    view.findViewById(R.id.co_borrower_layout).setVisibility(View.VISIBLE);
                }
                else{
                    view.findViewById(R.id.co_borrower_layout).setVisibility(View.GONE);
                }
            }
        });
        coBorrowerYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    view.findViewById(R.id.co_borrower_employeed_layout).setVisibility(View.VISIBLE);

                }
                else{
                    view.findViewById(R.id.co_borrower_employeed_layout).setVisibility(View.GONE);
                }
            }
        });
        coBorrowerNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        view.findViewById(R.id.employment_selection_layout).setVisibility(View.VISIBLE);
                    }
                }
            });

        coBorrowerEmployeedYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    view.findViewById(R.id.co_borrower_relation_layout).setVisibility(View.VISIBLE);
                }
                else{
                    view.findViewById(R.id.co_borrower_relation_layout).setVisibility(View.GONE);
                }
            }
        });

        govtEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    view.findViewById(R.id.bps_14_layout).setVisibility(View.VISIBLE);
                }
                else {
                    view.findViewById(R.id.bps_14_layout).setVisibility(View.GONE);
                }
            }
        });

        privateEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    view.findViewById(R.id.private_selection_layout).setVisibility(View.VISIBLE);
                }
                else {
                    view.findViewById(R.id.private_selection_layout).setVisibility(View.GONE);
                }
            }

        });

        armedEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    view.findViewById(R.id.commissioned_officer_layout).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
                else {
                    view.findViewById(R.id.commissioned_officer_layout).setVisibility(View.GONE);
                }
            }
        });

        bps14Yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    view.findViewById(R.id.six_months_service).setVisibility(View.VISIBLE);
                }
                else {
                    view.findViewById(R.id.six_months_service).setVisibility(View.GONE);
                }
            }
        });

        brachSelfEmployeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    view.findViewById(R.id.one_year_relation_layout).setVisibility(View.VISIBLE);
                }
                else
                    view.findViewById(R.id.one_year_relation_layout).setVisibility(View.GONE);
            }
        });

        relation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    view.findViewById(R.id.employment_selection_layout).setVisibility(View.VISIBLE);
                }
                else{
                    view.findViewById(R.id.employment_selection_layout).setVisibility(View.GONE);
                }
            }
        });


//Validation part
        tvAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(tvAge.getText().toString().isEmpty())){
                    int age = Integer.parseInt(tvAge.getText().toString().trim());
                    String stAge = tvAge.getText().toString().trim();
                    if(age<21 ||age>60 || stAge.equals("")) {
                        tvAge.setError("Age must be between 21 and 60 Enter Age");
                    }
                }
            }
        });
        TenureDuration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(TenureDuration.getText().toString().isEmpty())){
                    int duration = Integer.parseInt(TenureDuration.getText().toString().trim());
                    String stDuration = TenureDuration.getText().toString().trim();
                    if(duration<1 ||duration>7 || stDuration.equals("")) {
                        TenureDuration.setError("Duration must be between 1 and 7");
                    }
                }
            }
        });
        CarPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(CarPrice.getText().toString().isEmpty())){
                    int CarPriceVar = Integer.parseInt(CarPrice.getText().toString().trim());
                    String stDuration = CarPrice.getText().toString().trim();
                    if(CarPriceVar<1 || stDuration.equals("")) {
                        CarPrice.setError("Enter valid value");
                    }
                }
            }
        });

        DownPay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !(DownPay.getText().toString().isEmpty())){
                    double DownPayVar = Integer.parseInt(DownPay.getText().toString().trim());
                    double CarPricevar = Integer.parseInt(CarPrice.getText().toString().trim());
                    double DwnPercentageVar = (DownPayVar/CarPricevar)*100;
                    String stDownpay = DownPay.getText().toString().trim();
                    if(DwnPercentageVar<15 || stDownpay.equals("")) {
                        DownPay.setError("Down Payment at least 15 Percent of your Loan that is "+CarPricevar*15/100);
                    }
                }
            }
        });


        //validation
        view. findViewById(R.id.Check_Car_eligibilty_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvError.setVisibility(View.VISIBLE);
                if(newCar.isChecked()){

                }
                if(usedCar.isChecked()||importedCar.isChecked()){
                    if(olderYes.isChecked()){
                        tvError.setText("Error!, You cannot take this much older car");
                    }
                }

                 if(dualNationalityYes.isChecked()){
                    tvError.setText("Error, Dual nationality is not allowed");
                }

                if(cnicNo.isChecked()){
                    tvError.setText("Error, You must be CNIC holder");
                }

                if(others.isChecked()){
                    tvError.setText("Error!, Not applicable for co-borrower relation");
                }

                if (coBorrowerEmployeedNo.isChecked()){
                    tvError.setText("Error!, Co-borrower must be Employeed");
                }

                if(bps14No.isChecked()){
                    tvError.setText("Must be a BPS 14 Employee");
                }

                if(sixMonthsNo.isChecked()){
                    tvError.setText("Must have done six month service at Job");
                }
                if(commisionedOfficerNo.isChecked()){
                    tvError.setText("Must be a Comissioned Officer");
                }
                if(ImpawnNo.isChecked()){
                    tvError.setText("Must have assets!");
                }

                if(armedEmployee.isChecked() || govtEmployee.isChecked()) {
                    int income = Integer.parseInt(tvIncome.getText().toString().trim());
                    if(income<25000){
                        tvError.setText("Error! Minimum 25000 income Require");
                    }
                }
                else if(brachSelfEmployeed.isChecked()){
                    int income = Integer.parseInt(tvIncome.getText().toString().trim());
                    if(income<70000){
                        tvError.setText("Error! Minimum 70000 income Require");
                    }
                }
                CarMonthlyInstallment();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void CarMonthlyInstallment() {
        double half_pay = Integer.parseInt(tvIncome.getText().toString().trim());
        int tenureDuration = Integer.parseInt(TenureDuration.getText().toString().trim());
        int numberOinstallment = tenureDuration*12;
        half_pay/=2.0;
        double interest = 0;//0.0183,0.022,0.0067,0.0083
        if(brachSelfEmployeed.isChecked())
            interest = 0.0092;
        else if (govtEmployee.isChecked()) {
            if (tenureDuration > 3){
                interest=0.008;
            }
                else{
                interest=0.0083;
            }

        }

        else if(salariedEmployee.isChecked())
            if (tenureDuration > 3) {
               interest = 0.008;
            }
            else {
                interest = 0.0083;
            }
        else if(armedEmployee.isChecked()) {
            if (tenureDuration > 3) {
                interest = 0.0083;
            }
            else interest=0.0067;
        }

        int loanAmount = Integer.parseInt(CarPrice.getText().toString().trim());
        int downPaymentVar = Integer.parseInt(DownPay.getText().toString().trim());
        loanAmount-=downPaymentVar;
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
        else{
            tvError.setText("");
            Toast.makeText(getActivity(), "Eligible for The Loan \n your installment is : " + monthlyInstall, Toast.LENGTH_LONG).show();
        }
    }

}
