package com.seata.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money  扣了多少钱
     */
    void decrease(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
