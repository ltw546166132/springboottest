package org.dromara.system.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.dromara.system.service.AiAssistant;
import org.dromara.system.service.AiTools;
import org.dromara.system.service.MongoChatMemoryStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenaiConfig {
    @Value("${llm.apiKey.openai}")
    private String apiKey;
    @Value("${llm.url.openai}")
    private String url;
    @Resource
    private AiTools aiTools;

    @Bean
    public ChatLanguageModel openAiChatModel() {
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
            .baseUrl(url)
            .apiKey(apiKey)
            .modelName("gpt-4o-mini").build();
        return openAiChatModel;
    }

    /**
     * AIService配置
     * @param openAiChatModel
     * @param mongoChatMemoryStore
     * @return
     */
    @Bean
    public AiAssistant aiAssistant(ChatLanguageModel openAiChatModel, MongoChatMemoryStore mongoChatMemoryStore){
        return AiServices.builder(AiAssistant.class)
            .chatLanguageModel(openAiChatModel)
//            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).chatMemoryStore(new InMemoryChatMemoryStore()).build())
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(30).chatMemoryStore(mongoChatMemoryStore).build())
            .tools(List.of(aiTools))
            .build();
    }
}
