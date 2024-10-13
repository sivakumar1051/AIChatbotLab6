package com.siva.chatbot.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.siva.chatbot.model.Conversations;
import com.siva.chatbot.service.ChatbotService;


public class ChatbotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ChatbotService chatbotService;

    @Override
    public void init() {
        chatbotService = new ChatbotService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    	  // getting chat history 
        List<Conversations> recentConversations = chatbotService.getRecentConversations();
        request.setAttribute("chatHistory", recentConversations);

        request.getRequestDispatcher("chatbot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        // finding solution for user query
        chatbotService.getChatbotResponse(userInput);

       // getting chat history 
        List<Conversations> recentConversations = chatbotService.getRecentConversations();
        request.setAttribute("chatHistory", recentConversations);

        // returning response 
        request.getRequestDispatcher("chatbot.jsp").forward(request, response);
    }
}
