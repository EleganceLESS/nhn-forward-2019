package com.nhn.forward2019.reactivekafka.controller;

import com.nhn.forward2019.reactivekafka.service.Step3Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/step3")
public class Step3Controller extends DemoController {

    public Step3Controller(Step3Service service) {
        super(service);
    }
}
