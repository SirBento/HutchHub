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

    public MessageAdapter(List<Message> userMessagesList) {
        this.userMessageList = userMessagesList;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder
    {
        public TextView senderMessageText, sender_date, sender_time,
                receiverMessageText,receiver_date, receiver_time, receiver_username;
        public ConstraintLayout constraintLayout_receiver, constraintLayout_sender;


        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMessageText = itemView.findViewById(R.id.sender_message_text);
            receiverMessageText = itemView.findViewById(R.id.receiver_message_text);
            receiver_username = itemView.findViewById(R.id.receiver_username);
            constraintLayout_receiver = itemView.findViewById(R.id.constraintLayout_receiver);
            constraintLayout_sender = itemView.findViewById(R.id.constraintLayout_sender);
            sender_date = itemView.findViewById(R.id.sender_date);
            sender_time = itemView.findViewById(R.id.sender_time);
            receiver_time = itemView.findViewById(R.id.receiver_time);
            receiver_date = itemView.findViewById(R.id.receiver_date);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_messages_layout,null);

        Auth = FirebaseAuth.getInstance();

        return new MessageViewHolder(view); }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        String messageSenderID = Auth.getCurrentUser().getUid();
        Message messages = userMessageList.get(position);
        String fromUserID = messages.getId();

        if(messageSenderID.equals(fromUserID)){

            holder.sender_date.setVisibility(View.VISIBLE);
            holder.sender_time.setVisibility(View.VISIBLE);
            holder.senderMessageText.setText(messages.getMessage());



            holder.constraintLayout_receiver.setVisibility(View.GONE);
            holder.receiverMessageText.setVisibility(View.INVISIBLE);
            holder.receiver_username.setVisibility(View.INVISIBLE);
            holder.receiver_date.setVisibility(View.INVISIBLE);
            holder.receiver_time.setVisibility(View.INVISIBLE);
            //holder.receiver_date.setText(messages.getTime());
            //holder.receiver_time.setText(messages.getDate());
           // holder.senderMessageText.setTextColor(Color.BLACK);
          //  holder.senderMessageText.setVisibility(View.VISIBLE);
            //holder.senderMessageText.setBackgroundResource(R.drawable.sender_messeges_layout);


        }else{

            holder.receiverMessageText.setVisibility(View.VISIBLE);
            holder.receiverMessageText.setBackgroundResource(R.drawable.receiver_messages_layout);
            holder.receiver_username.setText(messages.getName());
            holder.receiverMessageText.setText(messages.getMessage());
            holder.receiver_username.setVisibility(View.VISIBLE);
            holder.receiver_date.setVisibility(View.VISIBLE);
            holder.receiver_time.setVisibility(View.VISIBLE);
            holder.receiver_date.setText(messages.getTime());
            holder.receiver_time.setText(messages.getDate());


            //holder.sender_date.setVisibility(View.VISIBLE);
            //holder.sender_time.setVisibility(View.VISIBLE);
            holder.constraintLayout_sender.setVisibility(View.GONE);
            holder.senderMessageText.setVisibility(View.INVISIBLE);
            //holder.sender_date.setText(messages.getTime());
            //holder.sender_time.setText(messages.getDate());
        }
    }
    @Override
    public int getItemCount() {
        return userMessageList.size();
    }


}
