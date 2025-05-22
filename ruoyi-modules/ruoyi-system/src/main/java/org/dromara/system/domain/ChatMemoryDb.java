package org.dromara.system.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("chat_memory")
public class ChatMemoryDb {
    @Id
    private ObjectId id;

    @Field("memory_id")
    private Long memoryId;

    @Field("user_id")
    private Long userId;

    private String content;
}
