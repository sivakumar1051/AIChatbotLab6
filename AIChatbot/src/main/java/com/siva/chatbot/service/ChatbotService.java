package com.siva.chatbot.service;

import java.sql.Timestamp;
import java.util.List;

import com.siva.chatbot.model.Conversations;
import com.siva.chatbot.repository.ChatbotRepository;

public class ChatbotService {

	private ChatbotRepository chatRepository;

    public ChatbotService() {
    	chatRepository = new ChatbotRepository();
    }

    public void saveConversation(Conversations conversation) {
        conversation.setTimestamp(new Timestamp(System.currentTimeMillis())); // Set the current timestamp
        chatRepository.save(conversation);
    }

    public List<Conversations> getAllConversations() {
        return chatRepository.findAll();
    }
}
