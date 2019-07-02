package com.app.project.utils;

import com.app.project.dto.ProductDto;
import com.app.project.exceptions.MailException;
import com.app.project.model.entity.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static j2html.TagCreator.*;

@Slf4j
public final class EmailUtil {

  private static final String emailAddress = "the.mountain.057@gmail.com";
  private static final String emailPassword = "zupapomidorowa123";


  private EmailUtil() {
  }


  private static String createHtmlTable(List<ProductDto> customerProducts) {

    return
            tbody().with(th("Product name"),
                    th("Product category"),
                    th("Product price")
                            .with(
                                    each(customerProducts, i -> tr(
                                            td(i.getName()),
                                            td(i.getCategory().toString()),
                                            td(i.getPrice().toString())
                                    )))).renderFormatted();
  }


  public static void sendAllSummaryTable(String recipient, String subject, List<ProductDto> productList) {
    String htmlContent = productList.isEmpty() ? "Jeszcze nic u nas nie kupiłeś." : String.join(h1("Twoje kupione produkty").render(), createHtmlTable(productList));
    sendAsHtml(recipient, subject, htmlContent);
  }


  private static void sendAsHtml(String recipient, String subject, String htmlContent) {

    try {
      System.out.println("Sending email to " + recipient + " ...");

      Session session = createSession();

      MimeMessage mimeMessage = new MimeMessage(session);
      prepareEmailMessage(mimeMessage, recipient, subject, htmlContent);

      Transport.send(mimeMessage);
      System.out.println("Email has been sent!");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new MailException("SEND AS HTML MESSAGE EXCEPTION");
    }
  }

  private static void prepareEmailMessage(MimeMessage mimeMessage, String recipient, String subject, String htmlContent) {
    try {
      mimeMessage.setContent(htmlContent, "text/html;charset=utf-8");
      mimeMessage.setFrom(new InternetAddress(emailAddress));
      mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      mimeMessage.setSubject(subject);
    } catch (Exception e) {
      throw new MailException("PREPARE EMAIL MESSAGE EXCEPTION");
    }
  }

  private static Session createSession() {

    Properties properties = new Properties();
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");

    return Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(emailAddress, emailPassword);
      }
    });
  }

}
