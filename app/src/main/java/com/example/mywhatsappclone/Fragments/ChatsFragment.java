package com.example.mywhatsappclone.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mywhatsappclone.Adapters.ChatListRecycleViewAdapt;
import com.example.mywhatsappclone.Models.ChatListItem;
import com.example.mywhatsappclone.Models.GroupChatModel;
import com.example.mywhatsappclone.Models.OneToOneChatModel;
import com.example.mywhatsappclone.Models.User;
import com.example.mywhatsappclone.databinding.FragmentChatsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ChatsFragment extends Fragment {
    String userId;
    FragmentChatsBinding binding;
    FirebaseFirestore db;
    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        ArrayList<ChatListItem> list = new ArrayList<>(); // later make this get filled with firebase
        ChatListRecycleViewAdapt adapter = new ChatListRecycleViewAdapt(list, getContext());
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getUid();
        binding.chatListRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatListRecyclerView.setLayoutManager(layoutManager);

        db.collection("Users").document(userId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    //Log.i("Error Tracing","Successfull  and name is  "+documentSnapshot.get("Name").toString());
                    // ArrayList<String> dum= user.getOneToOneChatIds();
                    for (String roomId : user.getGroupIds()) {
                        db.collection("Rooms").document(roomId.trim()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot3) {
                                if (documentSnapshot3.exists()) {

                                    ChatListItem item = new ChatListItem();
                                    GroupChatModel chatModel = documentSnapshot3.toObject(GroupChatModel.class);

                                    item.setName(chatModel.getNameOfRoom());
                                    item.setProfilePic(chatModel.getProfilePicOfRoom());
                                    item.setLastMessage(chatModel.getLastMessage().get("message"));

                                    item.setIsGroup(true);
                                    item.setId(roomId);

                                    list.add(item);

                                    adapter.notifyDataSetChanged();

                                } else {
                                    Log.i("Error Tracing", " Document 3 Does not exist");

                                }

                            }

                        });
                    }

                    for (String chatId : user.getOneToOneChatIds()) {

                        db.collection("OneToOne").document(chatId.trim()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot2) {
                                // list.add(documentSnapshot.get(""));
                                // String st=(Map<String,String>);
                                if (documentSnapshot2.exists()) {

                                    ChatListItem item = new ChatListItem();
                                    OneToOneChatModel chatModel = documentSnapshot2.toObject(OneToOneChatModel.class);


                                    if (!(chatModel.getUser1().get("userId").equals(userId))) {
                                        item.setName(chatModel.getUser1().get("Name"));
                                        // item.setProfilePic(chatModel.getUser1().get("profilePic"));

                                    } else {
                                        item.setName(chatModel.getUser2().get("Name"));
                                        //item.setProfilePic(chatModel.getUser2().get("profilePic"));

                                    }
                                    //item.setName(chatModel.get);
                                    item.setLastMessage(chatModel.getLastMessage().get("message"));

                                    item.setIsGroup(false);
                                    item.setId(chatId);

                                    list.add(item);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Log.i("Error Tracing", "Document Does not exist");

                                }

                            }
                        });
                    }

                    //list.sort(); make a comparator that sorts the list based on last message time
                    adapter.notifyDataSetChanged();
                } else {
                    Log.i("Error Tracing", "User Not Found");
                }
            }
        });

        //Add Logic For retrieving users from Firestore and after that dont forget to put intent filter back to signing page

        Log.i("Error Tracing", "end of on create view");

        return binding.getRoot();
    }
}