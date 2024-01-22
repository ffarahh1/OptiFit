package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class Cards_videos_list extends AppCompatActivity {

    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_videos_list);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        readItemsFromCsv();

        recyclerView.setAdapter(new ItemAdapter(items));
    }

    private void readItemsFromCsv() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("exercises.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    items.add(new Item(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Item {
        String text;
        String url;

        Item(String text, String url) {
            this.text = text;
            this.url = url;
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
        private final List<Item> items;

        ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item item = items.get(position);
            holder.text.setText(item.text);

            //System.out.println("1111TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST");
            holder.itemView.setOnClickListener(v -> {
                //System.out.println("videoUrl"+ item.url);
                Intent intent = new Intent(Cards_videos_list.this, VideoPlayerActivity.class);
                intent.putExtra("videoUrl", item.url);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView text;

            ItemViewHolder(View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.item_text);
            }
        }
    }
}