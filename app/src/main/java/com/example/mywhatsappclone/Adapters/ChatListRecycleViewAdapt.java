package com.example.mywhatsappclone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mywhatsappclone.ChatScreen;
import com.example.mywhatsappclone.ChatScreenForGroup;
import com.example.mywhatsappclone.Models.ChatListItem;
import com.example.mywhatsappclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatListRecycleViewAdapt extends RecyclerView.Adapter<ChatListRecycleViewAdapt.ViewHolder> {
    ArrayList<ChatListItem> chatListItems;// Make it later of user instead of integer
   Context context;

    public ChatListRecycleViewAdapt(ArrayList<ChatListItem> list, Context context) {
        this.chatListItems = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_list_item,parent,false);
        Log.i("Error Tracing","Inside adapter OncreateViewHOlder");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("Error Tracing","Inside adapter OnBindingViewHOlder");

        ChatListItem chatItem= chatListItems.get(position);
        Log.i("Error Tracing","Size of list is"+Integer.toString(chatListItems.size()));

     Picasso.get().load(chatItem.getProfilePic()).placeholder(R.drawable.avatar).into(holder.profilePic);// placeholder means that it load url is null then placeholder image will be used
        holder.receiverName.setText(chatItem.getName());
        holder.lastMessage.setText(chatItem.getLastMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatItem.getIsGroup()){
                    Intent intent = new Intent(context, ChatScreenForGroup.class);
                    //LATER MAKE  CHATLIST CLASS PARSABLE SO THAT YOU DONT HAVE TO SEND ITS MEMBER ONE BY ONE INSTEAD YOU CAN SEND WHOLE OBJECT
                    intent.putExtra("id", chatItem.getId());
                    intent.putExtra("profilePic", chatItem.getProfilePic());
                    intent.putExtra("name", chatItem.getName());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, ChatScreen.class);
                    //LATER MAKE  CHATLIST CLASS PARSABLE SO THAT YOU DONT HAVE TO SEND ITS MEMBER ONE BY ONE INSTEAD YOU CAN SEND WHOLE OBJECT
                    intent.putExtra("id", chatItem.getId());
                    intent.putExtra("profilePic", chatItem.getProfilePic());
                    intent.putExtra("name", chatItem.getName());
                    context.startActivity(intent);

                }
//                intent.putExtra("userName", chatItem.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         ImageView profilePic;
         TextView receiverName,lastMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic= itemView.findViewById(R.id.profile_image);
            receiverName = itemView.findViewById(R.id.name);
            lastMessage= itemView.findViewById(R.id.lastMessage);
        }
    }
}
