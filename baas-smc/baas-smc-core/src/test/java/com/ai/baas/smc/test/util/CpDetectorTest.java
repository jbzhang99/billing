package com.ai.baas.smc.test.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

public class CpDetectorTest {

    public static void main(String[] args) throws MalformedURLException, IOException {
        File file = new File("e:/BIU_201604_chenshuliang99.zip");
        ZipFile zipFile = new ZipFile(file);
        InputStream inputStream = null;
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.isDirectory()) {
                continue;
            }
            String zipEntryName = zipEntry.getName();
            System.out.println(zipEntryName);
            if (zipEntryName.endsWith(".csv")) {
                File csvFile = new File(zipEntryName);
                System.out.println(getFileEncode(csvFile));
                inputStream = zipFile.getInputStream(zipEntry);
                break;
            }
        }
        byte[] b = new byte[3];
        inputStream.read(b);
        inputStream.close();
        System.out.println(getFileEncode((BufferedInputStream) inputStream, 3));
        inputStream.read(b);
        inputStream.close();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65)
            System.out.println(file.getName() + "：编码为UTF-8");
        else
            System.out.println(file.getName() + "：可能是GBK，也可能是其他编码");
    }

    public static String getFileEncode(String path) throws MalformedURLException, IOException {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。 cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法
         * 加进来，如ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        File f = new File(path);
        charset = detector.detectCodepage(f.toURI().toURL());
        if (charset != null)
            return charset.name();
        else
            return null;
    }

    public static String getFileEncode(File file) throws MalformedURLException, IOException {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。 cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法
         * 加进来，如ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        charset = detector.detectCodepage(file.toURI().toURL());
        if (charset != null)
            return charset.name();
        else
            return null;
    }

    public static String getFileEncode(InputStream in, int length) throws MalformedURLException,
            IOException {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。 cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法
         * 加进来，如ParsingDetector、 JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        charset = detector.detectCodepage(in, length);
        if (charset != null)
            return charset.name();
        else
            return null;
    }
}
