package com.incloud.hcp.service._framework.email.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.core.io.InputStreamSource;

import java.io.File;
import java.util.Map;

@Data
@ToString
@EqualsAndHashCode
public class EmailParams {

    private String to;
    private String from;
    private String subject;
    private String cc;
    private String[] ccArray;
    private String bcc;
    private String[] bccArray;
    private String replyTo;
    private File file;
    private String fileName;
    private InputStreamSource inputStreamSource;
    private Map<String, String> attributesTemplate;
    private String contentType;
    private String templateFreemaker;

    public void addAttachment(String fileName, File file) {
        this.file = file;
        this.fileName = fileName;
    }

    public void addAttachment(String fileName, InputStreamSource inputStreamSource) {
        this.fileName = fileName;
        this.inputStreamSource = inputStreamSource;
    }

    public void addAttachment(String fileName, InputStreamSource inputStreamSource, String contentType) {
        this.fileName = fileName;
        this.inputStreamSource = inputStreamSource;
        this.contentType = contentType;
    }



}
