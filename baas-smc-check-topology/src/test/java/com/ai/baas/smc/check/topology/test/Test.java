package com.ai.baas.smc.check.topology.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.ParamCode;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.TypeCode;
import com.ai.baas.smc.check.topology.vo.StlBillItemData;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.DateUtil;

public class Test {

    public static void main(String[] args) throws Exception {
        String targetName = "ERR_wewewewewe.zip";
        String pathRes = "e:/test";
        String targetPath = "e:/test1";
        File resourcesFile = new File(pathRes); // 源文件
        File targetFile = new File(targetPath); // 目的
        // 如果目的路径不存在，则新建
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream outputStream = new FileOutputStream(targetPath + "/" + targetName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        createCompressedFile(out, resourcesFile, "");
        out.close();
    }

    static void createCompressedFile(ZipOutputStream out, File file, String dir) {
        try {
            // 如果当前的是文件夹，则进行进一步处理
            if (file.isDirectory()) {
                // 得到文件列表信息
                File[] files = file.listFiles();
                // 将文件夹添加到下一级打包目录
                out.putNextEntry(new ZipEntry(dir + "/"));

                dir = dir.length() == 0 ? "" : dir + "/";

                // 循环将文件夹中的文件打包
                for (int i = 0; i < files.length; i++) {
                    createCompressedFile(out, files[i], dir + files[i].getName()); // 递归处理
                }
            } else { // 当前的是文件，打包处理
                // 文件输入流
                FileInputStream fis = new FileInputStream(file);

                out.putNextEntry(new ZipEntry(dir));
                // 进行写操作
                int j = 0;
                byte[] buffer = new byte[1024];
                while ((j = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, j);
                }
                // 关闭输入流
                fis.close();
            }
        } catch (FileNotFoundException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {

        }
    }
}
