package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zwr
 */
@Mapper
public interface OrderMapper {
    void insert(Orders orders);
}
