package com.seata.springcloud.alibaba.service.Impl;

import com.seata.springcloud.alibaba.dao.AccountDao;
import com.seata.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money  金额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("--->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
//        try{ //暂停几秒钟线程
//            TimeUnit.SECONDS.sleep(20);
//        }catch (InterruptedException e){e.printStackTrace();}
        accountDao.decrease(userId,money);
        log.info("--->account-service中扣减余额结束");
    }
}
