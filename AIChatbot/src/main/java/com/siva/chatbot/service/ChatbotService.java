package com.siva.chatbot.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.siva.chatbot.model.Conversations;
import com.siva.chatbot.repository.ChatbotRepository;

public class ChatbotService {

    private ChatbotRepository chatRepository;
    private Map<String, String> queryKeywords;

    public ChatbotService() {
        chatRepository = new ChatbotRepository();
        queryKeywords = new HashMap<>();
        initializePredefinedResponses();
    }

    private void initializePredefinedResponses() {
    	queryKeywords.put("hello", "Hello! How can I assist you with Java or any other topic today?");
        
        // Java Basics
    	queryKeywords.put("what is java", "Java is a high-level, object-oriented programming language developed by Sun Microsystems, now owned by Oracle.");
    	queryKeywords.put("what is jvm", "JVM stands for Java Virtual Machine, which allows Java programs to run on any platform by converting bytecode into machine code.");
    	queryKeywords.put("what is jre", "JRE stands for Java Runtime Environment, which provides the libraries and resources needed to run Java applications.");
    	queryKeywords.put("what is jdk", "JDK stands for Java Development Kit, which includes JRE as well as tools for developing, debugging, and compiling Java applications.");

        // Java Concepts
    	queryKeywords.put("what is polymorphism", "Polymorphism in Java allows objects to be treated as instances of their parent class. It supports method overriding and overloading.");
        queryKeywords.put("what is inheritance", "Inheritance is a mechanism in Java where a class can inherit properties and behavior from a parent class.");
        queryKeywords.put("what is encapsulation", "Encapsulation is the practice of hiding the internal details of a class and only exposing necessary parts through public methods.");
        queryKeywords.put("what is abstraction", "Abstraction allows you to focus on what an object does rather than how it does it. It is achieved using abstract classes and interfaces.");
        
        // Java Frameworks
        queryKeywords.put("what is spring", "Spring is a comprehensive framework used for building Java applications. It offers features for dependency injection, AOP, and more.");
        queryKeywords.put("what is hibernate", "Hibernate is an ORM framework for mapping Java objects to database tables. It simplifies database operations using HQL and native SQL.");
        queryKeywords.put("what is spring boot", "Spring Boot is a framework that simplifies the development of Spring applications by providing pre-configured settings and reducing boilerplate code.");
        queryKeywords.put("what is maven", "Maven is a build automation tool primarily used for Java projects. It simplifies project configuration and dependency management.");
        queryKeywords.put("what is gradle", "Gradle is a powerful build automation tool for Java and Android projects, providing flexibility over Maven's convention-based system.");
        
        // Java Data Structures and Algorithms
        queryKeywords.put("what is a hash map", "A HashMap is a data structure in Java that allows storing key-value pairs. It provides constant-time complexity for basic operations.");
        queryKeywords.put("what is a linked list", "A LinkedList is a linear data structure in Java where elements are stored in nodes. Each node points to the next, allowing efficient insertions and deletions.");
        queryKeywords.put("what is recursion", "Recursion is a technique where a function calls itself to solve a problem. It's commonly used in divide-and-conquer algorithms.");
        
        // Java Concurrency
        queryKeywords.put("what is multithreading", "Multithreading in Java allows the concurrent execution of two or more parts of a program to maximize CPU utilization.");
        queryKeywords.put("what is synchronized in java", "The synchronized keyword in Java is used to control access to a block of code by multiple threads, preventing race conditions.");
        queryKeywords.put("what is an executor service", "ExecutorService is a higher-level replacement for working with threads directly. It helps manage and control a pool of threads.");
        
        // Advanced Java
        queryKeywords.put("what is lambda expression", "A Lambda expression in Java provides a concise way to represent an anonymous function, making code more functional and less verbose.");
        queryKeywords.put("what is stream api", "The Stream API is a feature in Java 8 that allows functional-style operations on collections, making it easier to work with data in a declarative way.");
        queryKeywords.put("what is optional in java", "Optional is a container object introduced in Java 8 to handle null values gracefully and avoid NullPointerExceptions.");

        // Spring Boot Concepts
        queryKeywords.put("what is spring boot", "Spring Boot is a framework that simplifies the development of Spring applications by providing default configurations, auto-configuration, and embedded servers.");
        queryKeywords.put("what is auto-configuration", "Auto-configuration in Spring Boot automatically configures your application based on the libraries on the classpath, reducing the need for manual configurations.");
        queryKeywords.put("what is a Spring Boot starter", "Spring Boot starters are a set of convenient dependency descriptors you can include in your application. For example, 'spring-boot-starter-web' helps build web applications quickly.");
        queryKeywords.put("what is Spring Boot Actuator", "Spring Boot Actuator provides production-ready features, including metrics, health checks, and application monitoring, to help manage and monitor your Spring Boot application.");
        queryKeywords.put("what is Spring Boot DevTools", "Spring Boot DevTools provides additional development-time features such as automatic restarts, live reload, and configuration for improved development experience.");
        queryKeywords.put("what is a Spring Boot application", "A Spring Boot application is a standalone application that can be run independently without needing an external web server, thanks to the embedded server capabilities.");
        queryKeywords.put("what is @SpringBootApplication", "The @SpringBootApplication annotation is a convenience annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan, making it easier to set up a Spring Boot application.");
        queryKeywords.put("what is a Spring Boot profile", "Spring Boot profiles allow you to define different configurations for different environments (e.g., development, testing, production) using application-{profile}.properties files.");
        queryKeywords.put("what is Spring Data JPA", "Spring Data JPA simplifies database access and integrates with Spring Boot to provide a convenient way to create repository layers and manage database operations.");
        queryKeywords.put("what is Spring Security", "Spring Security is a powerful and customizable authentication and access control framework for Java applications, providing comprehensive security features for web applications.");

        // Default response for unmatched queries
    }
	/*
	 * public void saveConversation(Conversations conversation) {
	 * conversation.setTimestamp(new Timestamp(System.currentTimeMillis())); // Set
	 * the current timestamp chatRepository.save(conversation); }
	 */

	
    public void getChatbotResponse(String userInput) {
        String lowerCaseInput = userInput.toLowerCase();
        String outResponse = null;

        // Check if the user input contains in an predefined keyword map or no
        for (String keyword : queryKeywords.keySet()) {
            if (lowerCaseInput.contains(keyword.toLowerCase()) || lowerCaseInput.equals(keyword.toLowerCase())) {
                outResponse = queryKeywords.get(keyword);
                break; 
            }
        }

        // If a response was generated from a keyword, save the conversation
        if (outResponse != null) {
            // Create a Conversations object to save
            Conversations conversation = new Conversations();
            conversation.setUserQuery(userInput);
            conversation.setChatbotResponse(outResponse);
            conversation.setTimestamp(new Timestamp(System.currentTimeMillis())); 
            
            // Save the conversation to the database
            chatRepository.save(conversation);
           
        }else {

        // Default response if no match found 
        String defaultResponse = "I'm not sure how to answer that.Talk to an agent for more information";
        
        // Saving  the default response to the database
        Conversations defaultConversation = new Conversations();
        defaultConversation.setUserQuery(userInput);
        defaultConversation.setChatbotResponse(defaultResponse);
        defaultConversation.setTimestamp(new Timestamp(System.currentTimeMillis())); 

    
         chatRepository.save(defaultConversation);
        }
        
        
    }
    
    // method to get recent conversations
    public List<Conversations> getRecentConversations() {
        List<Conversations> allConversations = chatRepository.findAll();

        
        return allConversations;
    
    }
}
