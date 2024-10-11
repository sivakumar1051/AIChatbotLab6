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
