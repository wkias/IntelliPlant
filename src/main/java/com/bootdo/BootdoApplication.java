package com.bootdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan({"com.bootdo.*.dao","com.bootdo.*.*.dao"})
@SpringBootApplication
@EnableCaching
public class BootdoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootdoApplication.class, args);
        System.out.println("\n启动成功：工厂智能信息化平台V1.0");
		String fatty = " ___           _            _   _   _   ____    _                   _\n" +
                       "|_ _|  _ __   | |_    ___  | | | | (_) |  _ \\  | |   __ _   _ __   | |_\n" +
                       " | |  | '_ \\  | __|  / _ \\ | | | | | | | |_) | | |  / _` | | '_ \\  | __|\n" +
                       " | |  | | | | | |_  |  __/ | | | | | | |  __/  | | | (_| | | | | | | |_\n" +
                       "|___| |_| |_|  \\__|  \\___| |_| |_| |_| |_|     |_|  \\__,_| |_| |_|  \\__|\n";
        String lank =   " ___       _       _ _ _ ____  _             _\n" +
                        "|_ _|_ __ | |_ ___| | (_)  _ \\| | __ _ _ __ | |_\n" +
                        " | || '_ \\| __/ _ \\ | | | |_) | |/ _` | '_ \\| __|\n" +
                        " | || | | | ||  __/ | | |  __/| | (_| | | | | |_\n" +
                        "|___|_| |_|\\__\\___|_|_|_|_|   |_|\\__,_|_| |_|\\__|\n";
        System.out.println(lank);
    }
}
