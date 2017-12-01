package com.ai.baas.smc.test.api.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.ai.opt.sdk.util.DateUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class TestIsUtf {
    public static void main(String[] args) throws JSchException, SftpException, IOException {

        // JSch jsch = new JSch();
        // jsch.getSession("pabas01", "10.1.130.84", 22);
        // Session session = jsch.getSession("pabas01", "10.1.130.84", 22);
        // session.setPassword("pabas01@123");
        // Properties sshConfig = new Properties();
        // sshConfig.put("StrictHostKeyChecking", "no");
        // session.setConfig(sshConfig);
        // session.connect();
        // Channel channel = session.openChannel("sftp");
        // channel.connect();
        // ChannelSftp channelSftp = (ChannelSftp) channel;
        // channelSftp.cd("/aifs01/users/pabas01/test");
        //
        // String tmpLocalPath = System.getProperty("user.dir");// 本地暂存
        // String saveFile = tmpLocalPath + "/tmp/" + "BIU" + "/"
        // + DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS) + "/" + "BIU_201603_SF_10.zip";
        // File file = new File(saveFile);
        File file = new File("C:/test.zip");
        //File file = new File("E:/testt/BIU_201604_chenshuliang99.zip");
        ZipFile zp = new ZipFile(file);
        InputStream inputStream = null;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        for (Enumeration entries = zp.entries(); entries.hasMoreElements();) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String zipEntryName = zipEntry.getName();
            System.out.println(zipEntryName);
            inputStream = zp.getInputStream(zipEntry);
            byte[] b = new byte[3];
            inputStream.read(b);
            if (zipEntryName.endsWith(".csv") || zipEntryName.endsWith(".txt")) {
            if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
                System.out.println("ok");
            }}
        }
        // channelSftp.get("BIU_201603_SF_10.zip", new FileOutputStream(file));

    }
    // public boolean isUTF8(File file){
    //
    //
    //
    // try {
    //
    // byte[] buf = FileUtils.readFileToByteArray(file);
    //
    // String filecCntent = FileUtils.readFileToString(file,"UTF-8");
    //
    // if(filecCntent.equals(new String(buf,"UTF-8"))){
    //
    // return true;
    //
    // }
    //
    // } catch (IOException e) {
    //
    // // TODO 動生成された catch ブロック
    //
    // e.printStackTrace();
    //
    // }
    //
    // return false；
    //
    // }

}
