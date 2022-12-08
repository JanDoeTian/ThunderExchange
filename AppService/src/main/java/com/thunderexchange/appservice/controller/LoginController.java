package com.gudy.counter.controller;

import com.gudy.counter.bean.res.Account;
import com.gudy.counter.bean.res.CaptchaRes;
import com.gudy.counter.bean.res.CounterRes;
import com.gudy.counter.cache.CacheType;
import com.gudy.counter.cache.RedisStringCache;
import com.gudy.counter.service.AccountService;
import com.gudy.counter.util.Captcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import commons.uuid.GudyUuid;

import static com.gudy.counter.bean.res.CounterRes.FAIL;
import static com.gudy.counter.bean.res.CounterRes.RELOGIN;
import static com.gudy.counter.bean.res.CounterRes.SUCCESS;

@RestController
@RequestMapping("/login")
@Log4j2
public class LoginController {


    @RequestMapping("/captcha")
    public CounterRes captcha() throws Exception{

        Captcha captcha = new Captcha(120,40,
                4,10);


        String uuid = String.valueOf(GudyUuid.getInstance().getUUID());
        RedisStringCache.cache(uuid,captcha.getCode(),
                CacheType.CAPTCHA);

        //uuid,base64
        CaptchaRes res = new CaptchaRes(uuid,captcha.getBase64ByteStr());
        return new CounterRes(res);
    }

    @Autowired
    private AccountService accountService;

    @RequestMapping("/userlogin")
    public CounterRes login(@RequestParam long uid,
                            @RequestParam String password,
                            @RequestParam String captcha,
                            @RequestParam String captchaId) throws Exception{

        Account account = accountService.login(uid, password,
                captcha, captchaId);

        if(account == null){
            return new CounterRes(FAIL,
                    "用户名密码/验证码错误，登录失败",null);
        }else {
            return new CounterRes(account);
        }
    }

    @RequestMapping("/loginfail")
    public CounterRes loginFail(){
        return new CounterRes(RELOGIN,"请重新登陆",null);
    }

    //退出登录
    @RequestMapping("/logout")
    public CounterRes logout(@RequestParam String token){
        accountService.logout(token);
        return new CounterRes(SUCCESS,"退出成功",null);
    }

    @RequestMapping("pwdupdate")
    public CounterRes pwdUpdate(@RequestParam int uid,
                                @RequestParam String oldpwd,
                                @RequestParam String newpwd){
        boolean res = accountService.updatePwd(uid, oldpwd, newpwd);
        if(res){
            return new CounterRes(SUCCESS,"密码更新成功",null);
        }else {
            return new CounterRes(FAIL,"密码更新失败",null);
        }

    }
}
