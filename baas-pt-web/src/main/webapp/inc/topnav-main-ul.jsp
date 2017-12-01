<%@ page contentType="text/html;charset=UTF-8" language="java" %>

			<ul>
                <li>
                    <p><img src="${_baaspt }/images/iPhone.png"></p>
                    <p>010-87901321</p>
                </li>
                <li class="right">
                	<c:choose>
                		<c:when test="${empty sessionScope.user_session_key.accountId || 0==sessionScope.user_session_key.accountId}">
		                    <p><a href="${baas_uac_reg_url }">注册</a></p>
		                    <p>|</p>
		                    <p><a href="${_base }/login">登录</a></p>
	                    </c:when>
	                    <c:otherwise>
					         <c:choose>
							    <c:when test="${empty sessionScope.user_session_key.shortNickName}">
							      <p><a href="${baas_uac_center_url }">你好，${sessionScope.user_session_key.nickName}</a></p>
							    </c:when>
							    <c:otherwise>
							         <p><a href="${baas_uac_center_url }">你好，${sessionScope.user_session_key.shortNickName}</a></p>
							    </c:otherwise>
							</c:choose>
							<p>|</p>
		                    <p><a href="${_base }/ssologout">退出</a></p>
							
					    </c:otherwise>
                    </c:choose>
                </li>
            </ul>