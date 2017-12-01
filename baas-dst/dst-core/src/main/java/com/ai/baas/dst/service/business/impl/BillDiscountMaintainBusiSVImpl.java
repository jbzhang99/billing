package com.ai.baas.dst.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountDeleteRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountUpdateVo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountVo;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.service.atom.impl.billdiscount.AbstractBillDiscountProductManager;
import com.ai.baas.dst.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.dst.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.dst.service.business.interfaces.IBillDiscountMaintainBusiSV;
import com.ai.baas.dst.util.ServiceFactoryUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠服务实现类
 * @author wangluyang
 *
 */
@Service
@Transactional
public class BillDiscountMaintainBusiSVImpl implements IBillDiscountMaintainBusiSV {
    
    @Autowired
    protected ApplicationContext ctx;
    
    @Autowired
    protected IDiscountInfoAtomSV discountInfoAtomSV;
    
//    @Autowired
//    protected IAmcProductDetailAtomSV amcProductDetailAtomSV;
    
    @Autowired
    protected IDiscountExtAtomSV discountExtAtomSV; 

    @Override
    public String addBillDiscountProduct(BillDiscountVo vo) throws BusinessException {
        String serviceName = ServiceFactoryUtil.getBillDiscountProductManager(vo.getDiscountType());
        AbstractBillDiscountProductManager manager = (AbstractBillDiscountProductManager) ctx
                .getBean(serviceName);
        return manager.addBillDiscountProduct(vo);
    }

	@Override
	public void deleteBillDiscountProduct(BillDiscountDeleteRequest request) throws BusinessException {
		// TODO Auto-generated method stub
		String tenantId = request.getTenantId();
        String discountId = request.getDiscountId();
//      discountInfoAtomSV.deleteDiscountInfo(tenantId, discountId);
//      discountExtAtomSV.deleteDiscountExt(tenantId, discountId, null);
        DiscountInfo vo = new DiscountInfo();
        vo.setDiscountId(discountId);
        vo.setTenantId(tenantId);
        vo.setStatus(DstConstants.DiscountInfo.Status.INVALID);
        discountInfoAtomSV.updateDiscountInfo(vo);
	}

	@Override
	public void updateBillDiscountProduct(BillDiscountUpdateVo vo) throws BusinessException {
		// TODO Auto-generated method stub
		DiscountInfo info = discountInfoAtomSV.getDiscountInfo(vo.getTenantId(), vo.getDiscountId());
        if(info == null) {
            return;
        }
        if(StringUtil.isBlank(info.getCalcType())) {
            throw new SystemException("账单优惠活动变更失败，获取不到此账单优惠活动的优惠类型！");
        }
        String serviceName = ServiceFactoryUtil.getBillDiscountProductManager(info.getCalcType());
        AbstractBillDiscountProductManager manager = (AbstractBillDiscountProductManager) ctx
                .getBean(serviceName);
        manager.updateBillDiscountProduct(vo);
	}

}
