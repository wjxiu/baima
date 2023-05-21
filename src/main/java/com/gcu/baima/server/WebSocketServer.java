package com.gcu.baima.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.gcu.baima.encoder.BaseInfoModelEncoder;
import com.gcu.baima.encoder.BaseResponseMessageEncoder;
import com.gcu.baima.model.BaseResponseMessage;
import com.gcu.baima.model.UserMessageModel;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiu
 * @create 2023-05-16 19:51
 */
@Slf4j
@Component
@ServerEndpoint(value = "/api/websocket/{type}/{id}", encoders = {BaseInfoModelEncoder.class, BaseResponseMessageEncoder.class})
public class WebSocketServer {
    //    在线所有链接 id---在线用户和客服的socket的session
    public static HashMap<String, Session> sessionMap = new HashMap<>();
    //    保存客服map
    public static HashMap<String, Session> KFMap = new HashMap<>();
    public String id;
    //    在线用户计数器
    public static AtomicInteger onlineClient = new AtomicInteger();
    public Session session;
//   todo 下线的用户的待接受的消息列表,key:用户id,value:待接受的消息
//    public static HashMap<String, Queue<UserMessageModel>> offlineUserMessMap = new HashMap<>();

    //    用户对应的客服
    private static HashMap<String, String> bindKfClients = new HashMap<>();

    @OnOpen()
    public void OnOpen(Session session, @PathParam("id") String clientId, @PathParam("type") Integer type) {
        this.id = clientId;
        this.session = session;
        UserMessageModel userMessageModel = new UserMessageModel();
        userMessageModel.setMessage("你的id为:" + clientId);
        userMessageModel.setFromId(clientId);
        if (type == 0) {
            userMessageModel.setToType("KF");
            userMessageModel.setFromType("USER");
        } else {
            userMessageModel.setToType("USER");
            userMessageModel.setFromType("KF");
            KFMap.put(clientId, session);
        }

        String json = new Gson().toJson(userMessageModel);
        sendMessage(json);
        if (!sessionMap.containsKey(clientId)) {
            onlineClient.addAndGet(1);
            sessionMap.put(clientId, session);
        }
        /**
         * todo 消息补偿
         */
//        if (!CollectionUtils.isEmpty(offlineUserMessMap)&&offlineUserMessMap.containsKey(clientId)) {
//            Queue<UserMessageModel> userMessageModels = offlineUserMessMap.get(clientId);
//            UserMessageModel poll ;
////           按照入队顺序发送消息
//            while ((poll = userMessageModels.poll())!=null){
//                sendMessage(poll);
//            }
////            移出下线用户消息队列
//            offlineUserMessMap.remove(clientId);
//        }


        log.info("当前人数为：{}", onlineClient.get());
        log.info("链接成功,id:{}", clientId);
    }

    //用户发送第一条信息，客服回复一条信息，会出bug,客服没收到，但是用户收到，并且客服回复的fromid和toId一样
    @OnMessage()
    public void OnMessage(Session session, String mess, @PathParam("id") String clientId) {
        Gson gson = new Gson();
        UserMessageModel userMessageModel = new Gson().fromJson(mess, UserMessageModel.class);
        String fromId = userMessageModel.getFromId();
        String fromType = userMessageModel.getFromType();
        String message = userMessageModel.getMessage();
        if (mess == null) {
            this.sendMessage(BaseResponseMessage.error("", "传递参数结构异常"));
            return;
        }
//        if (!sessionMap.containsKey(acceptId)){
////            添加到离线用户消息列表
//            Queue<UserMessageModel> list = offlineUserMessMap.getOrDefault(acceptId, new LinkedList<>());
//            list.add(userMessageModel);
//            offlineUserMessMap.put(acceptId,list);
//            sendMessage(BaseResponseMessage.error("", "接收端不在线"));
//            log.info("客户端:{} 发送消息到接受端:{} 不在线，放置到代发送列表，当前待发送列表:{}条",clientId, acceptId,list.size());
//        }else{
//        }
//            todo 将userMessageModel的acceptid改为从客服map中随机挑选一个id

//            是用户
        if (fromType.equalsIgnoreCase("USER")) {
//            绑定客服
            String bindKFId = userBindRandomKF(fromId);
            Session KFSession = sessionMap.get(bindKFId);
            userMessageModel.setToType("KF");
            userMessageModel.setFromType("USER");


            userMessageModel.setToId(bindKFId);
            if (KFMap.isEmpty() || KFSession == null) {
                userMessageModel.setMessage("非常抱歉，你的客服或全部客服已经离线😭😭😭");
            }
            String json = gson.toJson(userMessageModel);
            log.info("客户发送给客服的消息{}", json);
//            发送消息给自己
            sendMessage(json);
            if (!KFMap.isEmpty() && KFSession != null) {
//            发送消息给客服
                sendMessage(KFSession, json);
            }
        } else {
//              是客服
            userMessageModel.setFromType("KF");
            userMessageModel.setToType("USER");

//            String toId = userMessageModel.getToId();
//            userMessageModel.setToId(userMessageModel.getFromId());
            userMessageModel.setFromId(id);
            String json = gson.toJson(userMessageModel);
            log.info("客服发送给客户的消息{}", json);
            if (!sessionMap.containsKey(userMessageModel.getToId())) {
                userMessageModel.setMessage("非常抱歉，用户已经下线或没有这名用户😭😭😭");
                String s = gson.toJson(userMessageModel);
                sendMessage(s);
            } else {
                sendMessage(sessionMap.get(userMessageModel.getToId()), json);
                sendMessage(json);
            }
        }
    }

    @OnClose()
    public void OnClose(Session session, @PathParam("id") String clientId) {
        sessionMap.remove(clientId);
        onlineClient.decrementAndGet();
        this.session = null;
        bindKfClients.remove(clientId);
        KFMap.remove(clientId);
        log.info("信息:id为{}关闭链接", clientId);
        log.info("当前人数为：{}", onlineClient.get());
    }


    public void sendMessage(Session session, Object mess) {
        try {
            if (mess instanceof String) {
                session.getBasicRemote().sendText(mess.toString());
            } else {
                session.getBasicRemote().sendObject(mess);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    //               todo 持久化消息
    public void sendMessage(Object mess) {
        try {
            if (mess instanceof String) {
                session.getBasicRemote().sendText(mess.toString());
            } else {
                session.getBasicRemote().sendObject(mess);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    //
    public String getrandomKFId(HashMap<String, Session> map) {
        if (map.isEmpty()) {
            throw new RuntimeException("没有客服在线");
        }
        List<String> list = new ArrayList<String>(map.keySet());
        int i = RandomUtil.randomInt(0, list.size());
        return list.get(i);
    }
//    public String getUserSessionFromKFId(String KFId) {
//        boolean b = bindKfClients.containsValue(KFId);
//        if (!b) return null;
//        final String[] userKey = {""};
//        bindKfClients.forEach((key, value) -> {
//            if (value.equals(KFId)) {
//                userKey[0] = key;
//            }
//        });
//        return userKey[0];
//    }

    //    用户随机绑定一个客服，直到用户或客服下线
    public String userBindRandomKF(String userId) {
        //            否，绑定一个客服
        Boolean flag = true;
        if (CollectionUtil.isEmpty(KFMap)) {
            return null;
        }
        String KFid = bindKfClients.get(userId);
        int count = 0;
//        循环找出在线用户
        while (flag || count < KFMap.size() || StringUtils.isEmpty(KFid) || !KFMap.containsKey(KFid) || KFMap.get(KFid) == null) {
//            退出死循环
            count++;
            Set<String> keys = KFMap.keySet();

            List<String> list = new ArrayList<String>(keys);
            int i = RandomUtil.randomInt(0, list.size());
            KFid = list.get(i);
//            不符合情况的客服：下线的客服或者不存在这个客服
            flag = !KFMap.containsKey(KFid) || KFMap.get(KFid) == null;
            bindKfClients.put(userId, KFid);
        }
        KFid = bindKfClients.get(userId);

//            获取自己的客服
        return KFid;
    }
}
