package com.seata.springcloud.alibaba.service;

public interface StorageService {
    /**
     * 扣减库存
     * @param productId 哪款产品
     * @param count  扣减数量
     */
    void decrease(Long productId,Integer count);
}
