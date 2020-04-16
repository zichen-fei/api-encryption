package cn.bit.api.controller;

import cn.bit.api.annotation.Decrypt;
import cn.bit.api.annotation.Encrypt;
import cn.bit.api.aspect.SafetyAspect;
import cn.bit.api.response.ResponseBuilder;
import cn.bit.api.util.Constant;
import cn.bit.api.util.RsaUtil;
import cn.bit.api.vo.User;
import cn.bit.bdp.common.response.Response;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/user/login")
    @ResponseBody
    @Encrypt
    @Decrypt
    public cn.bit.bdp.common.response.Response<Object> login(@RequestParam String data) {
        logger.info(data);
        User user = JSON.parseObject(data, User.class);
        return ResponseBuilder.ok(user);
    }

    @GetMapping(value = "/get/key")
    @ResponseBody
    public Response<String> getPublicKey() {
        String publicKey = RsaUtil.getPublicKey();
        String privateKey = RsaUtil.getPrivateKey();
        if (!SafetyAspect.KEYS.containsKey(Constant.PRIVATE_KEY)) {
            SafetyAspect.KEYS.put(Constant.PRIVATE_KEY, privateKey);
            SafetyAspect.KEYS.put(Constant.PUBLIC_KEY, publicKey);
            logger.info(JSON.toJSONString(SafetyAspect.KEYS));
        } else {
            logger.info("keys exist");
        }
        return ResponseBuilder.ok(publicKey);
    }
}