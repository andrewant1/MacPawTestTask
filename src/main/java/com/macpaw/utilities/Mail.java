package com.macpaw.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    public static Message getGmailMessage(String username, String password,  String subject, String from) throws Exception{

        String subjectField = null;
        int iterations =1;
        Message getMessage = null;
        Session session = null;
        Store store = null;
        Properties props = System.getProperties();

        props.setProperty("mail.store.protocol","imap");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.imap.port", "993");

        session = Session.getInstance(props, null);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com",username,password);
        Folder folder =  store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        while(iterations <= 10){
            Message[] messages = null;
            messages = folder.getMessages();
            SearchTerm fromSenderTerm = new FromTerm(new InternetAddress(from));
            messages = folder.search(fromSenderTerm);

            for (Message message :messages){
                subjectField = message.getSubject();

                if (subjectField.equalsIgnoreCase(subject)){
                    getMessage = message;
                    break;
                }
            }

            if (getMessage == null){
                Thread.sleep(2000);
                iterations++;
            }
            else {
                break;
            }
        }

        if (getMessage != null){
            return  getMessage;
        }
        else {
            throw new Exception("The Email Message was Not found!");
        }

    }


    public  static  String getMsgContent(String username, String password, String subject, String from ) throws Exception{
        Message message = getGmailMessage(username, password, subject, from);
        String line = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(message.getInputStream()));

        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }



    public static void deleteEmails(String username, String password) throws Exception{
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com",username,password);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        Message[] messages;
        for (Message message : messages = folder.getMessages()) {
            message.setFlag(Flags.Flag.DELETED,true);
        }

        folder.close(true);

    }


}