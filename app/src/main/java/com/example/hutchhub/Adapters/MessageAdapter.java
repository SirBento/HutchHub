package com.example.hutchhub.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
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
        public TextView senderMessageText, receiverMessageText;



        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMessageText = itemView.findViewById(R.id.sender_message_text);
            receiverMessageText = itemView.findViewById(R.id.receiver_message_text);
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

            holder.senderMessageText.setVisibility(View.VISIBLE);
            holder.receiverMessageText.setVisibility(View.INVISIBLE);
            holder.senderMessageText.setTextColor(Color.BLACK);
            holder.senderMessageText.setBackgroundResource(R.drawable.sender_messeges_layout);
            holder.senderMessageText.setText(messages.getMessage() + "\n \n" + messages.getTime() + "  -  " + messages.getDate());


        }else{

            holder.receiverMessageText.setVisibility(View.VISIBLE);
            holder.senderMessageText.setVisibility(View.INVISIBLE);
            holder.senderMessageText.setTextColor(Color.BLACK);
            holder.receiverMessageText.setBackgroundResource(R.drawable.receiver_messages_layout);
            holder.receiverMessageText.setText(messages.getMessage() + "\n \n" + messages.getTime() + "  -  " + messages.getDate());
        }

    }

    @Override
    public int getItemCount() {
        return userMessageList.size();
    }




}
