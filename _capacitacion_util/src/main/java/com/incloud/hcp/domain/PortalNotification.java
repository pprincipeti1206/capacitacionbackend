package com.incloud.hcp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PortalNotification implements Serializable {

    @JsonProperty( value = "NotificationTypeKey" )     private String notificationTypeKey        ;
    @JsonProperty( value = "NotificationTypeVersion" ) private String notificationTypeVersion    ;
    @JsonProperty( value = "Templates" )               private List<Template> templates          ;

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Template {

        @JsonProperty( value = "Language" )          private String language                     ;
        @JsonProperty( value = "TemplatePublic" )    private String templatePublic               ;
        @JsonProperty( value = "TemplateSensitive" ) private String templateSensitive            ;
        @JsonProperty( value = "TemplateGrouped" )   private String templateGrouped              ;
        @JsonProperty( value = "TemplateLanguage" )  private String templateLanguage             ;
        @JsonProperty( value = "Subtitle" )          private String subtitle                     ;

    }

}