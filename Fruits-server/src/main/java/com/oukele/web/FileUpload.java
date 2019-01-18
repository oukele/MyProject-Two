package com.oukele.web;

import com.oukele.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "/fileUpload")
public class FileUpload {

    private static String newImg;

    @PostMapping
    public Result fileUpload(@RequestPart("filename") MultipartFile multipartFile, HttpServletRequest request) {
        if( !multipartFile.isEmpty() ){
            //验证文件是否为图片格式 && 文件大小不能超过 5M  1KB = 1024B
            if( multipartFile.getContentType().contains("image/") && multipartFile.getSize() < 1024 * 1024 * 1024 * 5 ){
                //图片的存储文件夹
                String save = request.getServletContext().getRealPath("/images");
                File file = new File(save);
                if( !file.exists() ){
                    file.mkdirs();
                }
                //文件名
                String fileName =multipartFile.getOriginalFilename().substring(0,multipartFile.getOriginalFilename().indexOf("."));
                //上传文件的后缀
                String zhui = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."),multipartFile.getOriginalFilename().length());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //生成新的文件名
                String fileNewName ="upload_"+fileName+"_"+simpleDateFormat.format(new Date())+zhui;
                newImg = fileNewName;
                try {
                    //将此图片存储到images文件夹中
                    multipartFile.transferTo(new File(save+"\\"+fileNewName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                return Result.getResult(501,"上传失败");
            }
        }

        return Result.getResult(200,newImg);
    }



}
