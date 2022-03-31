package com.elm.shj.admin.portal.services.sftp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {

    @Bean
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
    public SftpProperties sftpProperties() {
        return new SftpProperties();
    }

    @Bean
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.incident.client")
    public SftpProperties applicantIncidentSftpProperties() {
        return new SftpProperties();
    }

    @Bean
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.card.client")
    public SftpProperties cardsSftpProperties() {
        return new SftpProperties();
    }
}
