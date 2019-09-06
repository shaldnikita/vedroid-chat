package ru.shaldnikita.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<String> messages;

    //xml to view
    private LayoutInflater inflater;


    DataAdapter(Context context, ArrayList<String> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }


    //Методы для логики взаимодействия DataAdapter с RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    //Прокуртка по позиции
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setMessageTest(messages.get(position));
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }
}
