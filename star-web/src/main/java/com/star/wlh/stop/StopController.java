package com.star.wlh.stop;

import com.star.wlh.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("stop")
@RestController
public class StopController {
    @Autowired
    private StopService stopService;
    @RequestMapping("test")
    public ResponseResult<String> graceful(){
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseResult.ok("完成调用");
    }
}
