package org.dromara.system.service;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import dev.langchain4j.service.UserName;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component
public class AiTools {
    @Tool(name = "sum", value = "可以为ai提供数字加法计算能力, 返回结果四舍五入保留3位小数")
    public BigDecimal sum(@ToolMemoryId Long memoryId, @UserName Long userId, @P("参数1") BigDecimal a, @P("参数2") BigDecimal b){
        System.out.println(memoryId);
        System.out.println(userId);
        System.out.println(NumberUtil.add(a,b));
        return a.add(b);
    }
}
