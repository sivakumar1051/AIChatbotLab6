<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AI-Powered Chatbot</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to the external CSS file -->
</head>
<body>
    <h1>AI-Powered Chatbot</h1>

    <div class="chat-container">
    <c:forEach var="conversation" items="${chatHistory}">
        <div class="message user">
            ${conversation.userQuery}
        </div>
        <div class="message chatbot">
            ${conversation.chatbotResponse}
        </div>
    </c:forEach>
</div>


    <form action="chat" method="post" class="chat-form">
        <input type="text" id="userInput" name="userInput" placeholder="Type your question..." required>
        <button type="submit">Send</button>
    </form>
</body>
</html>
