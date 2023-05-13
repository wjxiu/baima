package com.gcu.baima.service.Front;

import com.gcu.baima.entity.Customer;
import com.gcu.baima.entity.VO.LoginVo;

/**
 * @author xiu
 * @create 2023-05-12 9:37
 */
public interface FrontSignService {
    String loginFront(LoginVo loginVo);

    void regiesterFront(LoginVo loginVo);

    Customer getCustomerByUserName(String UserName);
}
