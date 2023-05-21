package com.gcu.baima.encoder;

import com.gcu.baima.model.BaseInfoModel;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author xiu
 * @create 2023-05-16 20:35
 */
public class BaseInfoModelEncoder implements Encoder.Text<BaseInfoModel> {
    @Override
    public String encode(BaseInfoModel baseInfoModel) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(baseInfoModel);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
