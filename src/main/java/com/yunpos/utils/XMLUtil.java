package com.yunpos.utils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * 
 * 功能描述：xml解析工具类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class XMLUtil {
	
	/**
	 * 解析xml节点,解析该xml节点的子节点，及子节的子节点
	 * @param hm
	 * @param e
	 */
	@SuppressWarnings("unchecked")
	public static void parseXMLNode(Map<String,String> hm,Element e){
		Element child=null;   //定义一个Element元素对象
		//下面开始迭代循环
		for(Iterator<Element> childs= e.getChildren().iterator();childs.hasNext();){
			child=(Element)childs.next(); //获取节点下的每一个子元素
			if(child.getChildren().isEmpty()){
				hm.put(child.getName(), child.getValue()); //将每一个元素的名称和值都保存到HashMap中，方便以后查询取出
			}else{
				parseXMLNode(hm,child);
			}
				
			
		}
	}
	
	/**
	 * 此方法用来解析获取的XML数据,返回一个XML的根元素Element对象
	 * resXml：快钱返回的XML数据流
	 * root：最终得到的根元素
	 * */
	public static Element parseXML(String resXml)throws Exception{
		SAXBuilder sb=new SAXBuilder();  //初始化SAXBuilder
		//创建一个新的字符串
        StringReader read = new StringReader(resXml);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource inSource = new InputSource(read);
		Document doc=sb.build(inSource);		//得到并构建XML文档对象
		Element root=doc.getRootElement();  //得到XML的根元素;  //定义一个Element根元素对象
		return root;
	}
	
	/**
	 * 解析xml节点，返回该节点的下级子节点
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> parse(Element root){
		Map<String,String> result = new HashMap<String,String>();
		//下面开始迭代循环
		for (Iterator<Element> childs= root.getChildren().iterator();childs.hasNext();) {
			Element child = childs.next(); //获取每一个子元素
			String childName=(String)child.getName();  //得到该节点的名称
			if(child.getChildren().isEmpty()){
				result.put(childName, child.getValue());
			}
		}
		return result;
	}
	
	/**
	 * 解释XML第一级节点,返回map
	 * */
	public static Map<String,String> parse(String xml)throws Exception{
		Element root = parseXML(xml);
		return parse(root);
	}
	
	/**
	 * 解析 xml所有节点,包括子节点及子节点的子节点
	 * @param xml
	 * @param result 解析结果
	 */
	public static void parse(String xml,Map<String,String> result)throws Exception{
		XMLUtil.parseXMLNode(result,XMLUtil.parseXML(xml));
	}
	
	public static void main(String []args)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		XMLUtil.parseXMLNode(map,XMLUtil.parseXML("<?xml version=\"1.0\" encoding=\"utf-8\"?><alipay><is_success>T</is_success> <request><param name=\"card_type\">CC</param><aaaa>Test</aaaa></request></alipay>"));
		System.out.println(map.get("is_success"));
		System.out.println(map.get("error"));
		System.out.println(map.get("aaaa"));
	}
}
