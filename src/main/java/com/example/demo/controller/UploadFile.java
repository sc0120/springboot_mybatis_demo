package com.example.demo.controller;

import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Caoyixian on 2018/1/26 0026.
 */
@RestController
@RequestMapping("/upload")
public class UploadFile {

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public void uploadImage(@RequestParam(name = "file", required = true) MultipartFile file,
                            HttpServletRequest request) throws Exception {

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "images";
//        String filePath = request.getSession().getServletContext().getRealPath("images/");
        System.out.println(filePath);
        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            String saveFilePath = filePath+"/"+fileName;
            FileOutputStream out = new FileOutputStream(saveFilePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
