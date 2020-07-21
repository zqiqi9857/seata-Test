package com.seata.springcloud.alibaba.service.Impl;

import com.seata.springcloud.alibaba.dao.StorageDao;
import com.seata.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
     @Autowired
     private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
     log.info("----->Storage-Service中扣减库存开始！！");
     storageDao.decrease(productId,count);
     log.info("--->storage-service中扣减库存结束！！！");
    }
}
