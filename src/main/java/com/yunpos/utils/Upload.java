package com.yunpos.utils;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

public class Upload {

	/**
	 * saveFileToServer 上传文件保存到服务器
	 * 
	 * @param filePath为上传文件的名称
	 *            ，
	 * @param saveFilePathName为文件保存全路径
	 * @param saveFileName为保存的文件
	 * @param extendes为允许的文件扩展名
	 *            , *
	 * @return 返回一个map，map中有4个值，第一个为保存的文件名fileName,第二个为保存的文件大小fileSize,,
	 *         第三个为保存文件时错误信息errors,如果生成缩略图则map中保存smallFileName，表示缩略图的全路径
	 */
	public static Map saveFileToServer(HttpServletRequest request, String saveFilePathName, String saveFileName,
			String[] extendes) throws IOException {
		StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
		MultipartFile file = (MultipartFile) multipartRequest.getFile("file");
		Map map = new HashMap();
		if (file != null && !file.isEmpty()) {
			// System.out.println("文件名为：" + file.getOriginalFilename());
			String extend = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (saveFileName == null || saveFileName.trim().equals("")) {
				saveFileName = UUID.randomUUID().toString() + "." + extend;
			}
			if (saveFileName.lastIndexOf(".") < 0) {
				saveFileName = saveFileName + "." + extend;
			}
			float fileSize = Float.valueOf(file.getSize());// 返回文件大小，单位为k
			List<String> errors = new java.util.ArrayList<String>();
			boolean flag = true;
			
			/**
			 * 对上传文件控制，判断文件名后缀，
			 * 如果文件后缀为.jsp,.php,.aspx,.asp,.aspx
			 * 文件，进行过滤
			 */
			String imageSuffix = "gif|jpg|jpeg|bmp|png|tbi";
			
			if (!isImg(extend,imageSuffix)) {
				flag = false;
			}
			if (flag == true && extendes != null) {
				for (String s : extendes) {
					if (extend.toLowerCase().equals(s))
						flag = true;
				}
			}
			if (flag) {
				File path = new File(saveFilePathName);
				if (!path.exists()) {
					path.mkdirs();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(saveFilePathName + File.separator
								+ saveFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) (fileSize);
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0) {
						out.write(buffer);
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				if (isImg(extend,imageSuffix)) {
					File img = new File(saveFilePathName + File.separator
							+ saveFileName);
					try {
						BufferedImage bis = ImageIO.read(img);
						int w = bis.getWidth();
						int h = bis.getHeight();
						map.put("path", img.getPath().replace(ConfigContants.IMAGEPATH, ConfigContants.IMAGEURL));
						map.put("width", w);
						map.put("height", h);
					} catch (Exception e) {
						// map.put("width", 200);
						// map.put("heigh", 100);
					}
				}
				map.put("mime", extend);
				map.put("fileName", saveFileName);
				map.put("fileSize", fileSize);
				map.put("error", errors);
				map.put("oldName", file.getOriginalFilename());
				// System.out.println("上传结束，生成的文件名为:" + fileName);
			} else {
				// System.out.println("不允许的扩展名");
				errors.add("不允许的扩展名");
			}
		} else {
			map.put("path", "");
			map.put("width", 0);
			map.put("height", 0);
			map.put("mime", "");
			map.put("fileName", "");
			map.put("fileSize", 0.0f);
			map.put("oldName", "");
		}
		return map;
	}
	
	public static boolean isImg(String extend,String imageSuffix) {
		boolean ret = false;
		List<String> list = new java.util.ArrayList<String>();
		if(org.apache.commons.lang.StringUtils.isNotEmpty(imageSuffix)){
			String []is = imageSuffix.split("\\|");
			int i,length=is.length;
			for(i=0;i<length;i++){
				list.add(is[i]);
			}
		}else{
			list.add("jpg");
			list.add("jpeg");
			list.add("bmp");
			list.add("gif");
			list.add("png");
			list.add("tif");
			list.add("tbi");
		}
		
		
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;

	}
	
	
	/**
	 * 生成最终上传图片文件的文件夹路径
	 * @param request
	 * @param config
	 * @return
	 */
	public static String generatorImagesFolderServerPath(HttpServletRequest request){
//		String path = request.getSession().getServletContext().getRealPath("/") + "\\upload";
		String path = ConfigContants.IMAGEPATH;
//		path = "E:\\upload\\images";
		File imageFolder = new File(path);
		if(!imageFolder.exists()){
			imageFolder.mkdirs();
		}
		return path;
	}
	
	public static Map upload(HttpServletRequest request, HttpServletResponse response){
		String saveFilePathName = generatorImagesFolderServerPath(request);
		File file = new File(saveFilePathName);
		if(!file.exists()){
			file.mkdirs();
		}
		Map map = null;
		try {
			map = saveFileToServer(request, saveFilePathName, "", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}
