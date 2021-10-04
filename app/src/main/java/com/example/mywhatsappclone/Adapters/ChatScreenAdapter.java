package com.example.mywhatsappclone.Adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywhatsappclone.Models.MessageModel;
import com.example.mywhatsappclone.R;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatScreenAdapter extends RecyclerView.Adapter {
    ArrayList<MessageModel> messageList;
    Context context;
    String id;
    int USER_VIEW_TYPE=1;
    int OTHERS_VIEW_TYPE=2;

    public ChatScreenAdapter(ArrayList<MessageModel> messageList, Context context, String id) {
        this.messageList = messageList;
        this.context = context;
        this.id = id;
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getUid())){// here we are checking that uid of current message is same as senders or not , and sender is the person who is logged in to the device right now , all  the others are recievers
            return USER_VIEW_TYPE;
        }else{
            return OTHERS_VIEW_TYPE;
        }

        //return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==USER_VIEW_TYPE){// we checking message kiska hai and then we choose the layout accordingly
            View view= LayoutInflater.from(context).inflate(R.layout.current_user,parent,false);
            return new UserViewHolder(view);

        }else{
            View view= LayoutInflater.from(context).inflate(R.layout.other_users,parent,false);
            return new OthersViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message=messageList.get(position);// jo message aega we display it at the same time so that order na change ho messages ka
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
       // cal.setTimeInMillis(timestamp);
      //  String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        if(holder.getClass()==UserViewHolder.class){// here we are checking ki konsi class ka holder hai
            ((UserViewHolder)holder).userMsg.setText(message.getMsg());
            //((UserViewHolder)holder).userMsgTime.setText(String.valueOf(message.getTimeStamp()));
            cal.setTimeInMillis(message.getTimeStamp());
           // String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

            String time = DateFormat.format("hh:mm", cal).toString();
        if(cal.get(Calendar.AM_PM)==Calendar.AM){
            ((UserViewHolder)holder).userMsgTime.setText(time+" AM");

        }else{
            ((UserViewHolder)holder).userMsgTime.setText(time+" PM");
        }
        // boolean ampm= cal.after(121);
            // the above type casting is done in order to avoid any error caused by type mismatch
            //((UserViewHolder)holder).userMsgTime.setText(time);
        }
        else{
            ((OthersViewHolder)holder).othersMsg.setText(message.getMsg());
            cal.setTimeInMillis(message.getTimeStamp());
            String time = DateFormat.format("hh:mm", cal).toString();
            if(cal.get(Calendar.AM_PM)==Calendar.AM){
                ((OthersViewHolder)holder).othersMsgTime.setText(time+" AM");

            }else{
                ((OthersViewHolder)holder).othersMsgTime.setText(time+" PM");

            }
        }
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setTitle("Delete Message")
//                        .setMessage("Are you sure you want to delete this message?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                FirebaseDatabase database= FirebaseDatabase.getInstance();
//                                String senderRoom = FirebaseAuth.getInstance().getUid()+recId;
//                                database.getReference().child("chats").child(senderRoom).child(message.getMessageId()).setValue(null);
//                            }
//                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).show();
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder { // this view holder is for reciever message
        TextView userMsg,userMsgTime;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userMsg=itemView.findViewById(R.id.userMessageTV); // later on maybe same name for group id and one to one might cause error
            userMsgTime=itemView.findViewById(R.id.userMessageTimeTV);// later maybe get binding object using constructor and use it to avoid name clash in xml
        }
    }

    public class OthersViewHolder extends RecyclerView.ViewHolder {
        TextView othersMsg,othersMsgTime;

        public OthersViewHolder(@NonNull View itemView) {
            super(itemView);
            othersMsg=itemView.findViewById(R.id.othersMessageTV);
            othersMsgTime=itemView.findViewById(R.id.othersMessageTimeTV);
        }
    }
}
