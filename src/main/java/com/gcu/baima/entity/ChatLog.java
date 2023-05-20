package com.gcu.baima.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Data
@TableName("chat_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ChatLog对象", description="")
public class ChatLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "消息内容")
    private String context;

    @ApiModelProperty(value = "ip使用Int存储，保存时要转换")
    private Long fromIp;

    @ApiModelProperty(value = "注册用户id")
    private String customerId;

    @ApiModelProperty(value = "招生专员id")
    private String mangerId;
    @ApiModelProperty(value = "消息发送时间")
    @TableField(fill = FieldFill.INSERT)
    private Date sendTime;


    public void setId(String id) {
        this.id = id;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setFromIp(Long fromIp) {
        this.fromIp = fromIp;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setMangerId(String mangerId) {
        this.mangerId = mangerId;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
