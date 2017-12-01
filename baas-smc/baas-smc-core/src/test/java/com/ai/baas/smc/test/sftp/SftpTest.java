package com.ai.baas.smc.test.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpTest {
    static Session session = null;

    static Channel channel = null;

    /**
     * 连接sftp服务器
     * 
     * @param host
     *            主机
     * @param port
     *            端口
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return
     */
    public ChannelSftp connect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            session = jsch.getSession(username, host, port);
            // System.out.println("Session created.");
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            // System.out.println("Session connected.");
            // System.out.println("Opening Channel.");
            channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + " success");
        } catch (Exception e) {
            System.out.println("Connected to " + host + " failure");
            SftpTest.disconnect();
        }
        return sftp;
    }

    /**
     * 上传文件
     * 
     * @param directory
     *            上传的目录
     * @param uploadFile
     *            要上传的文件
     * @param sftp
     * @throws Exception
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) throws Exception {
        try {
            sftp.cd(directory.substring(1));
        } catch (SftpException sException) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                this.makeDir(directory, sftp);
                sftp.cd(directory.substring(1));
            }
        }
        try {

            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeDir(String directory, ChannelSftp sftp) throws Exception {
        System.out.println(directory);
        System.out.println(sftp.pwd());
        String parentPath = new File(directory).getParentFile().getPath().replace("\\", "/");
        if (parentPath.equals("/")) {
            sftp.mkdir(directory.substring(1));
        } else {
            try {
                sftp.cd(parentPath.substring(1));
            } catch (SftpException sException) {
                if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                    makeDir(parentPath, sftp);

                }
            }
            sftp.cd("/was");
            sftp.mkdir(directory.substring(1));
        }

    }

    /*
     * 关闭连接
     */
    public static void disconnect() {
        if (channel != null || channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null || session.isConnected()) {
            session.disconnect();
        }

    }

    /**
     * 下载文件
     * 
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param saveFile
     *            存在本地的路径
     * @param sftp
     */

    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * 
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @param sftp
     */
    /*
     * public void delete(String directory, String deleteFile, ChannelSftp sftp) { try {
     * sftp.cd(directory); sftp.rm(deleteFile); } catch (Exception e) { e.printStackTrace(); } }
     */

    /**
     * 列出目录下的文件
     * 
     * @param directory
     *            要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */

    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    public static void main(String[] args) throws Exception {
        SftpTest sftpTest = new SftpTest();
        String host = "10.1.130.84";
        int port = 22;
        String username = "pabas01";
        String password = "pabas01@123";
        String directory = "/aifs01/users/pabas01/tmp";
        String uploadFile = "D:\\tmp\\test.txt";
        String downloadFile = "hosts.swp";
        String saveFile = "e:/tmp/hosts.swp";
        String deleteFile = "delete.txt";
        ChannelSftp sftp = sftpTest.connect(host, port, username, password);
//        sftpTest.upload(directory, uploadFile, sftp);
        sftpTest.download(directory, downloadFile, saveFile, sftp);
        // sf.delete(directory, deleteFile, sftp);
        sftp.cd(directory);
        sftp.mkdir("ss");
        System.out.println("finished");
    }

}