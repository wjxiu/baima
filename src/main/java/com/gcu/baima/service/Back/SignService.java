package com.gcu.baima.service.Back;

import com.gcu.baima.entity.Manager;
import com.gcu.baima.entity.VO.LoginVo;

/**
 * @author xiu
 * @create 2023-05-11 20:26
 */
public interface SignService {
    String loginBack(LoginVo loginVo);

    //    注册,密码使用bcrypt加密
    void regiester(Manager manager);
}
