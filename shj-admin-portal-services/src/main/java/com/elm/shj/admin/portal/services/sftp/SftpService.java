/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.sftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Service for filesystem manipulation using SFTP
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
public class SftpService {

    @Autowired
    @Qualifier("sftpProperties")
    private SftpProperties dataUploadConfig;

    @Autowired
    @Qualifier("applicantIncidentSftpProperties")
    private SftpProperties applicantIncidentsConfig;

    @Autowired
    @Qualifier("cardsSftpProperties")
    private SftpProperties cardsConfig;
    // Set the prompt when logging in for the first time, optional values: (ask | yes | no)
    private static final SimpleDateFormat FILE_NAME_POSTFIX_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final SimpleDateFormat FOLDER_NAME_FORMAT = new SimpleDateFormat("yyyy_MM_dd");
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
    private static final String APPLICANT_INCIDENTS_CONFIG_PROPERTIES = "applicantIncidentsConfigProperties";
    private static final String CARDS_CONFIG_PROPERTIES = "cardsConfigProperties";
    private static final String DATA_UPLOAD_CONFIG_PROPERTIES = "dataUploadConfigProperties";


    /**
     * upload files
     *
     * @param targetPath  the target file path
     * @param inputStream a file input stream to be uploaded
     * @return if the file was uploaded successfully
     * @throws JSchException in case of operation failure
     */
    public boolean uploadFile(String targetPath, InputStream inputStream,String  configPropertiesType) throws JSchException {
        SftpProperties config= getSftpPropertiesConfig( configPropertiesType);

        log.info("Upload File Started, ftpServer [{}:{}], ftpPath [{}]", config.getHost(), config.getPort(), targetPath);
        ChannelSftp sftp = this.createSftp( configPropertiesType);
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
    public boolean uploadFile(String targetPath, File file,String  configPropertiesType) throws Exception {
        return this.uploadFile(targetPath, new FileInputStream(file), configPropertiesType);
    }

    /**
     * download file
     *
     * @param targetPath the target file path
     * @return the file reference
     * @throws Exception in case of operation failure
     */
    public Resource downloadFile(String targetPath, String configPropertiesType) throws Exception {
        SftpProperties config = getSftpPropertiesConfig( configPropertiesType);
        log.info("Download File Started, ftpServer [{}:{}], ftpPath [{}]", config.getHost(), config.getPort(), targetPath);
        ChannelSftp sftp = this.createSftp(configPropertiesType);
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
    public boolean deleteFile(String targetPath,String  configPropertiesType) throws Exception {
        SftpProperties config= getSftpPropertiesConfig( configPropertiesType);

        ChannelSftp sftp = null;
        try {
            sftp = this.createSftp( configPropertiesType);
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
    private ChannelSftp createSftp(String configPropertiesType) throws JSchException {
        SftpProperties config= getSftpPropertiesConfig( configPropertiesType);
        JSch jsch = new JSch();
        log.info("Try to connect sftp[" + config.getUsername() + "@" + config.getHost() + "], use password[" + config.getPassword() + "]");

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort(),config.getSessionStrictHostKeyChecking());
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
    private Session createSession(JSch jsch, String host, String username, Integer port, String sessionStrictHostKeyChecking) throws JSchException {
        Session session;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new JSchException(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING,sessionStrictHostKeyChecking);
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

    private SftpProperties getSftpPropertiesConfig(String configPropertiesType) {
        switch (configPropertiesType) {
            case APPLICANT_INCIDENTS_CONFIG_PROPERTIES:
                return applicantIncidentsConfig;
            case CARDS_CONFIG_PROPERTIES:
                return cardsConfig;
            default:
                return dataUploadConfig;
        }
    }


    /**
     * Generates the file path to be used to save the file in sftp server
     *
     * @param fileName         the file name to save
     * @param referenceNumber the data request reference
     * @param isErrorFile      if the file to save is an error file
     * @return the sftp path for the file
     */
    public String generateSftpFilePath(String fileName, String referenceNumber, boolean isErrorFile) {
        String[] fileNameParts = Objects.requireNonNull(fileName).split("\\.");
        String localFileName = fileNameParts[0] +
                "_" +
                (isErrorFile ? "with-errors" : FILE_NAME_POSTFIX_FORMAT.format(new Date())) +
                "." +
                fileNameParts[1];
        return FOLDER_NAME_FORMAT.format(new Date()) +
                "/" +
                referenceNumber +
                "/" +
                (isErrorFile ? "error/" : "") +
                localFileName;
    }

    // zip a directory, including sub files and sub directories
    public void zipFolder(Path source) throws IOException {

        // get folder name as zip file name
        String zipFileName = source.toString() + ".zip";

        try (
                ZipOutputStream zos = new ZipOutputStream(
                        new FileOutputStream(zipFileName))
        ) {

            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file,
                                                 BasicFileAttributes attributes) {

                    // only copy files, no symbolic links
                    if (attributes.isSymbolicLink()) {
                        return FileVisitResult.CONTINUE;
                    }

                    try (FileInputStream fis = new FileInputStream(file.toFile())) {

                        Path targetFile = source.relativize(file);
                        zos.putNextEntry(new ZipEntry(targetFile.toString()));

                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }

                        // if large file, throws out of memory
                        //byte[] bytes = Files.readAllBytes(file);
                        //zos.write(bytes, 0, bytes.length);

                        zos.closeEntry();

                        System.out.printf("Zip file : %s%n", file);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.printf("Unable to zip : %s%n%s%n", file, exc);
                    return FileVisitResult.CONTINUE;
                }
            });

        }

    }

    public Resource downloadCardsZipFile(String path) throws Exception {
        SftpProperties config = this.getSftpPropertiesConfig(CARDS_CONFIG_PROPERTIES);
        log.info("Download File Started, ftpServer [{}:{}], ftpPath [{}]", config.getHost(), config.getPort(), path);
        ChannelSftp sftp = this.createSftp(CARDS_CONFIG_PROPERTIES);
        try {
            sftp.cd(config.getRootFolder());
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                sftp.get(path, outputStream);
                log.info("Download file success. TargetPath: {}", path);
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                return new ByteArrayResource(outputStream.toByteArray());
            }
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", path, e);
            throw new Exception("Download File failure from SFTP");
        } finally {
            this.disconnect(sftp);
        }
    }
}
