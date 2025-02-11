package com.letsgotravel.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.letsgotravel.myapp.service.MemberService;

@Controller
public class TestDBController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/testDB.do")
    @ResponseBody
    public String testDatabase() {
        return "Current DB Time: " + memberService.getCurrentTime();
    }
}
