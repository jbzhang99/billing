package com.ai.baas.op.web.controller.common;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/base")
public class BaseDataController {
	private static final Logger LOG = Logger
			.getLogger(BaseDataController.class);
    /**
     * 获取客户等级
     * @param param
     * @param request
     * @return
     * @author zhanglh
     * @ApiCode
     */
    @RequestMapping("/getSelect")
    @ResponseBody
    public ResponseData<BaseCodeInfo> getSelect(QueryInfoParams param,HttpServletRequest request) {
        ResponseData<BaseCodeInfo> responseData=null;
        
        try{
            //BaseCodeInfo getBaseInfo(QueryInfoParams param)
           // HttpSession session = request.getSession();
            //SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
            //TODO 租户Id暂时写死，因为没有数据
            //vo.setTenantId(user.getTenantId());
            param.setTradeSeq(TradeSeqUtil.newTradeSeq("PUB"));
            param.setTenantId("PUB");
            IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
            BaseCodeInfo info=iBaseInfoSV.getBaseInfo(param);
            responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_SUCCESS,"获取数据成功",info);
        }catch(Exception e){
            responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_FAILURE,"获取数据失败",null);
            LOG.info("获取数据失败", e);
        }
    
        return responseData;
    }
    
   
}
