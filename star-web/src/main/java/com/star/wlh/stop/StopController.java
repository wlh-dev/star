package com.star.wlh.stop;

import com.star.wlh.user.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("stop")
@RestController
public class StopController {
    @Autowired
    private StopService stopService;
    @RequestMapping("test")
    public Result<String> graceful(){
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.ok("完成调用");
    }
}
