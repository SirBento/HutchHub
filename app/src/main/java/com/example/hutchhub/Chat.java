package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.hutchhub.Adapters.MessageAdapter;
import com.example.hutchhub.Models.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Chat extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference messagedb, userRef;
    FirebaseDatabase database;

    ImageButton sendButton;
    EditText input_message;
    List<Message> message;
    MessageAdapter messageAdapter;
    RecyclerView messageRecyclerView;
    LinearLayoutManager linearLayoutManager;
    Toolbar chatToolbar;
    String currentuserId, currentusername,CurrentTime,date, Displaymessage;

    int iconId = R.drawable.message;
    Uri iconUri = Uri.parse("android.resource://" + getPackageName() + "/" + iconId);
    String iconUrl = iconUri.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatToolbar = findViewById(R.id.chat_toolbar);
        chatToolbar.setTitleTextColor(getResources().getColor(R.color.backgroundcolor));
        setSupportActionBar(chatToolbar);
        getSupportActionBar().setTitle("Farmer's Community");


        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentuserId =auth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        messagedb = FirebaseDatabase.getInstance().getReference().child("Messages");
        getUserName();

        messageRecyclerView = findViewById(R.id.private_messages_list_of_users);
        messageRecyclerView.setItemAnimator(new SlideInUpAnimator());
        sendButton = findViewById(R.id.send_message_bnt);
        input_message = findViewById(R.id.input_message);
        message = new ArrayList<>();
        messageAdapter = new MessageAdapter(message);
        linearLayoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(messageAdapter);
//TODO: Fix chat scrolling

        //remove network restrictions NB should be in debug mode only
          int  SDK_INT = Build.VERSION.SDK_INT;
        if(SDK_INT >8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        sendButton.setOnClickListener(v -> {
            if(validateMessage()){
              SaveMessage();
              sendNotification();
              input_message.setText("");
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        messagedb.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                    {
                        if(dataSnapshot.exists()){

                            Message message1 = dataSnapshot.getValue(Message.class);
                            message.add(message1);
                            messageAdapter.notifyDataSetChanged();
                            messageRecyclerView.smoothScrollToPosition(messageRecyclerView.getAdapter().getItemCount());

                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        if(dataSnapshot.exists()){

                            Message message1 = dataSnapshot.getValue(Message.class);
                            message.add(message1);
                            messageAdapter.notifyDataSetChanged();
                            messageRecyclerView.smoothScrollToPosition(messageRecyclerView.getAdapter().getItemCount());

                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void SaveMessage() {

        Displaymessage = input_message.getText().toString();
        CurrentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        date =  new SimpleDateFormat("dd/LLL/yyyy", Locale.getDefault()).format(new Date());
        HashMap<String, Object> saveMessage = new HashMap<>();

        saveMessage.put("id",currentuserId);
        saveMessage.put("name",currentusername);
        saveMessage.put("message", Displaymessage);
        saveMessage.put("time",CurrentTime);
        saveMessage.put("date",date);
        messagedb.push().setValue(saveMessage);
    }

    private void getUserName() {
        userRef.child(currentuserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    currentusername = snapshot.child("firstname").getValue().toString();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean validateMessage() {

        String message = input_message.getText().toString().trim();

        if (message.isEmpty()) {
            input_message.setError("This field cannot be empty");
            input_message.requestFocus();
            return false;

        } else {
            input_message.setError(null);
            return true;

        }

    }


    //sending to a specific user

    private void sendNotification(){


        OkHttpClient client = new OkHttpClient();

        /*
          RequestBody body = RequestBody.create(mediaType,
            "{\"contents\":{\"en\":\"New Group Message\"},\"app_id\":\"93d4ec61-14a4-407f-a606-55d50aa98198\",\"included_segments\":[\"All\"],\"excluded_segments\":[\"device_id_to_exclude\"],\"large_icon\":\"" + iconUrl + "\"}");
        * */
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType,
                "{\"contents\":{\"en\":\"New Group Message\"},\"app_id\":\"93d4ec61-14a4-407f-a606-55d50aa98198\",\"included_segments\":[\"All\"],\"large_icon\":\"" + iconUrl + "\"}");

        Request request = new Request.Builder()
                .url("https://api.onesignal.com/api/v1/notifications")
                .post(body)
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Basic YzI5NzQxNTYtZTA0ZC00ZGVkLTkxYmEtMWJiYTE1NzU2ZTQ1")
                .addHeader ("Content-Type","application/json")
                .build();
        Response response;

        {
            try {
                response = client.newCall(request).execute();

                // Check response code and return message
                if (response.isSuccessful()) {
                    Log.e("Notification Response", "Notification sent successfully!");
                } else {
                    Log.e("Notification Response","Error sending notification: " + response.code() + " - " + response.body().string());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }







    /**  SENDING TO A SPECIFIC DEVICE

     private void sendMessage(String playerId) {
     OkHttpClient client = new OkHttpClient();

     MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
     RequestBody body = RequestBody.create(mediaType,
     "{\"contents\":{\"en\":\"New Group Message\"},\"app_id\":\"93d4ec61-14a4-407f-a606-55d50aa98198\",\"include_player_ids\":[\"" + playerId + "\"]}");

     Request request = new Request.Builder()
     .url("https://api.onesignal.com/api/v1/notifications")
     .post(body)
     .addHeader("Accept", "application/json")
     .addHeader("Authorization", "Basic YzI5NzQxNTYtZTA0ZC00ZGVkLTkxYmEtMWJiYTE1NzU2ZTQ1")
     .addHeader("Content-Type", "application/json")
     .build();

     Response response;

     try {
     response = client.newCall(request).execute();

     // Check response code and return message
     if (response.isSuccessful()) {
     Log.e("Notification Response", "Notification sent successfully!");
     } else {
     Log.e("Notification Response", "Error sending notification: " + response.code() + " - " + response.body().string());
     }

     } catch (IOException e) {
     throw new RuntimeException(e);
     }
     }
     * */

}