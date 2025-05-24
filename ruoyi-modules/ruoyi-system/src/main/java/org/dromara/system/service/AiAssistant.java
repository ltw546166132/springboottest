package org.dromara.system.service;

import dev.langchain4j.service.*;

public interface AiAssistant {

    String testChat(String userMessage);

    @SystemMessage("{{prompt}}")
    String promptChat(@V("prompt") String prompt, @MemoryId Long memoryId, @UserName Long userId,  @UserMessage String userMessage);

    String memmoryChat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
