package com.siva.chatbot.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Conversations> chatHistory = chatbotService.getAllConversations();
	    request.setAttribute("chatHistory", chatHistory);
	    request.getRequestDispatcher("chatbot.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");
        
        // Simulate chatbot response (you can integrate with actual AI logic here)
        String chatbotResponse = generateChatbotResponse(userInput);

        // Save conversation to the database
        Conversations conversation = new Conversations();
        conversation.setUserQuery(userInput);
        conversation.setChatbotResponse(chatbotResponse);
        chatbotService.saveConversation(conversation);

        // Redirect back to the chatbot JSP page
        response.sendRedirect("chatbot.jsp");
    }

    private String generateChatbotResponse(String userInput) {
        // Simulated chatbot response logic
        return "You said: " + userInput; // Replace with your AI logic
    }

}
