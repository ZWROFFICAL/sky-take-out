package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author zwr
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {
    private static final String KEY = "SHOP_STATUS";
    private final RedisTemplate<String, Integer> redisTemplate;

    public ShopController(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PutMapping("/{status}")
    public Result<T> setStatus(@PathVariable Integer status) {
        log.info("设置店铺营业状态为：{}", status);
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus() throws NullPointerException {
        Integer status = redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺营业状态为：{}", status);
        return Result.success(status);
    }
}
