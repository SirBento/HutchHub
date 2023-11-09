package com.example.hutchhub.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hutchhub.Models.Message;
import com.example.hutchhub.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    List<Message> userMessageList;
     FirebaseAuth Auth;


    public MessageAdapter(List<Message> userMessagesList)
    {
        this.userMessageList = userMessagesList;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder
    {
        public TextView senderMessageText, receiverMessageText,receiver_username;
        public ConstraintLayout constraintLayout_sender;


        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMessageText = itemView.findViewById(R.id.sender_message_text);
            receiverMessageText = itemView.findViewById(R.id.receiver_message_text);
            receiver_username = itemView.findViewById(R.id.receiver_username);
            constraintLayout_sender = itemView.findViewById(R.id.constraintLayout_sender);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_messages_layout,null);

        Auth = FirebaseAuth.getInstance();

        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        String messageSenderID = Auth.getCurrentUser().getUid();
        Message messages = userMessageList.get(position);
        String fromUserID = messages.getId();

        if(messageSenderID.equals(fromUserID)){


            holder.constraintLayout_sender.setVisibility(View.INVISIBLE);
            holder.receiverMessageText.setVisibility(View.INVISIBLE);
            holder.receiver_username.setVisibility(View.INVISIBLE);

            holder.senderMessageText.setTextColor(Color.BLACK);
            holder.senderMessageText.setVisibility(View.VISIBLE);
            holder.senderMessageText.setBackgroundResource(R.drawable.sender_messeges_layout);
            holder.senderMessageText.setText(messages.getMessage() + "\n \n" + messages.getTime() + "\n" + messages.getDate());

        }else{

            holder.receiverMessageText.setVisibility(View.VISIBLE);
            holder.receiver_username.setVisibility(View.VISIBLE);
            holder.senderMessageText.setVisibility(View.INVISIBLE);
            holder.senderMessageText.setTextColor(Color.BLACK);
            holder.receiverMessageText.setBackgroundResource(R.drawable.receiver_messages_layout);
            holder.receiver_username.setText(messages.getName());
            holder.receiverMessageText.setText(messages.getMessage() + "\n \n" + messages.getTime() + "\n" + messages.getDate());
        }

    }

    @Override
    public int getItemCount() {
        return userMessageList.size();
    }




}
