package io.renren;

import io.renren.modules.app.utils.JwtUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void test() {
        System.out.println(new Sha256Hash("abc123", "stackexplode.cloud").toHex());

        //    String token = jwtUtils.generateToken(1);
//
//    System.out.println(token);
    }
}
