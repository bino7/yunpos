<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--  <%@ include file="../views/commons/taglibs.jsp" %>
<%@ include file="../views/commons/meta.jsp" %>  --%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	//微信支付
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', jsApiCall,
						false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', jsApiCall);
				document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
			}
		} else {
			jsApiCall();
			
		}
	});

	function jsApiCall() {
		var str = window.navigator.userAgent;
		var version = str.substring(8, 11);
		if (version != "5.0") {
			alert("微信浏览器系统版本过低，请将微信升级至5.0以上");
		} else {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : "${appId}",
				"timeStamp" : "${timeStamp}",
				"nonceStr" : "${nonceStr}",
				"package" : "${packagess}",
				"signType" : "${signType}",
				"paySign" : "${paySign}"
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request：ok") {
					window.location.href = "${ctx}/ajax/transaction/update/${id}?status=2";
				} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
					window.location.href = "${ctx}/ajax/transaction/update/${id}?status=6";
				} else if (res.err_msg == "get_brand_wcpay_request:fail") {
					window.location.href = "${ctx}/ajax/transaction/update/${id}?status=6";
				}
			});
		}
	}
</script>
</head>
<body>
</body>
</html>