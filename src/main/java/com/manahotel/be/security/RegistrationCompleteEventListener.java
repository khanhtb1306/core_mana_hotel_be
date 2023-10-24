package com.manahotel.be.security;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.security.RegistrationCompleteEvent;
import com.manahotel.be.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final StaffService staffService;

    @Autowired
    private final JavaMailSender mailSender;
    private Staff staff;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        staff = event.getStaff();
//        String verificationToken = UUID.randomUUID().toString();
//        staffService.saveUserVerificationToken(staff, verificationToken);
//        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
//        try {
//            sendVerificationEmail(url);
//        } catch (UnsupportedEncodingException | MessagingException | jakarta.mail.MessagingException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void sendVerificationEmail(String url) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, " + staff.getStaffName() + ", </p>" +
                "<p>Thank you for registering with us," + "" +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Users Registration Portal Service";

        jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("nguyenhuync2937@gmail.com", senderName);
        messageHelper.setTo(staff.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendPasswordResetVerificationEmail(String url, Staff staff) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String subject = "Xác minh yêu cầu đặt lại mật khẩu";
        String senderName = "Dịch vụ người dùng";
        String mailContent = "<p> Chào, " + staff.getStaffName() + ", </p>" +
                "<p><b>Gần đây bạn đã yêu cầu đặt lại mật khẩu của mình,</b>" + "" +
                "Vui lòng nhấp vào liên kết bên dưới để hoàn thành hành động.</p>" +
                "<a href=\"" + url + "\">Đặt lại mật khẩu</a>" +
                "<p> Dịch vụ cổng đăng ký người dùng";
        jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom("nguyenhuync2937@gmail.com", senderName);
        messageHelper.setTo(staff.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
