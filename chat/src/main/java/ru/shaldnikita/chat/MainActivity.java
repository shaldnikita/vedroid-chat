package ru.shaldnikita.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.chat.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static int MAX_LENGTH_MESSAGE = 150;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    EditText editTextMessage;
    Button sendButton;
    RecyclerView messageRecycler;


    ArrayList<String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_message_b);
        messageRecycler = findViewById(R.id.message_recycler);


        messageRecycler.setLayoutManager(new LinearLayoutManager(this));


        //Использование созданного адаптера
        final DataAdapter dataAdapter = new DataAdapter(this, messages );
        messageRecycler.setAdapter(dataAdapter);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString();
                //Проверка на исключения
                if (message.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.ToastHintNull, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (message.length()>MAX_LENGTH_MESSAGE){
                    Toast.makeText(getApplicationContext(), R.string.ToastHintSoLong, Toast.LENGTH_SHORT).show();
                    return;
                }

                //Запись в БД
                myRef.push().setValue(message);
                editTextMessage.setText("");

            }
        });

        //Извлечение из БД
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String message = dataSnapshot.getValue(String.class);
                messages.add(message);
                dataAdapter.notifyDataSetChanged();
                messageRecycler.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

}
