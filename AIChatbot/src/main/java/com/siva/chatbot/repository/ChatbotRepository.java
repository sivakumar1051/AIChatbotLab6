package com.siva.chatbot.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siva.chatbot.model.Conversations;
import com.siva.chatbot.util.ConnectionUtil;

public class ChatbotRepository {

	public void save(Conversations conversation) {
        String sql = "INSERT INTO conversations (user_query, chatbot_response, timestamp) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, conversation.getUserQuery());
            statement.setString(2, conversation.getChatbotResponse());
            statement.setTimestamp(3, conversation.getTimestamp());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conversations> findAll() {
        List<Conversations> conversations = new ArrayList<>();
        String sql = "SELECT * FROM conversations";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                Conversations conversation = new Conversations();
                conversation.setId(resultSet.getInt("id"));
                conversation.setUserQuery(resultSet.getString("user_query"));
                conversation.setChatbotResponse(resultSet.getString("chatbot_response"));
                conversation.setTimestamp(resultSet.getTimestamp("timestamp"));
                conversations.add(conversation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversations;
    }
}
