package com.wpstan.demo.aop.service.impl;

import com.wpstan.demo.aop.service.ConfigService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class XmlConfigService implements ConfigService {
    private Random random = new Random();
    @Override
    public void loadConfig() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("XmlConfigService load complete...");
    }
}
