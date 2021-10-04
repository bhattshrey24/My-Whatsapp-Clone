package com.example.mywhatsappclone.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywhatsappclone.Models.MessageModel;
import com.example.mywhatsappclone.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatScreenGroupAdapter extends RecyclerView.Adapter{
    ArrayList<MessageModel> messageList;
    Context context;
    String id;
    int USER_VIEW_TYPE=1;
    int OTHERS_VIEW_TYPE=2;
    String[]color= new String[]{"#70E614","#F44336","#03A9F4","#673AB7","#FFEB3B","#FFBB86FC","#FF9800"};


    public ChatScreenGroupAdapter(ArrayList<MessageModel> messageList, Context context, String id) {
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
            return new ChatScreenGroupAdapter.UserViewHolder(view);

        }else{
            View view= LayoutInflater.from(context).inflate(R.layout.other_users_group,parent,false);
            return new ChatScreenGroupAdapter.OthersViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message=messageList.get(position);// jo message aega we display it at the same time so that order na change ho messages ka
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);

        Random rand = new Random();
        int col = rand.nextInt(7);
        String selectedColor =color[col];
        if(holder.getClass()== ChatScreenGroupAdapter.UserViewHolder.class){// here we are checking ki konsi class ka holder hai
            ((ChatScreenGroupAdapter.UserViewHolder)holder).userMsg.setText(message.getMsg());
            cal.setTimeInMillis(message.getTimeStamp());
            String time = DateFormat.format("hh:mm", cal).toString();
            if(cal.get(Calendar.AM_PM)==Calendar.AM){
                ((ChatScreenGroupAdapter.UserViewHolder)holder).userMsgTime.setText(time+" AM");
            }else{
                ((ChatScreenGroupAdapter.UserViewHolder)holder).userMsgTime.setText(time+" PM");
            }

        }
        else{
            ((ChatScreenGroupAdapter.OthersViewHolder)holder).othersMsgG.setText(message.getMsg());
            cal.setTimeInMillis(message.getTimeStamp());
            String time = DateFormat.format("hh:mm", cal).toString();
            if(cal.get(Calendar.AM_PM)==Calendar.AM){
                ((ChatScreenGroupAdapter.OthersViewHolder)holder).othersMsgTimeG.setText(time+" AM");

            }else{
                ((ChatScreenGroupAdapter.OthersViewHolder)holder).othersMsgTimeG.setText(time+" PM");

            }
            //Problem is wether to call using id or somthing of user to get its name? or change the message model to store name but it can cause clash since names might change in future
            ((ChatScreenGroupAdapter.OthersViewHolder)holder).othersNameG.setText("message.");

            ((ChatScreenGroupAdapter.OthersViewHolder)holder).othersNameG.setTextColor(Color.parseColor(selectedColor));


        }

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
        TextView othersMsgG,othersMsgTimeG,othersNameG;

        public OthersViewHolder(@NonNull View itemView) {
            super(itemView);
            othersNameG=itemView.findViewById(R.id.othersNameTv);
            othersMsgG=itemView.findViewById(R.id.othersMessageTVGroup);
            othersMsgTimeG=itemView.findViewById(R.id.othersMessageTimeTVGroup);
        }
    }
}
