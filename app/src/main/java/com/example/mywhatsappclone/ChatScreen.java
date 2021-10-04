package com.example.mywhatsappclone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mywhatsappclone.Adapters.ChatScreenAdapter;
import com.example.mywhatsappclone.Models.MessageModel;
import com.example.mywhatsappclone.databinding.ActivityChatScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ChatScreen extends AppCompatActivity {
    ActivityChatScreenBinding binding;
    String name, profilePic, one2oneOrRoomid;
    //boolean isGroup;
    FirebaseFirestore db;
    String userId;
    ArrayList<MessageModel> messageList;
    ChatScreenAdapter chatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        name = getIntent().getStringExtra("name");
        profilePic = getIntent().getStringExtra("profilePic");
        one2oneOrRoomid = getIntent().getStringExtra("id");

        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getUid();
        binding.chatNameTV.setText(name);
        Picasso.get().load(profilePic).placeholder(R.drawable.avatar).into(binding.profileImage);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });


        messageList = new ArrayList<>();
        chatAdapter = new ChatScreenAdapter(messageList, this, userId);
        binding.chatScreenRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatScreenRecyclerView.setLayoutManager(layoutManager);

   binding.sendButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

       if(binding.etMessage.getText().toString().equals("")){
           Toast.makeText(getApplicationContext()," Can't send empty message ",Toast.LENGTH_LONG).show();
       //Later for better user experience just disable the send button when nothing is typed on edit text
       } else{
           Map<String,Object> msg= new HashMap<>();
           msg.put("msg",binding.etMessage.getText().toString().trim());
           msg.put("senderId",userId);
           msg.put("timeStamp",new Date().getTime());
           db.collection("OneToOne").document(one2oneOrRoomid.trim()).collection("Message").add(msg).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
               @Override
               public void onSuccess(DocumentReference documentReference) {
                   //  Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                   Log.i("Error Tracing", "Msg Added Successfully");

               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
               }
           });
       }


           binding.etMessage.setText("");

       }
   });


    }

    @Override
    protected void onStart() { // we set up a listener here so that it doesnt keep running in background when app is not running or this screen is not on display , so alternatively you can like start in some other life cycle listner but then you have to disable the listener in the counter method of that life cycle method but here in onStart i guess we dont have to disable listener it in onStop method
        super.onStart();

            //  db.collection("OneToOne").document().get().addOnSuccessListener(){
            Log.i("Error Tracing", "Inside else of chat screen and id is" + one2oneOrRoomid);
            db.collection("OneToOne").document(one2oneOrRoomid.trim()).collection("Message").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @RequiresApi(api = Build.VERSION_CODES.N) // this is for comparingLong function
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(!value.isEmpty()){
                        messageList.clear();
                        for (DocumentSnapshot doc :value.getDocuments()) {
                            Log.i("Error Tracing", "Inside for of chat screen and id is" + one2oneOrRoomid);
                            MessageModel msg = new MessageModel();
                            msg.setMsg(doc.get("msg").toString());
                            msg.setTimeStamp(Long.parseLong(doc.get("timeStamp").toString()));
                            msg.setSenderId(doc.get("senderId").toString());
                            messageList.add(msg);
                            chatAdapter.notifyDataSetChanged();
                        }
                        //long a=12,b=123;
                        Collections.sort(messageList,Comparator.comparingLong(MessageModel::getTimeStamp));// this is in order t sort list based on time of message
                     // messageList.sort((e1,e2)->);
                    }
                    else {
                        Log.i("Error Tracing", "Inside else of onEVENt of chat screen and doc is empty");

                    }

                }
            });

    }

}