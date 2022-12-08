package cloud.stackexplode.gulimall.mail.controller;

import cloud.stackexplode.gulimall.common.auth.AuthCodeRequest;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendMail")
    public R sendCode(@RequestBody AuthCodeRequest authCodeRequest) throws Exception {
        log.info("email: " + authCodeRequest.getTarget());
        mailService.sendMail(authCodeRequest.getTarget(), authCodeRequest.getCode());
        return R.ok();
    }
}
