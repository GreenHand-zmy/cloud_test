package org.peter.common.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class H5Controller {

    // 保存html文件的目录地址
    public static final String h5Path = "";

    /**
     * 获取h5文件内容
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @GetMapping(path = "/getH5File",
            produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public byte[] getUploadFile(String fileName) throws IOException {
        // 存放h5文件的地址
        File dest = new File(h5Path + fileName);
        FileInputStream inputStream = new FileInputStream(dest);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    /**
     * 接受h5文本内容
     * 并生成文件到系统中
     *
     * @param html5Content
     */
    @PostMapping(path = "/upload")
    @ResponseBody
    public void upload(@RequestParam String html5Content) throws IOException {
        // 保证目录一定存在
        mkdirIfNotExisted(h5Path);

        // 生成html5文件名,可以随机生成
        String filename = "";
        filename += ".html";
        File dest = new File(h5Path + filename);

        // 将内容写进文件
        FileWriter writer = new FileWriter(dest);
        writer.write(html5Content);

        // 将连接保存到数据库中
        String src = "/getH5File?filename" + filename;
    }

    private void mkdirIfNotExisted(String path) {
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }
    }
}
