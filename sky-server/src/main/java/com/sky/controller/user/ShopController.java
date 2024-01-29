package com.sky.controller.user;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author zwr
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {
    private static final String KEY = "SHOP_STATUS";
    private final RedisTemplate<String, Integer> redisTemplate;

    public ShopController(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/status")
    public Result<Integer> getStatus() {
        Integer status = redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺营业状态为：{}", status);
        return Result.success(status);
    }
}
