/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.sftp;

import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Service for filesystem manipulation using SFTP
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SftpService {

    // Set the prompt when logging in for the first time, optional values: (ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    private final SftpProperties config;

    /**
     * upload files
     *
     * @param targetPath  the target file path
     * @param inputStream a file input stream to be uploaded
     * @return if the file was uploaded successfully
     * @throws JSchException in case of operation failure
     */
    public boolean uploadFile(String targetPath, InputStream inputStream) throws JSchException {
        log.info("Upload File Started, ftpServer [{}:{}], ftpPath [{}]", config.getHost(), config.getPort(), targetPath);
        ChannelSftp sftp = this.createSftp();
        try {
            createDirs(config.getRootFolder(), sftp);
            sftp.cd(config.getRootFolder());
            log.info("Change path to {}", config.getRootFolder());

            int index = targetPath.lastIndexOf("/");
            String fileDir = targetPath.substring(0, index);
            String fileName = targetPath.substring(index + 1);
            boolean dirs = this.createDirs(fileDir, sftp);
            if (!dirs) {
                log.error("Remote path error. path:{}", targetPath);
                throw new Exception("Upload File failure from SFTP");
            }
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error("Upload file failure. TargetPath: {}", targetPath, e);
            throw new JSchException("Upload File failure from SFTP");
        } finally {
            this.disconnect(sftp);
        }
    }

    /**
     * upload files
     *
     * @param targetPath the target file path
     * @param file       the file to upload
     * @return if the file was uploaded successfully
     * @throws Exception in case of operation failure
     */
    public boolean uploadFile(String targetPath, File file) throws Exception {
        return this.uploadFile(targetPath, new FileInputStream(file));
    }

    /**
     * download file
     *
     * @param targetPath the target file path
     * @return the file reference
     * @throws Exception in case of operation failure
     */
    public Resource downloadFile(String targetPath) throws Exception {
        log.info("Download File Started, ftpServer [{}:{}], ftpPath [{}]", config.getHost(), config.getPort(), targetPath);
        ChannelSftp sftp = this.createSftp();
        ByteArrayOutputStream outputStream = null;
        try {
            createDirs(config.getRootFolder(), sftp);
            sftp.cd(config.getRootFolder());
            log.info("Change path to {}", config.getRootFolder());
            outputStream = new ByteArrayOutputStream();
            sftp.get(targetPath, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            String fileName = targetPath.substring(targetPath.lastIndexOf("/") + 1);
            return new ByteArrayResource(outputStream.toByteArray(), fileName);
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure from SFTP");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            this.disconnect(sftp);
        }
    }

    /**
     * Delete Files
     *
     * @param targetPath the target file path
     * @return if the file is deleted
     * @throws Exception in case of operation failure
     */
    public boolean deleteFile(String targetPath) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = this.createSftp();
            sftp.cd(config.getRootFolder());
            sftp.rm(targetPath);
            return true;
        } catch (Exception e) {
            log.error("Delete file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Delete File failure from SFTP");
        } finally {
            this.disconnect(sftp);
        }
    }

    /**
     * Create a one-level or multi-level directory
     *
     * @param dirPath the path to create
     * @param sftp    the current channel
     * @return if directories are created of not
     */
    private boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty() && sftp != null) {
            String[] dirs = Arrays.stream(dirPath.split("/"))
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);

            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                    log.info("Change directory {}", dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                        log.info("Create directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Create directory failure, directory:{}", dir, e1);
                    }
                    try {
                        sftp.cd(dir);
                        log.info("Change directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Change directory failure, directory:{}", dir, e1);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Creates SFTP connection
     *
     * @return the created connection
     * @throws JSchException in case of operation failure
     */
    private ChannelSftp createSftp() throws JSchException {
        JSch jsch = new JSch();
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use password[" + config.getPassword() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(config.getSessionConnectTimeout());

        log.info("Session connected to {}.", config.getHost());

        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());

        log.info("Channel created to {}.", config.getHost());

        return (ChannelSftp) channel;
    }

    /**
     * Create session
     *
     * @param jsch     the session holder
     * @param host     the host to connect to
     * @param username the username
     * @param port     the port
     * @return the created session
     * @throws JSchException in case of operation failure
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws JSchException {
        Session session;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new JSchException(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, config.getSessionStrictHostKeyChecking());
        return session;
    }

    /**
     * Close the connection
     *
     * @param sftp the connected channel
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            log.error(e.getMessage(), e);
        }
    }

}
