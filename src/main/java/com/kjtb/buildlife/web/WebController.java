package com.kjtb.buildlife.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Dr d
 * @Date 2021-10-28
 **/
@RestController
public class WebController {

    @RequestMapping("/")
    public String test() {
        return "加油呀，振起, 不要在愚蠢的愚昧之巅️";
    }
}
