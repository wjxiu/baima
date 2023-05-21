package com.gcu.baima.service.Back.impl;

import cn.hutool.core.net.Ipv4Util;
import com.gcu.baima.entity.ChatLog;
import com.gcu.baima.mapper.ChatLogMapper;
import com.gcu.baima.service.Back.ChatLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Service
public class ChatLogServiceImpl extends ServiceImpl<ChatLogMapper, ChatLog> implements ChatLogService {
//    添加一条聊天记录
@Override
public void add(ChatLog chatLog, HttpServletRequest request) {
    //        设置ip,如果ip出错，默认值为1
    String remoteAddr = request.getRemoteAddr();
    long l = Ipv4Util.ipv4ToLong(remoteAddr, 1);
    chatLog.setFromIp(l);
    save(chatLog);
}
}
