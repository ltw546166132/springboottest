package org.dromara.test;

import jakarta.annotation.Resource;
import org.dromara.system.domain.ChatMemoryDb;
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
        chatMemoryDb.setUserId(userId);
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
}
