package com.yunpos.KDT;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;

public class KdtApiClient {
	private static final String Version = "1.0";

    private static final String apiEntry = "https://open.koudaitong.com/api/entry?";

    private static final String format = "json";

    private static final String signMethod = "md5";
    
    private static final String DefaultUserAgent = "KdtApiSdk Client v0.1";

    private String appId;
    private String appSecret;
    
    public KdtApiClient() {

    }

    public KdtApiClient(String appId, String appSecret) throws Exception{
        if ("".equals(appId) || "".equals(appSecret)){
            throw new Exception("appId 和 appSecret 不能为空");
        }
        
        this.appId = appId;
        this.appSecret = appSecret;
    }
    
    public HttpResponse get(String method, HashMap<String,String> parames) throws Exception{
        String url = apiEntry + getParamStr(method, parames);
        
        HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", DefaultUserAgent);
 
		HttpResponse response = client.execute(request);
		return response;
    }
    
    /** 
     * 重写验证方法，取消检测ssl 
     */  
	private TrustManager truseAllManager = new X509TrustManager() {
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {
			// TODO Auto-generated method stub
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {
			// TODO Auto-generated method stub
		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	private void enableSSL(HttpClient httpclient){  
        //调用ssl  
         try {  
                SSLContext sslcontext = SSLContext.getInstance("TLS");  
                sslcontext.init(null, new TrustManager[] { truseAllManager }, null);  
                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);  
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
                Scheme https = new Scheme("https", sf, 443);  
                httpclient.getConnectionManager().getSchemeRegistry().register(https);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
    }  
	
    
    public HttpResponse get(String url) throws Exception{       
        HttpClient client = new DefaultHttpClient();
        enableSSL(client);
	
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", DefaultUserAgent);
		HttpResponse response = client.execute(request);
		return response;
    }

    
    public HttpResponse post(String method, HashMap<String, String> parames, List<String> filePaths, String fileKey) throws Exception{
    	String url = apiEntry + getParamStr(method, parames);
    	
    	HttpClient client = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(url);
    	httppost.addHeader("User-Agent", DefaultUserAgent);
    	
    	if(null != filePaths && filePaths.size() > 0 && null != fileKey && !"".equals(fileKey)){
	    	MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	    	for(int i = 0; i < filePaths.size(); i++){
	    		File file = new File(filePaths.get(i));
	    		ContentBody cbFile = new FileBody(file);
	            mpEntity.addPart(fileKey, cbFile);
	    	}
	        
	        httppost.setEntity(mpEntity);
    	}
    	
        HttpResponse response = client.execute(httppost);
        
        return response;
    }
    
	public HttpResponse post(String url, HashMap<String, String> params) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		enableSSL(client);
		
		List<NameValuePair> param = new ArrayList<NameValuePair>(); // 参数
		if (params != null) {
			// 添加参数
			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		HttpPost request = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(param, "UTF-8");
		request.setEntity(entity);

		HttpResponse response = client.execute(request);
		return response;

	}
    
    public String getParamStr(String method, HashMap<String, String> parames){
        String str = "";
        try {
            str = URLEncoder.encode(buildParamStr(buildCompleteParams(method, parames)), "UTF-8")
                    .replace("%3A", ":")
                    .replace("%2F", "/")
                    .replace("%26", "&")
                    .replace("%3D", "=")
                    .replace("%3F", "?");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
    
    private String buildParamStr(HashMap<String, String> param){
        String paramStr = "";
        Object[] keyArray = param.keySet().toArray();
        for(int i = 0; i < keyArray.length; i++){
            String key = (String)keyArray[i];

            if(0 == i){
                paramStr += (key + "=" + param.get(key));
            }
            else{
                paramStr += ("&" + key + "=" + param.get(key));
            }
        }

        return paramStr;
    }


    private HashMap<String, String> buildCompleteParams(String method, HashMap<String, String> parames) throws Exception{
        HashMap<String, String> commonParams = getCommonParams(method);
        for (String key : parames.keySet()) {
			if(commonParams.containsKey(key)){
				throw new Exception("参数名冲突");
			}
			
			commonParams.put(key, parames.get(key));
		}
        
        commonParams.put(KdtApiProtocol.SIGN_KEY, KdtApiProtocol.sign(appSecret, commonParams));
        return commonParams;
    }

    private HashMap<String, String> getCommonParams(String method){
       HashMap<String, String> parames = new HashMap<String, String>();
        parames.put(KdtApiProtocol.APP_ID_KEY, appId);
        parames.put(KdtApiProtocol.METHOD_KEY, method);
        parames.put(KdtApiProtocol.TIMESTAMP_KEY, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        parames.put(KdtApiProtocol.FORMAT_KEY, format);
        parames.put(KdtApiProtocol.SIGN_METHOD_KEY, signMethod);
        parames.put(KdtApiProtocol.VERSION_KEY, Version);
        return parames;
    }
}
