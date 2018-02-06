package com.example.saad_kamaal.bankingagent.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saad_kamaal.bankingagent.helper.UtilHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userIdEt;
    private EditText passwordEt;
    private Button loginBtn;
    private Button createAccountBtn;
    private DatabaseReference loginRef;
    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        userIdEt = (EditText) findViewById(R.id.user_id_et);
        passwordEt = (EditText) findViewById(R.id.password_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        createAccountBtn = (Button) findViewById(R.id.create_account_btn);
        createAccountBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn) {
            if (inputsAreValid()) {
                UtilHelper.showWaitDialog(this, "Checking Credentials");
                userId = userIdEt.getText().toString();
                loginRef = FirebaseDatabase.getInstance()
                        .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root/users/" + userId);
                loginRef.addListenerForSingleValueEvent(loginListener);
            }
        } else if (v.getId() == R.id.create_account_btn) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    ValueEventListener loginListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            UtilHelper.dismissWaitDialog();
            if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                String password = (String) dataSnapshot.getValue();
                if (password.equals(passwordEt.getText().toString())) {
                    UtilHelper.createLoginSession(LoginActivity.this, userId);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    UtilHelper.showMessage(LoginActivity.this, "login failed...");
                }
            } else {
                UtilHelper.showMessage(LoginActivity.this, "login failed...");
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private boolean inputsAreValid() {
        if (TextUtils.isEmpty(userIdEt.getText())) {
            userIdEt.setError("Required!");
            userIdEt.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(passwordEt.getText())) {
            passwordEt.setError("Required!");
            passwordEt.requestFocus();
            return false;
        }
        return true;
    }
}
