package com.mrbonk97.ourmemory.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String from, String to, String subject, String message) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message(message), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public String message(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n");
        sb.append("  <head>\n");
        sb.append("    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n");
        sb.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n");
        sb.append("    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />\n");
        sb.append("    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />\n");
        sb.append("    <link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800&display=swap\" rel=\"stylesheet\" />\n");
        sb.append("    <style>\n");
        sb.append("      img {\n");
        sb.append("        margin-top: -100px;\n");
        sb.append("        display: none;\n");
        sb.append("      }\n");
        sb.append("      @media screen and (min-width: 600px) {\n");
        sb.append("        img {\n");
        sb.append("          display: block;\n");
        sb.append("        }\n");
        sb.append("      }\n");
        sb.append("    </style>\n");
        sb.append("  </head>\n");
        sb.append("  <body>\n");
        sb.append("    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top: 40px\">\n");
        sb.append("      <tr>\n");
        sb.append("        <td align=\"center\">\n");
        sb.append("          <table width=\"200\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
        sb.append("            <td style=\"border-top: 2px solid black; border-bottom: 2px solid black; text-align: center; padding-top: 10px; padding-bottom: 10px; font-family: 'Noto Sans KR', sans-serif\">우만시</td>\n");
        sb.append("          </table>\n");
        sb.append("        </td>\n");
        sb.append("      </tr>\n");
        sb.append("    </table>\n");
        sb.append("\n");
        sb.append("    <h1 style=\"margin-bottom: 0; margin-top: 40px; text-align: center; letter-spacing: 0.1em; font-family: 'Noto Sans KR', sans-serif; font-size: 40px\">메일 인증 안내</h1>\n");
        sb.append("    <h3 style=\"margin-top: 10px; margin-bottom: 0; text-align: center; font-family: 'Noto Sans KR', sans-serif; font-size: 16px; font-weight: 400\">회원 가입을 완료하기 위해</h3>\n");
        sb.append("    <h3 style=\"margin-top: 0; text-align: center; font-family: 'Noto Sans KR', sans-serif; font-size: 16px; font-weight: 400\">아래의 이메일 인증 코드를 입력해 주세요.</h3>\n");
        sb.append("\n");
        sb.append("    <h3 style=\"margin-top: 60px; margin-bottom: 0; margin-left: -8%; padding: 0; text-align: center\">인증번호</h3>\n");
        sb.append("    <h3 style=\"margin-top: 0; margin-bottom: 0; margin-left: -5%; text-align: center; color: #ffcf40; font-size: 40px; font-weight: 700\">" + code + "</h3>\n");
        sb.append("\n");
        sb.append("    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
        sb.append("      <tr>\n");
        sb.append("        <td align=\"center\">\n");
        sb.append("          <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
        sb.append("             <td><img src=\"../../resources/static/email4.png\" width=\"100%\" /></td>");
        sb.append("          </table>\n");
        sb.append("        </td>\n");
        sb.append("      </tr>\n");
        sb.append("    </table>\n");
        sb.append("\n");
        sb.append("    <h4 style=\"text-align: center; margin-top: 40px; margin-bottom: 0; font-family: 'Noto Sans KR', sans-serif; font-size: 16px; font-weight: 500\">인증 코드를 확인하고</h4>\n");
        sb.append("    <h4 style=\"text-align: center; margin-top: 0; font-family: 'Noto Sans KR', sans-serif; font-size: 16px; font-weight: 500\">입력란에 정확하게 입력해주시기 바랍니다.</h4>\n");
        sb.append("\n");
        sb.append("    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top: 60px\">\n");
        sb.append("      <tr>\n");
        sb.append("        <td align=\"center\">\n");
        sb.append("          <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
        sb.append("            <td style=\"border-top: 2px solid black; text-align: center\">\n");
        sb.append("              <h5 style=\"margin-top: 10px; margin-bottom: 0; font-family: 'Noto Sans KR', sans-serif; font-size: 14px; font-weight: 400\">인증코드는 발급 후 10분 동안만 유효합니다.</h5>\n");
        sb.append("              <h5 style=\"margin-top: 0px; font-family: 'Noto Sans KR', sans-serif; font-size: 14px; font-weight: 300\">만약 코드가 작동하지 않으면, 다시 요청해 주세요.</h5>\n");
        sb.append("            </td>\n");
        sb.append("          </table>\n");
        sb.append("        </td>\n");
        sb.append("      </tr>\n");
        sb.append("    </table>\n");
        sb.append("  </body>\n");
        sb.append("</html>\n");
        sb.append("\n");



        return sb.toString();





    }
}
