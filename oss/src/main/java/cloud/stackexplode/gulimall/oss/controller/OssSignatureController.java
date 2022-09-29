package cloud.stackexplode.gulimall.oss.controller;

import cloud.stackexplode.gulimall.common.utils.R;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/oss")
public class OssSignatureController {
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKey;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    @Resource
    private OSS ossClient;

    @GetMapping("/policy")
    public R policy() {
        Map<String, String> res = new LinkedHashMap<>();
        String host = "https://" + bucket + "." + endpoint;
        String dir = LocalDate.now().toString();
        try {
            long expireTime = 30;
            long expireTimeEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expireDate = new Date(expireTimeEndTime);
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1024 * 1024);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String policy = ossClient.generatePostPolicy(expireDate, policyConditions);
            String postSignature = ossClient.calculatePostSignature(policy);
            String encodedPolicy = BinaryUtil.toBase64String(policy.getBytes(StandardCharsets.UTF_8));
            res.put("host", host);
            res.put("accessKey", accessKey);
            res.put("policy", encodedPolicy);
            res.put("signature", postSignature);
            res.put("dir", dir);
            res.put("expire", String.valueOf(expireTimeEndTime / 1000));

        } catch (Exception e) {
            log.error("签名失败");
        } finally {
            log.info("签名结束");
        }
        return R.ok().put("data", res);
    }
}
