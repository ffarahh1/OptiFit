package com.example.fitnessapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.fitnessapp.ai_chatbot_test1.Message;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Message> chatHistory;

    public ChatAdapter(List<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = chatHistory.get(position);
        holder.textView.setText(message.getText());
        // Add more code here to display the message correctly
        if ("user".equals(message.getRole())) {
            holder.itemView.setBackgroundResource(R.color.user_message_background);
        } else {
            holder.itemView.setBackgroundResource(R.color.bot_message_background);
        }
    }

    @Override
    public int getItemCount() {
        return chatHistory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            // Initialize more views here if needed
        }
    }
}

