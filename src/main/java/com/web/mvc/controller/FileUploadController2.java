package com.web.mvc.controller;

import com.web.mvc.beans.Photo;
import com.web.mvc.validate.PhotoValidator;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload2")
public class FileUploadController2 {

    private List<Photo> list = new ArrayList<>();
    private String emptybase64 = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP8/1+yHgAHtAKYD9BncgAAAABJRU5ErkJggg==";

    @Autowired
    private PhotoValidator validator;

    @GetMapping("/")
    public String input(Model model) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("list", list);
        model.addAttribute("previewWidth", 0);
        return "fileUploadView2";
    }
    
    @GetMapping("/get/{name}")
    public String get(@PathVariable String name,Model model) {
        Photo photo = list.stream().filter(p -> p.getName().equals(name)).findAny().get();
        model.addAttribute("photo", photo);
        model.addAttribute("list", list);
        model.addAttribute("previewWidth", 100);
        return "fileUploadView2";
    }

    @PostMapping("/uploadFile")
    public String submit(@ModelAttribute Photo photo,@RequestParam("file") MultipartFile file, Model model, BindingResult result) {
        model.addAttribute("previewWidth", 0);
        photo.setBase64(getBase64(file));
        System.out.println(photo.getBase64().length() + " : "+photo.getBase64());
        validator.validate(photo, result);
        if (result.hasErrors()) {
            model.addAttribute("list", list);
            return "fileUploadView2";
        }
        list.add(photo);
        model.addAttribute("list", list);
        return "fileUploadView2";
    }

    private String getBase64(MultipartFile file) {
        String base64 = null;
        File f = null;
        try {
            f = File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit(); // 使用完刪除檔案
            //進行 base64 編碼程序
            FileInputStream inputFile = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            base64 = new String(Base64.encodeBase64(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64.length() == 0 ? emptybase64 : base64;
    }
}
