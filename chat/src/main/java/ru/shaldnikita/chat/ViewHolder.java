package ru.shaldnikita.chat;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView message;

    ViewHolder(View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.message_item);
    }

    void setMessageTest(String text) {
        message.setText(text);
    }
}
