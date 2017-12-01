<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
<%@ include file="/inc/inc.jsp"%>
<title>关于我们</title>
<meta name="viewport" content="width=device-width; initial-scale=1; user-scalable=1;" />
<link href="${_base}/resources/baaspt/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/frame.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/global.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/modular.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="topnav"><!--头部-->
    <div class="topnav-main">
        <%@ include file="/inc/topnav-main-ul.jsp"%>
    </div>
</div>
<div class="nav-bg">
	 <div class="er-subnav"><!--导航-->
	            <%@ include file="/inc/er-subnav-ul.jsp"%>
	 </div><!-- 导航结束-->
</div>
<div class="about-banner"></div>

<div class="about-culture"><!--公司简介-->
       <div class="about-culture-cnt">
        <ul>
            <li class="jianj">——  公司简介  ——</li>
            <li>
                    <p>亚信集团成立于1993年，是亚太区第一大、全球第二大的业务运营支撑软件（BOSS软件）供应商。亚信公司总部设在北京，目前拥有员工逾14000名，为全球电信运营商、虚拟运营商以及行业客户提供服务，在欧洲、美洲、亚洲以及中国各省会城市设有分支机构。
                 </p>
                <p>在当前中国经济自主创新、转型升级的关键时刻，亚信再次转型，致力于成为“产业互联网时代的领航者”。亚信在线是亚信集团旗下致力于产业互联网创新的公司，为互联网公司和进行互联网化转型的企业提供客户运营所需的核心支撑能力，帮助企业快速实现产品和服务的数字化运营，从生产运营转向客户运营，使企业从容面对业务环境变化。通过数字互联，亚信在线还将帮助企业实现产业间的协同，实现跨企业的客户、产品、订单和清算的交换，建设产业互联生态圈，使跨界协作不断产生新的商业模式与业务增长点。</p>
                <p>基于亚信集团在通信领域多年积攒的业务经验和运营支撑能力，亚信在线推出了支持企业级客户运营和业务支撑的SaaS产品系列。通过在线SaaS及能力开放模式，为类运营商企业提供核心业务运营支撑能力，帮助企业互联网化，助力产业互联。目前亚信在线己面向新媒体、移动虚商、汽车、宽带接入商、制造等构建了行业应用SaaS，并已在多家行业领军企业中应用，活灵的业务支撑及快速交付能力得到业界高度认同。</p>
                <p>亚信在线为产业公司互联网化提供全面的软件支撑，并以SaaS方式交付服务，使企业以更加成本有效、更安全和更快捷的方式实现数字化运营。</p>
            </li>
        </ul>
        </div> 
</div>

    <div class="about-contact"><!--联系我们-->
        <ul>
            <li class="bt">关于团队</li>
            <li>
                <p>我们，是高效、灵活、敏捷的创新型团队</p>
                <p>我们，是来自互联网和电信运营商领域的精英</p>
                <p>我们，有多位电信领域专家级的技术大牛</p>
                <p>我们，只为打造更易用更省心的专业计费产品</p>
            </li>
        </ul>
        <ul>
            <li class="bt">联系我们</li>
            <li>
                <p>咨询热线：010-8790-1321</p>
                <p>QQ咨询：3403137015</p>
                <p>邮箱咨询：BaaS_service@asiainfo.com</p>
                <p>公司地址：北京市海淀区西北旺东路10号院亚信总部研发中心大厦</p>
            </li>
        </ul>
         <ul  class="ul-widht">
            <li class="bt">微信公众号</li>
            <li><img src="${_baaspt }/images/erwm.png"></li>
        </ul>
    </div>

<!--footer-->
<%@ include file="/inc/foot.jsp"%>

</body>
</html>
