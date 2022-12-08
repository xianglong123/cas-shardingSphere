package com.cas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 上午10:26 2022/12/08
 * @version: V1.0
 * @review:
 */
@SpringBootApplication(scanBasePackages = "com.cas")
@MapperScan("com.cas.mapper")
public class TestApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(TestApplication.class, args);
            System.out.println("启动成功！！！！");
        } catch (Exception e) {
            System.out.println("启动失败！！！！");
        }
    }

}
