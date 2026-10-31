
package com.LibraryManagement.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class Aiservice {

    private final ChatClient chatClient;

    public Aiservice(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String askGemini(String topic) {
        try {
            return chatClient.prompt()
                    .system("""
                            You are a helpful AI assistant
                            for a Library Management System.
                            Give clear and short useful answers about
                            books, authors, genres, and reading
                            recommendations.
                            """)
                    .user("Regarding the book or topic '" + topic
                            + "', provide a brief summary and "
                            + "three useful recommendations.")
                    .call()
                    .content();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "AI request failed: " + ex.getMessage();
        }
    }
}
