package org.dromara.system.service;

import cn.hutool.core.util.ObjectUtil;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.dromara.system.domain.ChatMemoryDb;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MongoChatMemoryStore implements ChatMemoryStore {
    @Resource
    private MongoTemplate mongoTemplate;
    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Query query = Query.query(Criteria.where("memory_id").is(memoryId));
        ChatMemoryDb chatMemoryDbs = mongoTemplate.findOne(query, ChatMemoryDb.class);
        if(ObjectUtil.isNull(chatMemoryDbs)){
            return List.of();
        }
        String content = chatMemoryDbs.getContent();
        return ChatMessageDeserializer.messagesFromJson(content);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        Query query = Query.query(Criteria.where("memory_id").is(memoryId));
        ChatMemoryDb chatMemoryDbs = mongoTemplate.findOne(query, ChatMemoryDb.class);
        if(ObjectUtil.isNotNull(chatMemoryDbs)){
            String s = ChatMessageSerializer.messagesToJson(messages);
            chatMemoryDbs.setContent(s);
            mongoTemplate.save(chatMemoryDbs);
        }else{
            ChatMemoryDb chatMemoryDb = new ChatMemoryDb();
            chatMemoryDb.setMemoryId(Long.parseLong(memoryId.toString()));
            chatMemoryDb.setContent(ChatMessageSerializer.messagesToJson(messages));
            mongoTemplate.save(chatMemoryDb);
        }
    }

    @Override
    public void deleteMessages(Object memoryId) {
        mongoTemplate.remove(Query.query(Criteria.where("memory_id").is(memoryId)), ChatMemoryDb.class);
    }
}
