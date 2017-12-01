<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
<%@ include file="/inc/inc.jsp"%>
<title>功能页</title>
<meta name="viewport" content="width=device-width; initial-scale=1; user-scalable=1;" />
<link href="${_base}/resources/baaspt/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/frame.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/global.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/modular.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--导航 第一屏-->
<div class="mainbav">
		<div class="topnav">
		    <div class="topnav-main">
		        <%@ include file="/inc/topnav-main-ul.jsp"%>
		    </div>
		</div>

        <div class="subnav-index"><!-- 导航-->
            <%@ include file="/inc/subnav-ul.jsp"%>
        </div><!-- 导航结束-->
       <div class="subnav-word">
       <p>通过云计算的方式为企业提供<span>定价、计费、结算及账务管理</span>等经营支撑服务<br>助力企业订用式商业模式的转型及成长</p>
       </div>
       <div class="subnav-img">
       <p><A href="#"><img src="${_baaspt }/images/pc.png"></A></p>
       </div>
</div>   
<!--第二屏-->
<div class="tpList-none">
    <div class="tpList-none-warp">
        <div class="tpList-none-title">
        <p>复杂计费</p>
        </div>
         <div class="tpList-none-list">
             <ul>
                 <li class="bt">标准资费管理</li>
                 <li>根据业务类型可自定义配置基础资费数据，随时调整随时生效</li>
             </ul>
             <ul>
                 <li class="bt">套餐资费管理</li>
                 <li>支持组合资费、套餐资费等规则配置，助力企业构建多维度报价体系</li>
             </ul>
             <ul>
                 <li class="bt">优惠规则管理</li>
                 <li>提供多种优惠、活动规则配置项，满足企业多样化营销的诉求</li>
             </ul>
             <ul class="border-none">
                 <li class="bt">实时费用核算</li>
                 <li>核心计费引擎可实时处理业务费用核算，实现秒级精细化营销支撑</li>
             </ul>
         </div>
    </div>
</div>
<!--第三屏-->
<div class="tpList-tow">
    <div class="tpList-tow-warp">
   		<div class="tpList-tow-warp-left">
            <div class="tpList-tow-title">
            <p>多边结算</p>
            </div>
            <div class="tpList-tow-list">
                <ul>
                    <li class="bt">对账管理</li>
                    <li>通过手动和自动两种数据采集方式，实现自动化多边对账</li>
                </ul> 
                <ul>
                    <li class="bt">调账管理</li>
                    <li>当发现对账金额有误时，可以通过调账的功能及时进行修订</li>
                </ul> 
                <ul>
                    <li class="bt">结算规则配置</li>
                    <li>针对企业间结算业务的多样性，可以快速配置个性化的多变结算规则</li>
                </ul> 
            </div>
            
        </div>
    </div>
</div>
<!--第四屏-->
<div class="tpList-three">
    <div class="tpList-three-warp">
    <div class="tpList-three-warp-right">
            <div class="tpList-three-title">
            <p>收入保障</p>
            </div>
            <div class="tpList-three-list">
                <ul>
                    <li class="bt">收入流监控机制</li>
                    <li>提供账户级熔断机制，帮助企业更好的控制欠费、坏账等风险；提供数据流传输，处理，保存等过程的准确性保障机制</li>
                </ul> 
                <ul>
                    <li class="bt">信用监控</li>
                    <li>管理客户的信用级别，帮助企业有效构建客户服务体系</li>
                </ul> 
            </div>
        </div>
    </div>
</div>
<!--第五屏-->
<div class="tpList-for">
    <div class="tpList-for-warp">
   		<div class="tpList-for-warp-left">
            <div class="tpList-for-title">
            <p>账务管理</p>
            </div>
            <div class="tpList-for-list">
                <ul>
                    <li class="bt">账单管理</li>
                    <li>管理客户在规定账期内因业务所产生的统计费用，支持账单类别定制</li>
                </ul> 
                <ul>
                    <li class="bt">详单管理</li>
                    <li>管理客户在规定账期内因业务所产生的消费明细，支持详单费用类型的定制</li>
                </ul> 
                <ul>
                    <li class="bt">报表管理</li>
                    <li>提供表格、图型等多种形式，支持自定义、动态分析企业经营数据</li>
                </ul> 
            </div>
            
        </div>
    </div>
</div>
<!--第六屏-->
<div class="tpList-six">
    <div class="tpList-six-warp">
         <div class="tpList-six-title">
          <p>安全托管</p>
         </div>   
    <div class="tpList-six-list"></div>
    </div>
</div>
<!--footer-->
<%@ include file="/inc/foot.jsp"%>

</body>
</html>
