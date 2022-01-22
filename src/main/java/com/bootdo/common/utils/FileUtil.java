package com.bootdo.common.utils;

import com.bootdo.common.config.BootdoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Component
public class FileUtil {
    //private static BootdoConfig bootdoConfig;
    public static String fileRootPath;
//    @Autowired(required = true)
//    public void setBootdoConfig(BootdoConfig bootdoConfig){
//        FileUtil.bootdoConfig=bootdoConfig;
//    }
    @Autowired(required = true)
    public void setFileRootPath(BootdoConfig bootdoConfig){
        FileUtil.fileRootPath=bootdoConfig.getUploadPath();
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /**
     * 存储文件
     */
    public static String saveFiles(MultipartFile filesFile) throws Exception {
        String filesFileName = filesFile.getOriginalFilename();
        filesFileName = FileUtil.renameToUUID(filesFileName);
        String filesFilePath = fileRootPath + "files/";
        FileUtil.uploadFile(filesFile.getBytes(), filesFilePath, filesFileName);
        return filesFileName;
    }

    /**
     * 获取文件二进制数组
     */
    public static byte[] getFilesBytes(String filesName) throws Exception {
        String filePath = fileRootPath + "files/" + filesName;
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        return fileBytes;
    }
}
