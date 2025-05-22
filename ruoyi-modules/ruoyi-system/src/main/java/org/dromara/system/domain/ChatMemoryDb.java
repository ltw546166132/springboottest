package org.dromara.system.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("chat_memory")
public class ChatMemoryDb {
    @Id
    private ObjectId id;

    private Long userId;

    private String content;
}
