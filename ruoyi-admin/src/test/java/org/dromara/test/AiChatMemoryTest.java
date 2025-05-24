package org.dromara.test;

import jakarta.annotation.Resource;
import org.dromara.common.core.domain.R;
import org.dromara.system.domain.ChatMemoryDb;
import org.dromara.system.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class AiChatMemoryTest {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private AiAssistant aiAssistant;

    static Stream<Arguments> chatMemoryData() {
        return Stream.of(
            Arguments.of(1L, 1L, "来自 memoryId为1 的第1条内容"),
            Arguments.of(1L, 1L, "来自 memoryId为1  的第2条内容"),
            Arguments.of(2L, 1L, "来自 memoryId为2  的第1条内容"),
            Arguments.of(2L, 1L, "来自 memoryId为2  的第2条内容")
        );
    }

    @ParameterizedTest
    @MethodSource("chatMemoryData")
    public void test(Long memoryId, Long userId, String content) {
        ChatMemoryDb chatMemoryDb = new ChatMemoryDb();
        chatMemoryDb.setContent(content);
        chatMemoryDb.setMemoryId(memoryId);
        mongoTemplate.save(chatMemoryDb);
    }

    @Test
    public void testfind(){
        Query query = Query.query(Criteria.where("user_id").is(2L));
        List<ChatMemoryDb> userId = mongoTemplate.find(query, ChatMemoryDb.class);
        System.out.println(userId);
    }
    @Test
    public void test() {
        String json = aiAssistant.testChat("你好");
        System.out.println(json);
    }

    @Test
    public void testAiAssistant(){
        String json1 = aiAssistant.memmoryChat(1L,"你好, 我叫memory1");
        String json2 = aiAssistant.memmoryChat(2L,"你好, 我叫memory2");
        String json3 = aiAssistant.memmoryChat(1L,"你好, 我叫什么");
        String json4 = aiAssistant.memmoryChat(2L,"你好, 我叫什么");
    }
}
