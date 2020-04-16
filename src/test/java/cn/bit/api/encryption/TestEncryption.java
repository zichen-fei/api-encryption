package cn.bit.api.encryption;

import cn.bit.api.aspect.SafetyAspect;
import cn.bit.api.util.AesUtil;
import cn.bit.api.util.Constant;
import cn.bit.api.util.RsaUtil;
import cn.bit.api.vo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class TestEncryption {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void login() throws Exception {
        String randomKey = "uNkte8d2md7Pl9fE";

        String backendPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKKKgRNf3Sd5K0V0T8CtZVfxytZGY473XxOUXDDpddbVuC1Tp3Zh1GialdihIvsakmGomY5UcEg3UCLSW4TDu6LdLOhSwjnsisByUgT8JM23afUQJzNep+cYKOcxdLbiOS9gkbltLdi9Mz1dQbrEGmEQSpE2q+sfqjeY0MQn3asxAgMBAAECgYAEdZRQ+cJRgOuE/knp2Vf8b6/G7StyEqtTGDDnTqBjAn4JEFB52PlA3WzbAqC5DuORNlTwKLTj5lCWg/rqy4vsQrNVnsX6Zm8jECxLXdWrpGnM+kUJZm5z2s8V2Vh6G/+3Jn/1NSz05u0y8CQsjHF+hb+B2aRFxR5GLn+P6yoUAQJBAN/nVIqO7gIrmr32+iw4+z4YQTlulBESF+AFqRtshOLxEuoJyydMrL6EKaf8w1m2KJV81XbbPQ6L488b5ssnQtECQQC511RD2AGIw71Lp7VdlZ02MeVgixuI1h/paOpOFV8013eOSwiYJu1dNcnDokURQVegY0pAOY5CqRsCsfUtujphAkEAhj0vVpMy52I1OEdm3jSbjTMFh+VgKIvsfIdoR+TcFyPp7x9ICZvSxbZ/MxQ8GwE3e8iIQuZWyO3d+0IDMTGswQJBAJjHs5jK3ggHk3m8+FdlhodlYcOK2SoXjsie3fEvlMOmV719ZiVf60fzzlms78cNxvIubn56Ur/usiv3DS94qEECQEegM15GYvBLs7K3VjNjMRlNUXdo2o4SyY9qp9kwh9sWxyD02PKyF/X7W63Bf+Ddj6qD3+7s7pa65CczmsdDaNc=";
        String backendPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiioETX90neStFdE/ArWVX8crWRmOO918TlFww6XXW1bgtU6d2YdRompXYoSL7GpJhqJmOVHBIN1Ai0luEw7ui3SzoUsI57IrAclIE/CTNt2n1ECczXqfnGCjnMXS24jkvYJG5bS3YvTM9XUG6xBphEEqRNqvrH6o3mNDEJ92rMQIDAQAB";

        SafetyAspect.KEYS.put(Constant.PRIVATE_KEY, backendPrivateKey);
        SafetyAspect.KEYS.put(Constant.PUBLIC_KEY, backendPublicKey);

        System.out.println("后端私钥：" + backendPrivateKey);
        System.out.println("后端公钥：" + backendPublicKey);

        Map<String, String> map = new HashMap<>();
        map.put("username", "123");
        map.put("password", "123");

        String aesKey = Base64.encodeBase64String(RsaUtil.encryptByPublicKey(randomKey.getBytes(), backendPublicKey));
        String data = AesUtil.encrypt(JSON.toJSONString(map), randomKey);
        System.out.println("aesKey: " + aesKey);
        System.out.println("data: " + data);

        String frontPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCniNlblcKVHcaLnZXebMxi6TtrYursPCxNR4KHAmAAazQ6W+Cl8gJqNO9zA2WpX/IQw1fz3gkqyu9kCmJ37xOaUCXvgh2wRct4oVIbb2YseRd4H1F0U5rvBXitJCkK1g0BD+eg1T92GHXcBXHILWeoIBr8/6DFQJpS0GGlbk6uLwIDAQAB";
        String frontPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKeI2VuVwpUdxoudld5szGLpO2ti6uw8LE1HgocCYABrNDpb4KXyAmo073MDZalf8hDDV/PeCSrK72QKYnfvE5pQJe+CHbBFy3ihUhtvZix5F3gfUXRTmu8FeK0kKQrWDQEP56DVP3YYddwFccgtZ6ggGvz/oMVAmlLQYaVuTq4vAgMBAAECgYB73LR+fsGGx6XT8SUtdhjCBBe3NEHleDN0yyrYsEvT426Quv7jEYLxjMN/sAJIcDQiaporJXx3k+PbfcYBT9OYL6dNn3L36SLqYgLw6ZHve8GaQpiWYOvhmp+a5bBMl7nTPbxxFnIfKcTWdUSv/QT+XaRWCg0h9tEsUXL9v3EkSQJBANhMiCmNRNXCoe2cJIT90q61A8+3ySvUs+krgNmCbkZy4EL1U+GzL19ljhKkIhCSVHnPQCxqF93d5cFL/fp8wrUCQQDGSPkpFXCRPJ0rsQD08u48kLH3zj6ZNuCDIJPo04psM1qu7W95YeenGDfJ/NxROsXLrZAPusOTl3Dlj2Zr0kfTAkEAl8/vaaqbe6Tl3k3PXACC1Nh72ljWO4tI0wkToJZpK80JFjX2L92CDGTM+Mx2lEcAP5Z1Jjc4lSJfVfIuSpcyWQJBAIY8wPHfYeXxczYxDsTF8iNukg2Bwz9Wt4gOdhQZooy995aVh/JVJc8pxhso5L7ZWy8YwX7koaM502SWQHCeW6ECQQDJrZvJktv2yhn1RG0hGLetBCJJNGNVMKbZbDUUb/kymKo3trm/0f/GBnDkCyUSHACmg/WGAYsyQ1bYAizmIY4F";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("aesKey", aesKey)
                .param("data", data)
                .param("publicKey", frontPublicKey))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        JSONObject o = JSONObject.parseObject(content);

        String responseData = JSONObject.parseObject(o.get("data").toString()).get("data").toString();
        String responseAesKey = JSONObject.parseObject(o.get("data").toString()).get("aesKey").toString();

        String key = new String(RsaUtil.decryptByPrivateKey(Base64.decodeBase64(responseAesKey), frontPrivateKey));
        String realData = AesUtil.decrypt(responseData, key);
        System.out.println(key);
        System.out.println(realData);
    }

    @Test
    public void aesTest1() {
        //16位
        String key = "MIGfMA0GCSqGSIb3";

        //字符串
        String str = "test123";
        try {
            //加密
            String encrypt = AesUtil.encrypt(str, key);
            //解密
            String decrypt = AesUtil.decrypt(encrypt, key);

            System.out.println("加密前：" + str);
            System.out.println("加密后：" + encrypt);
            System.out.println("解密后：" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesTest2() {
        //16位
        String key = "MIGfMA0GCSqGSIb3";

        //复杂对象
        User user = new User();
        user.setUsername("123456");
        user.setPassword("111111");
        try {
            //加密
            String encrypt = AesUtil.encrypt(user.toString(), key);
            //解密
            String decrypt = AesUtil.decrypt(encrypt, key);

            System.out.println("加密前：" + user.toString());
            System.out.println("加密后：" + encrypt);
            System.out.println("解密后：" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rsaTest1() {
        //字符串
        String str = "test123";
        try {
            System.out.println("私钥：" + RsaUtil.getPrivateKey());
            System.out.println("公钥：" + RsaUtil.getPublicKey());

            //公钥加密
            byte[] ciphertext = RsaUtil.encryptByPublicKey(str.getBytes(), RsaUtil.getPublicKey());
            //私钥解密
            byte[] plaintext = RsaUtil.decryptByPrivateKey(ciphertext, RsaUtil.getPrivateKey());

            System.out.println("公钥加密前：" + str);
            System.out.println("公钥加密后：" + Base64.encodeBase64String(ciphertext));
            System.out.println("私钥解密后：" + new String(plaintext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rsaTest2() {
        //复杂对象
        User user = new User();
        user.setUsername("123456");
        user.setPassword("111111");
        try {
            System.out.println("私钥：" + RsaUtil.getPrivateKey());
            System.out.println("公钥：" + RsaUtil.getPublicKey());

            //公钥加密
            byte[] ciphertext = RsaUtil.encryptByPublicKey(user.toString().getBytes(), RsaUtil.getPublicKey());
            //私钥解密
            byte[] plaintext = RsaUtil.decryptByPrivateKey(ciphertext, RsaUtil.getPrivateKey());

            System.out.println("公钥加密前：" + user.toString());
            System.out.println("公钥加密后：" + Base64.encodeBase64String(ciphertext));
            System.out.println("私钥解密后：" + new String(plaintext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
