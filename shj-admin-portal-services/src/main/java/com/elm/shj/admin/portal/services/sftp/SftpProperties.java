/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.sftp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SFTP configuration properties
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Getter
@Setter
@AutoConfigureBefore(AutowiredAnnotationBeanPostProcessor.class)
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
public class SftpProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol;
    private String rootFolder;
    private String sessionStrictHostKeyChecking;
    private Integer sessionConnectTimeout;
    private Integer channelConnectedTimeout;
}
