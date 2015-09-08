package com.yunpos.utils;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * jxl导出excel
 * @author jamboree
 * @date  2013-11-28
 */
public class ExcelUtils {

	/**
	 * @author 
	 * @param objData 导出内容数组
	 * @param sheetName 导出工作表的名称
	 * @param columns 导出Excel的表头数组
	 * @return
	 */
	public static int exportToExcel(HttpServletResponse response, List<Map<String, Object>> objData, String sheetName,List<String> columns) {
		int flag = 0;
		//声明工作簿jxl.write.WritableWorkbook
		WritableWorkbook wwb;
		try {
			//根据传进来的file对象创建可写入的Excel工作薄
			OutputStream os = response.getOutputStream();
			
			wwb = Workbook.createWorkbook(os);

			/*
			 * 创建一个工作表、sheetName为工作表的名称、"0"为第一个工作表
			 * 打开Excel的时候会看到左下角默认有3个sheet、"sheet1、sheet2、sheet3"这样
			 * 代码中的"0"就是sheet1、其它的一一对应。
			 * createSheet(sheetName, 0)一个是工作表的名称，另一个是工作表在工作薄中的位置
			 */
			WritableSheet ws = wwb.createSheet(sheetName, 0);
			
			SheetSettings ss = ws.getSettings();
			ss.setVerticalFreeze(1);//冻结表头
			
			WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 10 ,WritableFont.BOLD);
			WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 9 ,WritableFont.NO_BOLD);
			WritableCellFormat wcf = new WritableCellFormat(font1);
			WritableCellFormat wcf2 = new WritableCellFormat(font2);
			WritableCellFormat wcf3 = new WritableCellFormat(font2);//设置样式，字体

			//创建单元格样式
			//WritableCellFormat wcf = new WritableCellFormat();

			//背景颜色
			wcf.setBackground(jxl.format.Colour.YELLOW);
			wcf.setAlignment(Alignment.CENTRE);  //平行居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
			wcf3.setAlignment(Alignment.CENTRE);  //平行居中
			wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
			wcf3.setBackground(Colour.LIGHT_ORANGE);
			wcf2.setAlignment(Alignment.CENTRE);  //平行居中
			wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中

			/*
			 * 这个是单元格内容居中显示
			 * 还有很多很多样式
			 */
			wcf.setAlignment(Alignment.CENTRE);

			//判断一下表头数组是否有数据
			if (columns != null && columns.size() > 0) {

				//循环写入表头
				for (int i = 0; i < columns.size(); i++) {

					/*
					 * 添加单元格(Cell)内容addCell()
					 * 添加Label对象Label()
					 * 数据的类型有很多种、在这里你需要什么类型就导入什么类型
					 * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label
					 * Label(i, 0, columns[i], wcf)
					 * 其中i为列、0为行、columns[i]为数据、wcf为样式
					 * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中
					 */
					ws.addCell(new Label(i, 0, columns.get(i), wcf));
				}

				//判断表中是否有数据
				if (objData != null && objData.size() > 0) {
					//循环写入表中数据
					for (int i = 0; i < objData.size(); i++) {

						//转换成map集合{activyName:测试功能,count:2}
						LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)objData.get(i);

						//循环输出map中的子集：既列值
						int j=0;
						for(Object o:map.keySet()){
							//ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)
							ws.addCell(new Label(j,i+1,String.valueOf(map.get(o))));
							j++;
						}
					}
				}else{
					flag = -1;
				}

				//写入Exel工作表
				wwb.write();

				//关闭Excel工作薄对象 
				wwb.close();
				
				//关闭流
				os.flush();
				os.close();
				
				os =null;
			}
		}catch (IllegalStateException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception ex) {
			flag = 0;
			ex.printStackTrace();
		}

		return flag;
	}


	/**
	 * 下载excel
	 * @author 
	 * @param response
	 * @param filename 文件名 ,如:20110808.xls
	 * @param listData 数据源
	 * @param sheetName 表头名称
	 * @param columns 列名称集合,如：{物品名称，数量，单价}
	 */
	public static void exportexcle(HttpServletResponse response,String filename,List<Map<String, Object>> listData,String sheetName,List<String> columns)
	{
		//调用上面的方法、生成Excel文件
		response.setContentType("application/vnd.ms-excel");
		//response.setHeader("Content-Disposition", "attachment;filename="+filename);
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");

			exportToExcel(response, listData, sheetName, columns);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 


	}
}