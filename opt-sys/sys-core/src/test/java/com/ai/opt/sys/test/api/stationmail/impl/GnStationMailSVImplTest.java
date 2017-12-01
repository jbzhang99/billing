package com.ai.opt.sys.test.api.stationmail.impl;


import com.ai.opt.sys.api.stationmail.impl.GnStationMailManageSVImpl;
import com.ai.opt.sys.api.stationmail.impl.GnStationMailQuerySVImpl;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class GnStationMailSVImplTest {

    @Autowired
    private GnStationMailQuerySVImpl mailQuerySV;

    @Autowired
    private GnStationMailManageSVImpl mailManageSV;

    @Test
    public void saveStationMails(){
        StationMails mails = new StationMails();
        List<StationMailVo> mailList = new ArrayList<>();
        for(int i=0;i<10;i++){
            StationMailVo mailVo = new StationMailVo();
            mailVo.setSender("15501009774");
            mailVo.setSenderName("王永新");
            mailVo.setRecipient("11111111111");
            mailVo.setRecipientName("王璐阳");
            mailVo.setTitle("测试");
            mailVo.setContent("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
            mailVo.setLevel("normal");
            mailVo.setSendtime(new Timestamp(System.currentTimeMillis()));
            mailList.add(mailVo);
        }
        mails.setMailVoList(mailList);
        mailManageSV.saveStationMails(mails);
    }
}
