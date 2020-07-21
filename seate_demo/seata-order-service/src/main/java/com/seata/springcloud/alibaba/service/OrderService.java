package com.seata.springcloud.alibaba.service;

import com.seata.springcloud.alibaba.domain.Order;

public interface OrderService {
    //创建订单
    void create(Order order);
}
