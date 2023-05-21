package com.gcu.baima.service.Back;

import com.gcu.baima.entity.ChatLog;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
public interface ChatLogService extends IService<ChatLog> {

    void add(ChatLog chatLog, HttpServletRequest request);
}
