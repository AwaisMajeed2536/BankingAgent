package com.example.saad_kamaal.bankingagent.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.saad_kamaal.bankingagent.adapters.ChatterBoxAdapter;
import com.example.saad_kamaal.bankingagent.helper.UtilHelper;
import com.example.saad_kamaal.bankingagent.models.MessageModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChatterBoxActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView chatterBoxRv;
    private List<MessageModel> dataList;
    private ChatterBoxAdapter adapter;
    private DatabaseReference messagesRef;
    private EditText typedMessageEt;
    private ImageView sendBtn;
    private String newMessage = "";
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter_box);
        initView();

    }

    private void initView() {
        UtilHelper.showWaitDialog(this, "loading data...");
        chatterBoxRv = (RecyclerView) findViewById(R.id.chatter_box_rv);
        typedMessageEt = (EditText) findViewById(R.id.typed_message_et);
        sendBtn = (ImageView) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(this);
        dataList = new ArrayList<>();
        adapter = new ChatterBoxAdapter(this, dataList);
        chatterBoxRv.setAdapter(adapter);
        chatterBoxRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chatterBoxRv.setItemAnimator(new DefaultItemAnimator());
        chatterBoxRv.setHasFixedSize(true);
        messagesRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://virtual-agent-d9704.firebaseio.com/root/chatterBox");
        messagesRef.addChildEventListener(childEventListener);
    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap<String, String> message = (HashMap<String, String>) dataSnapshot.getValue();
            MessageModel model = new MessageModel(message.get("message"), message.get("userId"));
            adapter.notifyDataSetChanged(model);
            chatterBoxRv.scrollToPosition(counter++);
            UtilHelper.dismissWaitDialog();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public void onClick(View v) {
        newMessage = typedMessageEt.getText().toString();
        MessageModel model = new MessageModel(newMessage, UtilHelper.getUserId(this));
        messagesRef.child(String.valueOf(counter)).setValue(model);
        typedMessageEt.setText("");
        typedMessageEt.setHint("Message");
    }
}












