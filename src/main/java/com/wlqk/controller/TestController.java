package com.wlqk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
public class TestController {

    @Value("${fileName}")
    private String fileName;

    @GetMapping("/test")
    public Object test(){
        return "linux---第三次测试开启jar包";
    }



    /**
     * 读取图片
     */
    @RequestMapping(value = "/iomoreimgcom", produces = {
            "application/json;charset=UTF-8" }, method = RequestMethod.GET)
    @ResponseBody
    public synchronized void iomoreimgcom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getParameter("url");
        File file = new File("D:\\dev\\idea\\ideaIU-2018.3.6\\test_jenkins\\22.png");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        response.setHeader("Content-Type", "image/jpeg");
        byte b[] = new byte[1024];
        int read;
        try {
            while ((read = bis.read(b)) != -1) {
                bos.write(b, 0, read);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(@RequestParam Map<String, Object> data, HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
        String time = "2021";
        String path = "D:\\dev\\idea\\ideaIU-2018.3.6\\test_jenkins\\22.png";
        String fileName = time+".png"; // 文件的默认保存名
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
