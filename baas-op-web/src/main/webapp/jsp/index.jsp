<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>运营首页</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">

		<div class="management">
			<div class="login-index">
		     <div class="index-title">
		      <p>终于等到你</p>
		      <span>只需六步，即可完成资费配置！让我们即刻开始！</span>
		     </div>
		     <div class="index-cnt">
		      <div class="index-cnt-bg">
		       <p class="inyi1"><a href="${_base}/billSubject/list"><span>配置账单科目</span><img src="${_base}/resources/baasop/images/index-5.png"></a></p>
		       <p class="inyi2">Ready!Go!</p>
		       <p class="inyi3"><a href="${_base}/standardFee/list"><img src="${_base}/resources/baasop/images/index-2.png"><span>配置标准产品</span></a></p>
		       <p class="inyi4"><a href="${_base}/salableProduct/list"><img src="${_base}/resources/baasop/images/index-3.png"><span>配置优惠套餐</span></a></p>
		       <p class="inyi5"><a href="${_base}/preferentialProduct/list"><img src="${_base}/resources/baasop/images/index-4.png"><span>配置详单级优惠</span></a></p>
		       <p class="inyi6"><a href="${_base}/billDiscount/list"><img src="${_base}/resources/baasop/images/index-1.png"><span>配置账单级优惠</span></a></p>
		       <p class="inyi7">上线</p>
		       <p class="inyi8"><a href="${_base}/drSubject/list"><img src="${_base}/resources/baasop/images/index-6.png"><span>配置详单科目</span></a></p>
		      </div>
		     </div>
		    </div>
		</div>
		<!--中间部分结束-->
		
		<!--底部-->
		<%@ include file="/inc/foot.jsp"%>
		<!--底部结束-->
		
</body>

<script type="text/javascript">
	setBreadCrumb("运营首页");
</script>

</html>

