package com.nhn.forward2019.reactivekafka.controller;

import com.nhn.forward2019.reactivekafka.service.Step2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/step2")
public class Step2Controller extends DemoController {

    public Step2Controller(Step2Service service) {
        super(service);
    }
}
