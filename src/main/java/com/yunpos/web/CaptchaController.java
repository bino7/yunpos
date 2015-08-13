package com.yunpos.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 功能描述：验证码生成控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月13日
 * @author Devin_Yang 修改日期：2015年8月13日
 *
 */
@Controller
public class CaptchaController {
	
	private static final long serialVersionUID = -1;

    public static final String CAPTCHA = "captcha";

    private int width = 120;
    private int height = 25;


    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest req, HttpServletResponse response)
            throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/jpeg");

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Font font = new Font("Verdana", Font.CENTER_BASELINE, 20);
        Color c = getRandColor(200, 250);

        Graphics g = image.getGraphics();
        g.setColor(c);
        g.fillRect(1, 1, width - 1, height - 1);
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(font);

        Random random = new Random();

        // 画随机线
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 从另一方向画随机线
        for (int i = 0; i < 70; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }

        // 生成随机数,并将随机数字转换为字母
        String sRand = "";
        for (int i = 0; i < 2; i++) {
            int itmp = random.nextInt(26) + 65;
            char ctmp = (char) itmp;
            sRand += String.valueOf(ctmp);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(ctmp), 15 * i + 10, 16);
        }
        g.dispose();

        HttpSession session = req.getSession();
        session.setAttribute(CAPTCHA, sRand);
        System.out.println("图片验证码："+sRand);

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        outputStream.close();
    }

}
