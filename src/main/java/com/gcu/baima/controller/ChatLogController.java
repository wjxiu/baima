package com.gcu.baima.controller;


import cn.hutool.core.net.Ipv4Util;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcu.baima.entity.ChatLog;
import com.gcu.baima.service.Back.ChatLogService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-05-08
 */
@Api(tags = "聊天记录控制器")
@RestController
@RequestMapping("/baima/chatlog")
public class ChatLogController {
    @Autowired
    ChatLogService chatLogService;

    @ApiOperation(value = "添加一条聊天记录", notes = "ip，sendTime不用传")
    @PostMapping("")
    public R add(@RequestBody ChatLog chatLog, HttpServletRequest request) {
//        设置ip,如果ip出错，默认值为1
        String remoteAddr = request.getRemoteAddr();
        long l = Ipv4Util.ipv4ToLong(remoteAddr, 1);
        chatLog.setFromIp(l);
        chatLogService.save(chatLog);
        return R.ok();
    }

    @PostMapping("/page/{pageNo}/{limit}")
    public R page(@PathVariable Long pageNo, @PathVariable Long limit, @RequestBody HashMap<String, String> map) {
        Page<ChatLog> chatLogPage = new Page<>(pageNo, limit);
        chatLogService.page(chatLogPage, null);
        return R.ok().data("page", chatLogPage);
    }
}

