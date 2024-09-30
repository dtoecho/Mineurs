package com.newlecmineursprj.util;

import com.newlecmineursprj.entity.Member;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(Member member, String tempPassword) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setTo(member.getEmail());
            helper.setSubject("[마인유어스 : mine, yours ෆ]"+member.getUsername()+"님 임시 비밀번호가 생성되었습니다.");
            String htmlMsg = """
                            <html>
                                <body>
                                    <table width="670" border="0" cellpadding="0" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td style="padding:24px 14px 0;">
                                                    <table width="670" border="0" cellpadding="0" cellspacing="0">
                                                        <tbody><!-- 상단메인배너 --><tr><td>
                                                            <img src="http://m-img.cafe24.com/images/template/admin/kr/img_visual_customer_9.jpg" alt="" loading="lazy">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding:50px 0 0 10px; font-size:12px; font-family:Gulim; color:#393939; line-height:19px;">
                                                    <p>안녕하세요. <strong>마인유어스 : mine, yours ෆ</strong> 입니다.<br> 저희 쇼핑몰을 방문해 주셔서 감사드립니다.</p>
                                                    <p style="margin-top:13px;"><strong>"""+member.getName()+"("+member.getName()+")"+
                                                """
                                                </strong> 고객님의 가입정보는 다음과 같습니다.</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table width="670" border="0" cellpadding="0" cellspacing="0" style="font-size:12px; font-family:Gulim; color:#393939; line-height:19px;">
                                                        <tbody>
                                                            <tr>
                                                                <td height="40">&nbsp;</td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0 0 20px;">
                                                                        <tbody>
                                                                            <tr>
                                                                                <td valign="middle" width="19">
                                                                                    <img src="http://m-img.cafe24.com/images/template/admin/kr/ico_title.gif" alt="" loading="lazy">
                                                                                </td>
                                                                                <td valign="middle"><strong style=" font-size:13px; font-family:Gulim; color:#1c1c1c;">
                                                                                    가입 정보</strong>
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:12px; font-family:Gulim; line-height:15px; border-top:1px solid #d5d5d5;">
                                                                        <tbody>
                                                                            <tr>
                                                                                <th align="left" colspan="1" rowspan="1" scope="row" valign="middle" width="22%" style="padding:13px 10px 10px; font-weight:normal; background-color:#f5f6f5; border-bottom:1px solid #d5d5d5; border-right:1px solid #d5d5d5; border-left:1px solid #d5d5d5; color:#80878d;">
                                                                                    아이디
                                                                                </th>
                                                                                <td align="left" valign="middle" width="28%" style="padding:13px 10px 10px; border-bottom:1px solid #d5d5d5; border-right:1px solid #d5d5d5; color:#393939;">
                                                                                """
                                                                                    +member.getUsername()+
                                                                                """
                                                                                </td>
                                                                                <th align="left" colspan="1" rowspan="1" scope="row" valign="middle" width="22%" style="padding:13px 10px 10px; font-weight:normal; background-color:#f5f6f5; border-bottom:1px solid #d5d5d5; border-right:1px solid #d5d5d5; color:#80878d;">
                                                                                    임시비밀번호
                                                                                </th>
                                                                                <td align="left" valign="middle" width="28%" style="padding:13px 10px 10px; border-bottom:1px solid #d5d5d5; border-right:1px solid #d5d5d5; color:#393939;">
                                                                                    """
                                                                                    +tempPassword+
                                                                                    """
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </tbody> 
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding:30px 0 60px 10px; font-size:12px; font-family:Gulim; color:#393939; line-height:19px;">
                                                    <p>회원가입 시 등록한 정보를 수정하려면, [MyShop]에서 변경하실 수 있습니다.</p> 
                                                    <p style="margin-top:13px;">언제든지 
                                                        <strong>
                                                        """
                                                            +member.getName()+"("+member.getUsername()+")"+
                                                        """
                                                        </strong> 
                                                        고객님의 쇼핑몰 방문을 기다리겠습니다.
                                                    </p> 
                                                </td>
                                            </tr>
                                        </tbody> 
                                    </table>
                                </body>
                            </html>
                            """;

            helper.setText(htmlMsg, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);

    }

}
