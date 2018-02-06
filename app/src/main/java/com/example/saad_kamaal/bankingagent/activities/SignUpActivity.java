package com.example.saad_kamaal.bankingagent.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saad_kamaal.bankingagent.helper.UtilHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userIdEt;
    private EditText passwordEt;
    private Button signUpBtn;
    private DatabaseReference signUpRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }


    private void initView() {
        userIdEt = (EditText) findViewById(R.id.user_id_et);
        passwordEt = (EditText) findViewById(R.id.password_et);
        signUpBtn = (Button) findViewById(R.id.sign_up_btn);
        signUpBtn.setOnClickListener(this);
        signUpRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root/users");
    }

    @Override
    public void onClick(View v) {
        if (inputsAreValid()) {
            UtilHelper.showWaitDialog(this, "Signng up");
            final String userId = userIdEt.getText().toString();
            signUpRef.child(userId).setValue(passwordEt.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            UtilHelper.createLoginSession(SignUpActivity.this, userId);
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            UtilHelper.dismissWaitDialog();
                        }
                    });
        }
    }


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
