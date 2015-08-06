package com.yunpos.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://mycompany.com/hr/schemas",name="WxuserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxuserRequest {
	
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
