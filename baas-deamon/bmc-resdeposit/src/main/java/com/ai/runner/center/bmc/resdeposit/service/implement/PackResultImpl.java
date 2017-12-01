package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.runner.center.bmc.resdeposit.constants.TypeEnum;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFunResBookSv;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IPackResult;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.MsgControl;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.viv.api.balance.param.ResourceDeposit;

@Service
public class PackResultImpl implements IPackResult {
    
    @Autowired
    // @Qualifier("testFun")
    private IFunResBookSv aIFunResBookSv;

    @Override
    public ResourceDeposit chResourceDeposit(FunResBook book) {
        ResourceDeposit rd = new ResourceDeposit();
        rd.setSystemId(book.getSYSTEM_ID());
        rd.setTenantId(book.getTENANT_ID());
        rd.setExternalId(DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
        // 暂时写死
        rd.setOwnerType(0);
        rd.setOwnerId(Long.parseLong(book.getSUBS_ID()));
        // 资源类型
        TypeEnum type = TypeEnum.getEnum(book.getResourceType());
        if (type == null) {
            // 错单处理
        }
        switch (type) {
        case VPACKAGE:
            rd.setResourceType(10);
            rd.setTotalAmount(Long.parseLong(book.getTotalAmount()));
            break;
        case SPACKAGE:
            rd.setResourceType(50);
            rd.setTotalAmount(Long.parseLong(book.getTotalAmount()));
            break;
        case DPACKAGE:
            rd.setResourceType(60);
            rd.setTotalAmount(Long.parseLong(book.getTotalAmount())*1024);
            break;
        case MPACKAGE:
            rd.setResourceType(99);
            rd.setTotalAmount(Long.parseLong(book.getTotalAmount()));
            break;
        }
        rd.setEffectDate(DateUtil.getDateString(Timestamp.valueOf(book.getACTIVE_TIME()),
                "yyyy-MM-dd HH:mm:ss"));
        rd.setExpireDate(DateUtil.getDateString(Timestamp.valueOf(book.getINACTIVE_TIME()),
                "yyyy-MM-dd HH:mm:ss"));
        // 是否可以转赠
        if ("Y".equalsIgnoreCase(book.getRES_BONUS_FLAG())) {
            rd.setAllowPresent(1);
        } else {
            rd.setAllowPresent(0);
        }
        // 写死 可转兑/买卖标识
        rd.setAllowConvert(0);
        // 是否清零
        if ("Y".equalsIgnoreCase(book.getRES_CLEAR_FLAG())) {
            rd.setAllowClear(0);
        } else {
            rd.setAllowClear(1);
        }
        // 写死 入账类型
        rd.setSourceType(0);
        // 入账来源
        rd.setSourceId(Long.parseLong(book.getSUBS_PRODUCT_ID()));
        // 写死即买即用标识
        rd.setUseFlag("0");
        
        rd.setProductType(aIFunResBookSv.getProductType(book.getPRODUCT_ID(),book.getTENANT_ID()));
        return rd;
    }

    @Override
    public MsgControl chMsgControl(FunResBook book) {
        MsgControl ms = new MsgControl();
        ms.setEvent_id(DateUtil.getDateString(DateUtil.YYYYMMDDHHMM));
        ms.setSystem_id(book.getSYSTEM_ID());
        ms.setTenant_id(book.getTENANT_ID());
        ms.setSource_type("resource");
        // 暂时写死
        ms.setOwner_type("subs");
        ms.setOwner_id(book.getSUBS_ID());
        // 资源类型 事件类型？？
        TypeEnum type = TypeEnum.getEnum(book.getResourceType());
        if (type == null) {
            // 错单处理
        }
        switch (type) {
        case VPACKAGE:
            ms.setEvent_type("VOICE");
            ms.setAmount_type("VOICE");
            break;
        case SPACKAGE:
            ms.setEvent_type("SMS");
            ms.setAmount_type("SM");
            break;
        case DPACKAGE:
            ms.setEvent_type("DATA");
            ms.setAmount_type("DATA");
            break;
        case MPACKAGE:
            ms.setEvent_type("vc");
            ms.setAmount_type("VC");
            break;
        }
        ms.setAmount(book.getTotalAmount());
        // 写死
        ms.setAmount_mark("PLUS");
        ms.setExpanded_info("{}");
        return ms;
    }

}
