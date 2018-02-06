package com.example.saad_kamaal.bankingagent.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.saad_kamaal.bankingagent.activities.MainActivity;
import com.example.saad_kamaal.bankingagent.activities.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by saad_kamaal on 7/4/2017.
 */

public class LoanFragment extends Fragment implements View.OnClickListener{
    private Button personalLoan, carLoan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_loan, container, false);
        initializeView(view);
        return view;
    }
    public void initializeView(View view){
        personalLoan = (Button)  view.findViewById(R.id.personal_loan_button);
        carLoan = (Button)  view.findViewById(R.id.car_loan_button);
        personalLoan.setOnClickListener(this);
        carLoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        switch (v.getId()){
            case R.id.personal_loan_button:
                final DatabaseReference pLoanRef = FirebaseDatabase.getInstance()
                        .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root/personalLoans");
                pLoanRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Long previousCount = (Long) dataSnapshot.getValue();
                        pLoanRef.setValue(++previousCount);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                if(activity instanceof MainActivity){
                    ((MainActivity)activity).ReplaceFragment(new PersonalLoanFragment());
                }
                break;
            case R.id.car_loan_button:
                final DatabaseReference cLoanRef = FirebaseDatabase.getInstance()
                        .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root/carLoans");
                cLoanRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Long previousCount = (Long) dataSnapshot.getValue();
                        cLoanRef.setValue(++previousCount);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                if(activity instanceof MainActivity){
                    ((MainActivity)activity).ReplaceFragment(new CarLoanFragment());
                }

                break;
        }

    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loan);
//        getSupportActionBar().setTitle("Loan");
//        findViewById(R.id.personal_loan_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoanFragment.this,PersonalLoanFragment.class);
//                startActivity(intent);
//            }
//        });
//        findViewById(R.id.car_loan_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoanFragment.this,CarLoanFragment.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }

}
