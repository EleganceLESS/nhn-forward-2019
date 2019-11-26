package com.nhn.forward2019.reactivekafka.controller;

import com.nhn.forward2019.reactivekafka.service.Step4Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/step4")
public class Step4Controller extends DemoController {

    public Step4Controller(Step4Service service) {
        super(service);
    }
}
