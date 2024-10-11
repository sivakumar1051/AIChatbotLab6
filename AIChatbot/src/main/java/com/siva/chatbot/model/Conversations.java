package com.siva.chatbot.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="conversations")
public class Conversations {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}

	public String getChatbotResponse() {
		return chatbotResponse;
	}

	public void setChatbotResponse(String chatbotResponse) {
		this.chatbotResponse = chatbotResponse;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_query")
    private String userQuery;

    @Column(name = "chatbot_response")
    private String chatbotResponse;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
