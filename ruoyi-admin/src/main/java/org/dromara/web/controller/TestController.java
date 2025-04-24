package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.dromara.common.core.domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ai模型接入Controller
 */
@SaIgnore
@RestController
public class TestController {
    @Resource
    private OpenAiChatModel openAiChatModel;

    /**
     * 测试ai模型接入
     * @return
     */
    @PostMapping("/aichat")
    public R<Void> test() {
        String json = openAiChatModel.chat("你好");
        return R.ok(json);
    }
}
