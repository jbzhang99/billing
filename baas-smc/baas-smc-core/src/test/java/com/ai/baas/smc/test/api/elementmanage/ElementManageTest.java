package com.ai.baas.smc.test.api.elementmanage;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.elementmanage.param.ElementSearchRequest;
import com.ai.baas.smc.api.elementmanage.param.ElementSearchResponseVO;
import com.ai.baas.smc.dao.mapper.bo.StlElement;
import com.ai.baas.smc.dao.mapper.bo.StlElementAttr;
import com.ai.baas.smc.service.busi.interfaces.IElementAttrManageBusiSV;
import com.ai.baas.smc.service.busi.interfaces.IElementManageBusiSV;
import com.ai.baas.smc.util.SmcSeqUtil;
import com.ai.opt.base.vo.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ElementManageTest {

    @Autowired
    protected ApplicationContext ctx;

    // @Test
    public void add() {
        StlElement stlElement = new StlElement();
        stlElement.setAttrType("111");
        stlElement.setCreateDeptId("111");
        stlElement.setCreateOperId("111");
        stlElement.setDataCreateType("111");
        stlElement.setElementName("1111");
        stlElement.setElementType("111");
        stlElement.setIsNecessary("2222");
        stlElement.setIsSettlement("111");
        stlElement.setObjectId("222");
        stlElement.setState("1");
        stlElement.setElementCode("1111");
        stlElement.setStatisticsCyc("111");
        stlElement.setValueType("1122");
        stlElement.setIsPrimaryKey("1");
        stlElement.setTenantId("2222");
        Long elementId = SmcSeqUtil.createElementId();
        stlElement.setElementId(elementId);
        IElementManageBusiSV elementManageBusiSV = ctx.getBean(IElementManageBusiSV.class);
        elementManageBusiSV.createElement(stlElement);
    }

    // @Test
    public void addAttr() {
        IElementAttrManageBusiSV elementManageBusiSV = ctx.getBean(IElementAttrManageBusiSV.class);
        StlElementAttr stlElementAttr = new StlElementAttr();
        Long elementId = SmcSeqUtil.createElementAttrId();
        stlElementAttr.setAttrId(elementId);
        stlElementAttr.setRelType("11");
        stlElementAttr.setRelValue("222");
        //stlElementAttr.setSubObjectId(Long.parseLong("222"));
        stlElementAttr.setElementId(Long.parseLong("101"));
        stlElementAttr.setTenantId("11");
        stlElementAttr.setSubElementId(Long.parseLong("101"));
        elementManageBusiSV.createElementAttr(stlElementAttr);
    }

    // @Test
    public void getElement() {
        IElementManageBusiSV elementManageBusiSV = ctx.getBean(IElementManageBusiSV.class);
        StlElement stlElement = elementManageBusiSV.searchElementById(Long.parseLong("101"));
        System.out.println(stlElement.getAttrType());

    }

    // @Test
    public void updateElement() {
        IElementManageBusiSV elementManageBusiSV = ctx.getBean(IElementManageBusiSV.class);
        StlElement stlElement = elementManageBusiSV.searchElementById(Long.parseLong("101"));
        stlElement.setState("1");
        elementManageBusiSV.updateElement(stlElement);
    }

    // @Test
    public void delElement() {
        IElementManageBusiSV elementManageBusiSV = ctx.getBean(IElementManageBusiSV.class);
        // elementManageBusiSV.deleteElement("101", "2222");

    }

    @Test
    public void searchList() {
        IElementManageBusiSV elementManageBusiSV = ctx.getBean(IElementManageBusiSV.class);
        ElementSearchRequest elementSearchRequest = new ElementSearchRequest();

        PageInfo<ElementSearchResponseVO> pageInfo = new PageInfo<ElementSearchResponseVO>();
        pageInfo.setPageNo(Integer.parseInt("1"));
        pageInfo.setPageSize(Integer.parseInt("10"));
        elementSearchRequest.setPageInfo(pageInfo);
        PageInfo<ElementSearchResponseVO> page = elementManageBusiSV
                .searchElementList(elementSearchRequest);
        System.out.println(page.getResult().size());

    }

    public static void main(String[] args) {
        ElementSearchRequest request = new ElementSearchRequest();
        PageInfo<ElementSearchResponseVO> pageInfo = new PageInfo<ElementSearchResponseVO>();
        pageInfo.setPageNo(1);
        pageInfo.setPageSize(10);
        request.setPageInfo(pageInfo);
        System.out.println(JSONObject.fromObject(request).toString());

    }
}
