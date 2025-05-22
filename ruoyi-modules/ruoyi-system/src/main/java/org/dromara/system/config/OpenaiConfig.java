package org.dromara.system.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.dromara.system.service.AiAssistant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenaiConfig {

    @Value("${llm.apiKey.openai}")
    private String apiKey;
    @Value("${llm.url.openai}")
    private String url;

    @Bean
    public ChatLanguageModel openAiChatModel() {
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
            .baseUrl(url)
            .apiKey(apiKey)
            .modelName("gpt-4.1-mini").build();
        return openAiChatModel;
    }

    @Bean
    public AiAssistant aiAssistant(ChatLanguageModel openAiChatModel){
        return AiServices.builder(AiAssistant.class)
            .chatLanguageModel(openAiChatModel)
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).chatMemoryStore(new InMemoryChatMemoryStore()).build())
            .build();
    }
}
