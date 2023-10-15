package com.manahotel.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@Configuration
//public class EmailConfig {
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com"); // Đổi host tùy theo dịch vụ email của bạn
//        mailSender.setPort(587); // Đổi port tùy theo dịch vụ email của bạn
//        mailSender.setUsername("nguyenhuync2937@gmail.com"); // Điền tài khoản email của bạn
//        mailSender.setPassword("huyhuy2937"); // Điền mật khẩu email của bạn
//
//        return mailSender;
//    }
//}
