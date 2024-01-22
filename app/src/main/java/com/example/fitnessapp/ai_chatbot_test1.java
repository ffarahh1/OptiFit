package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.ChatAdapter;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
//import com.openai.gpt3.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.os.Bundle;

public class ai_chatbot_test1 extends AppCompatActivity {
    private Message userMessage;
    private Message modelMessage;
    private RecyclerView recyclerView;
    private EditText inputText;
    private Button sendButton;
    private List<Message> chatHistory;
    private ChatAdapter adapter; // Add this line
//    private String apiKey = "";
//    private GenerativeModel gm = new GenerativeModel("gemini-pro", apiKey);
//    private GenerativeModelFutures model = GenerativeModelFutures.from(gm);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_chatbot_test1);

        recyclerView = findViewById(R.id.recyclerView);
        inputText = findViewById(R.id.inputText);
        sendButton = findViewById(R.id.sendButton);
        chatHistory = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView
        adapter = new ChatAdapter(chatHistory);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        userMessage = new Message("How can I help you? | كيف يمكنني مساعدتك؟", "model");
        chatHistory.add(userMessage);
        adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
        recyclerView.scrollToPosition(chatHistory.size() - 1);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = inputText.getText().toString();
                inputText.setText("");

                // Add user input to chat history
                userMessage = new Message(userInput, "user");
                chatHistory.add(userMessage);

                adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
                recyclerView.scrollToPosition(chatHistory.size() - 1);


                // Send user input to AI model
                sendMessageToModel(userInput);
            }
        });
    }

    private void sendMessageToModel(String userInput) {
        // Initialize the model
        String apiKey = "Enter your key";

        // For text-only input, use the gemini-pro model
        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-pro",apiKey);
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText(userInput)
                .build();

        System.out.println(userInput);

        Executor executor = Executors.newSingleThreadExecutor();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                System.out.println(resultText);
                modelMessage = new Message(resultText, "Ai");
                chatHistory.add(modelMessage);
//                adapter.notifyDataSetChanged();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
                        recyclerView.scrollToPosition(chatHistory.size() - 1);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                System.out.println("Error Occured ");
            }
        }, executor);
//        GenerativeModel gm = new GenerativeModel("gemini-pro", apiKey);
//        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
//
//        // Create previous chat history for context
//        System.out.println(userInput);
//        Content.Builder userContentBuilder = new Content.Builder();
//        userContentBuilder.setRole("user");
//        userContentBuilder.addText(userInput);
//        Content userContent = userContentBuilder.build();
//
//        List<Content> history = Arrays.asList(userContent);
//        // Initialize the chat
//        ChatFutures chat = model.startChat(history);
//        // Create a new user message
//        System.out.println(userInput);
//        Content CuserMessage = new Content.Builder()
//                .addText(userInput)
//                .build();
//        Executor executor = Executors.newSingleThreadExecutor();
//        //Executor executor = Executors.newFixedThreadPool(4); // 4 threads in the pool
//                // Send the message
//        ListenableFuture<GenerateContentResponse> response = chat.sendMessage(CuserMessage);
//        System.out.println(response);
//        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
//            @Override
//            public void onSuccess(GenerateContentResponse result) {
//                String resultText = result.getText();
//                // Add model response to chat history
//                modelMessage = new Message(resultText, "model");
//                System.out.println(modelMessage);
//                chatHistory.add(modelMessage);
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("Failed AI");
//                userMessage = new Message("Error Occured", "model");
//                chatHistory.add(userMessage);
//                adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
//                t.printStackTrace();
//            }
//        }, executor);
    }
    public class Message {
        private String text;
        private String role;

        public Message(String text, String role) {
            this.text = text;
            this.role = role;
        }

        // Getter method for 'text'
        public String getText() {
            return text;
        }

        // If needed, add a getter method for 'role' as well
        public String getRole() {
            return role;
        }
    }



}

