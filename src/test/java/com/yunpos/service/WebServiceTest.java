package com.yunpos.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.Application;
import com.yunpos.model.Users;
import com.yunpos.model.Wxuser;
import com.yunpos.webservice.PayboxResponse;
import com.yunpos.webservice.UsersRequest;
import com.yunpos.webservice.WxuserRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WebServiceTest {
	
	private int serverPort=8080;
	
	private WebServiceTemplate webServiceTemplate;
	private ObjectMapper objectMapper;


	
	
	Jaxb2Marshaller marshaller;
	
	@Before
	public void setUp() {
		webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setDefaultUri("http://localhost:" + this.serverPort	+ "/services");
		
		marshaller = new Jaxb2Marshaller();		
		
		objectMapper = new ObjectMapper();
	}

	@Autowired
	private WxuserService wxuserService;
	@Test
	public void inputAllwxUsers() {
		marshaller.setClassesToBeBound(WxuserRequest.class, PayboxResponse.class);
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);	
		
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("startId", 12);

		String request;
		try {
			request = objectMapper.writeValueAsString(data);

			WxuserRequest wxuserRequest = new WxuserRequest();
			wxuserRequest.setData(request);

			PayboxResponse response = (PayboxResponse) webServiceTemplate.marshalSendAndReceive(wxuserRequest);

			String result = response.getResult();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, String.class,
					Wxuser[].class);
			Map<String, Object> maps = objectMapper.readValue(result, javaType);
			Wxuser[] list = (Wxuser[]) maps.get("result");
			
			System.out.println("list Size" + list.length);

			for (Wxuser ww : list) {
				System.out.println("Id:" + ww.getId() + ",token:" + ww.getToken());
				wxuserService.save(ww);
			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Autowired
	private UsersService usersService;
	@Test
	public void inputAllUsers() {
		marshaller.setClassesToBeBound(UsersRequest.class, PayboxResponse.class);
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);	
		
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("startId", 12);

		String request;
		try {
			request = objectMapper.writeValueAsString(data);

			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setData(request);

			PayboxResponse response = (PayboxResponse) webServiceTemplate.marshalSendAndReceive(usersRequest);

			String result = response.getResult();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, String.class,
					Users[].class);
			Map<String, Object> maps = objectMapper.readValue(result, javaType);
			Users[] list = (Users[]) maps.get("result");
			
			System.out.println("list Size" + list.length);

			for (Users ww : list) {
				System.out.println("Id:" + ww.getId() + ",token:" + ww.getUsername());
				usersService.save(ww);
			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}