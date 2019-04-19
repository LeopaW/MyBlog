package com.xa.controller;

import com.xa.dto.ImgResponseDTO;
import com.xa.pojo.Blog;
import com.xa.pojo.User;
import com.xa.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.xa.servlet.WebPathInitServlet.uploadPath;

@Controller
@RequestMapping("/study")
public class StudyController {


    @Autowired
    private StudyService studyService;


    @RequestMapping("/ToSummary")
    public String toSummary() {
        return "study/summary";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public ImgResponseDTO upload(Model model, HttpSession httpSession, HttpServletRequest request) {
        // TODO Auto-generated method stub
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        String myFileNamePrefix = myFileName.substring(0,myFileName.lastIndexOf("."));
                        System.out.println(myFileNamePrefix);
                        String dateTime = new Date().getTime()+"";
                        User user = (User) httpSession.getAttribute("user");
                        String fileName =  user.getUsername()+dateTime+myFileNamePrefix+"." + myFileName.
                                substring(myFileName.lastIndexOf(".") + 1);
                        System.out.println(fileName);
                        //定义上传路径
                        try {
                            String pat = uploadPath;//获取文件保存路径
                            String sqlPath = "static"+File.separator+"img"+File.separator+"upload"+File.separator;
                            File fileDir = new File(pat + sqlPath);
                            if (!fileDir.exists()) { //如果不存在 则创建
                                fileDir.mkdirs();
                            }
                            String path = pat + sqlPath + fileName;
                            //存文件
                            File localFile = new File(path);
                            file.transferTo(localFile);
                            return new ImgResponseDTO("0","上传成功");
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return new ImgResponseDTO("1","上传失败");
    }


    @RequestMapping("/Summary")
    public String summary(Blog blog,Model model){

        System.out.println(blog);

        boolean sucFlag = studyService.summary(blog);
        if(sucFlag){
            return "login";
        }else{
            model.addAttribute("error","出错啦");
            return "summary";
        }
    }

    @RequestMapping("/listAll")
    public String listAll(Model model){
        List<Blog> blog = studyService.listAll();
        model.addAttribute("blogSummary",blog);
        return "index";
}




}
