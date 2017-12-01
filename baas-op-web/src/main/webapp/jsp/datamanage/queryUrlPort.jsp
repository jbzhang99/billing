<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>地址和端口查询</title>
    <%@ include file="/inc/inc.jsp"%>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

  <div class="wrapper">
  	<div class="management"><!--资费管理外侧-->
  	<div class="management-cnt">
  	<div class="nav-form">           
               <ul>
                 <li>
                 <p class="word">测试URL</p>
                 <p><input type="text" class="int-xxxlarge" id="testUrl" value=""></p>
                 </li>
               </ul>
               <ul>
                 <li>
                 <p class="word">正式URL</p>
                 <p><input type="text" class="int-xxxlarge" id="formalUrl" value=""></p>
                 </li>
               </ul>
               <ul>
                 <li>
                 <p class="word">口令</p>
                 <p><input type="text" class="int-medium" id="passowrd" value=""></p>
                 </li>
               </ul>
        </div>
        <div class="zl-sm">
        	 <p>说明：</p>
        	 <span>a.  对接URL是集成BaaS产品服务时所用；</span>
        	 <span id="upnumLimited">b.  测试URL在系统集成和联调期间使用，可处理10000条数据；</span>
        	 <span>c.  正式签约后正式端口开放使用，正式端口没有数据量的上限；</span>
        	 <span>d.  调用服务集成时请使用口令，每个租户口令为单独分配，请注意保密。</span>
        </div>
	  </div>
	  </div>
  </div>
  <!--底部-->
<%@ include file="/inc/foot.jsp"%>
<script type="text/javascript">
		(function () {
			seajs.use('app/jsp/datamanage/queryUrlPort', function (QueryUrlPortPager) {
				var pager = new QueryUrlPortPager({element: document.body});
				pager.render();
			});
		})();
	</script>
<!--底部结束-->
</body>
</html>
