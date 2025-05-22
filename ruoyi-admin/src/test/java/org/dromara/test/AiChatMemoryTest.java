package org.dromara.test;

import jakarta.annotation.Resource;
import org.dromara.system.domain.ChatMemoryDb;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class AiChatMemoryTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        ChatMemoryDb chatMemoryDb = new ChatMemoryDb();
        chatMemoryDb.setUserId(2L);
        chatMemoryDb.setContent("测试2");
        mongoTemplate.save(chatMemoryDb);
    }
}
