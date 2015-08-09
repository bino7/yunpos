package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.alipaybox.service.EntityService;
import com.alipaybox.webservice.PayboxResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseWebserviceClient<T1,T2> {	
	//@Autowired
	private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	//@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	//@Autowired 
	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	
	Class<T2> resultBean;
	
	public void setDefaultUri(String uri) {
		this.webServiceTemplate.setDefaultUri(uri);
	}

	@SuppressWarnings("unchecked")
	public T2[] marshalSendAndReceive(Map<String, ?> request)
			throws IOException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String jsonRequest = objectMapper.writeValueAsString(request);
		
		Type type = getClass().getGenericSuperclass();    
		ParameterizedType p = (ParameterizedType) type;  
		Class<T1> marshallerClass = (Class<T1>) p.getActualTypeArguments()[0]; 
		marshaller.setClassesToBeBound(marshallerClass, PayboxResponse.class);	
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);	
			
		Method method = marshallerClass.getDeclaredMethod("setData", String.class);
		T1 requestData = marshallerClass.newInstance();
		method.invoke(requestData, jsonRequest);
		PayboxResponse response = (PayboxResponse) webServiceTemplate.marshalSendAndReceive(requestData);
			  	
		
		resultBean = (Class<T2>) p.getActualTypeArguments()[1];	
		T2[] resultBeanList = (T2[])Array.newInstance(resultBean, 1);
			
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, String.class,
				resultBeanList.getClass());
		Map<String, Object> maps = objectMapper.readValue(response.getResult(), javaType);
				
		return  (T2[]) maps.get("result");		
	}
	
	public void pullAndUpdate() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("startId", 12);
		
		T2[] list = marshalSendAndReceive(data);
		for (T2 entity : list) {	
			//int id = entity.getId();
			Method method = resultBean.getDeclaredMethod("getId");
			int id = (int)method.invoke(entity);
			
			if (getService().findById(id) != null) {
				getService().delete(id);
			}
			getService().save(entity);
		}

	}
	
	public abstract EntityService<T2> getService();
}
