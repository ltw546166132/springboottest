package org.dromara.system.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.UserName;
import dev.langchain4j.service.V;

public interface AiAssistant {

    String testChat(String userMessage);

    String memmoryChat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
