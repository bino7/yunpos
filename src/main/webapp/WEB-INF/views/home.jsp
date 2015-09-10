<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./commons/taglibs.jsp" %>
<html>
<head>
    <title>Home Page</title>
    
<script>
//返回当前页面高度
jQuery(document).ready(function(){
	 var redirect_uri = encodeURIComponent("http://t.o2o520.com/pay/wechatpay/wap/create");
	 var action = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9a930755fdc2698d&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	 alert(action);
});

</script>
</head>
<body>
欢迎来到云铺后台！
</body>
</html>
