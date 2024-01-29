package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwr
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtProperties jwtProperties;

    public UserController(UserService userService, JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户登陆：{}", userLoginDTO.getCode());
        User user = userService.wxLogin(userLoginDTO);
        // 为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        UserLoginVO userLoginVO = UserLoginVO.builder().id(user.getId()).openid(user.getOpenid()).token(token).build();
        return Result.success(userLoginVO);
    }
}
