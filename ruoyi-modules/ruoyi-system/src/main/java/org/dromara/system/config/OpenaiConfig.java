package org.dromara.system.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
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
    public OpenAiChatModel openAiChatModel() {
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
            .baseUrl(url)
            .apiKey(apiKey)
            .modelName("gpt-4.1-mini").build();
        return openAiChatModel;
    }
}
