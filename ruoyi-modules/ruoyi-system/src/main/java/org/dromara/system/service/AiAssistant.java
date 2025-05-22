package org.dromara.system.service;

public interface AiAssistant {

    String testChat(String userMessage);

    String memmoryChat(Long userId, String userMessage);
}
