package com.seata.springcloud.alibaba.service.impl;

import com.seata.springcloud.alibaba.dao.OrderDao;
import com.seata.springcloud.alibaba.domain.Order;
import com.seata.springcloud.alibaba.service.AccountService;
import com.seata.springcloud.alibaba.service.OrderService;
import com.seata.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;



    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
       log.info("----->开始新建订单");
       //1.新建订单
       orderDao.create(order);

       log.info("---->订单微服务开始调用库存服务,做扣减库存Count");
       //2.扣减库存
       storageService.decrease(order.getProductId(),order.getCount());
       log.info("--->订单微服务开始调用库存服务,做扣减库存end");

       log.info("--->订单微服务开始调用账户服务，做扣减余额Money");
       //3.扣减余额
       accountService.decrease(order.getUserId(),order.getMoney());
       log.info("--->订单微服务开始调用库存服务,做扣减余额end");
       //4.修改订单状态 从零到1 1代表已完成
       log.info("-->修改订单状态开始");
       orderDao.update(order.getUserId(),0);
       log.info("-->修改订单状态结束");

       log.info("---->下订单结束了,~~~O(∩_∩)O哈哈~~~");
    }
}
