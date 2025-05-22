package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.dromara.common.core.domain.R;
import org.dromara.system.service.AiAssistant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ai模型接入Controller
 */
@SaIgnore
@RestController
public class TestController {
    @Resource
    private ChatLanguageModel openAiChatModel;
    @Resource
    private AiAssistant aiAssistant;

    /**
     * 测试ai模型接入
     * @return
     */
    @PostMapping("/aichat")
    public R<Void> test() {
        String json = openAiChatModel.chat("你好");
        aiAssistant.testChat("hello");
        return R.ok(json);
    }

    @PostMapping("/memmoryChat")
    public R<Void> memmoryChat() {
        String json = aiAssistant.memmoryChat(1L, "你好");
        aiAssistant.memmoryChat(2L, "你好");
        return R.ok(json);
    }
}
