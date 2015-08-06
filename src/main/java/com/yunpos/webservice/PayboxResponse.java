package com.yunpos.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://mycompany.com/hr/schemas", name = "PayboxResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PayboxResponse {
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
