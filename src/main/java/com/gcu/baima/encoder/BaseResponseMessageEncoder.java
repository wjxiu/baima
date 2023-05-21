package com.gcu.baima.encoder;

import com.gcu.baima.model.BaseResponseMessage;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author xiu
 * @create 2023-05-16 22:07
 */
public class BaseResponseMessageEncoder implements Encoder.Text<BaseResponseMessage> {
    @Override
    public String encode(BaseResponseMessage baseResponseMessage) throws EncodeException {
        return new Gson().toJson(baseResponseMessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
