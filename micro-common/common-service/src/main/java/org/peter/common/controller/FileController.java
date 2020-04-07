package org.peter.common.controller;

import org.peter.common.api.FileApi;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController("/file")
public class FileController implements FileApi {

    @PostMapping(path = "/upload", headers = "content-type=multipart/form-data")
    public String upload(@RequestParam("files") MultipartFile[] files) throws NoSuchAlgorithmException, IOException {

        // 记表
        // 保存到系统

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("SHA-1");
            md5.update(bytes);
            char[] md5Hex = Hex.encode(md5.digest());
            System.out.println(md5Hex);
            file.getContentType();

        }
        return "";
    }
}
