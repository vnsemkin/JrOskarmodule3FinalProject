package org.semkin.module3finalproject.chainofresponsobility.config;

import jakarta.annotation.PostConstruct;
import org.semkin.module3finalproject.chainofresponsobility.service.MyHandlerImplementationLevel1;
import org.semkin.module3finalproject.chainofresponsobility.service.MyHandlerImplementationLevel2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.semkin.module3finalproject"})
public class MyAppConfig {
    private final MyHandlerImplementationLevel1 level1;
    private final MyHandlerImplementationLevel2 level2;

    public MyAppConfig(MyHandlerImplementationLevel1 level1, MyHandlerImplementationLevel2 level2) {
        this.level1 = level1;
        this.level2 = level2;
    }

    @PostConstruct
    public void configureChain() {
        level1.setNextHandler(level2);
    }
}