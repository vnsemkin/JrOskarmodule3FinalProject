package org.semkin.module3finalproject.chainofresponsobility.config;

import org.semkin.module3finalproject.chainofresponsobility.service.QuestActionLevel1;
import org.semkin.module3finalproject.chainofresponsobility.service.QuestActionLevel2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.semkin.module3finalproject"})
public class MyAppConfig {

    public MyAppConfig(QuestActionLevel1 questLevel1, QuestActionLevel2 questLevel2) {
        questLevel1.setNextHandler(questLevel2);
    }
}