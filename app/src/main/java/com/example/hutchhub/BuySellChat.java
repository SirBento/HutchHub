package com.example.hutchhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hutchhub.Adapters.MessageAdapter;
import com.example.hutchhub.Models.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

public class BuySellChat extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference messagedb, userRef;
    FirebaseDatabase database;

    ImageButton BuySellChatSend_message_bnt;
    EditText BuySellChatInput_message;
    List<Message> message;
    MessageAdapter messageAdapter;
    RecyclerView BuySellChat_messages_list_of_users;
    LinearLayoutManager linearLayoutManager;

    Toolbar chatToolbar;
    String currentuserId, currentusername,CurrentTime,date, Displaymessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_chat);

        chatToolbar = findViewById(R.id.chat_toolbar);
        chatToolbar.setTitleTextColor(getResources().getColor(R.color.backgroundcolor));
        setSupportActionBar(chatToolbar);
        getSupportActionBar().setTitle("Buy & Sell Chat");

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentuserId =auth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        /**this hase to change to point a different node that contains buy sell messages**/
        messagedb = FirebaseDatabase.getInstance().getReference().child("Messages");
        getUserName();
        BuySellChat_messages_list_of_users = findViewById(R.id.BuySellChat_messages_list_of_users);
        BuySellChat_messages_list_of_users.setItemAnimator(new SlideInUpAnimator());
        BuySellChatSend_message_bnt = findViewById(R.id.BuySellChatSend_message_bnt);
        BuySellChatInput_message = findViewById(R.id.input_message);
        message = new ArrayList<>();
        messageAdapter = new MessageAdapter(message);
        linearLayoutManager = new LinearLayoutManager(this);
        BuySellChat_messages_list_of_users.setLayoutManager(linearLayoutManager);
        BuySellChat_messages_list_of_users.setAdapter(messageAdapter);




        BuySellChatSend_message_bnt.setOnClickListener(v -> {
            if(validateMessage()){
                SaveMessage();
                SendNotification(Displaymessage);
                BuySellChatInput_message.setText("");
            }

        });
    }

/** Change how the message is stored **/
    private void SaveMessage() {

        Displaymessage = BuySellChatInput_message.getText().toString();
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


    /** Change the first name  that is being taken to the one who wants to buy the rabbits **/
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

        String message = BuySellChatInput_message.getText().toString().trim();

        if (message.isEmpty()) {
            BuySellChatInput_message.setError("This field cannot be empty");
            BuySellChatInput_message.requestFocus();
            return false;

        } else {
            BuySellChatInput_message.setError(null);
            return true;

        }

    }


    private void SendNotification(String displaymessage) {
        /** send notification to each individual  among those who want to strike a deal  **/

    }

    void callApi(JSONObject jsonObject){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url = "https://fcm.googleapis.com/fcm/send";
        RequestBody body = RequestBody.create(jsonObject.toString(),JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization","Bearer 9018976dd8f78d5aa658d1d0cf1d5f76e561a224")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }
}