package com.ai.opt.sys.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMails;
import com.ai.opt.sys.api.stationmail.param.StationMailsPageQueryVo;
import com.ai.opt.sys.dao.mapper.bo.StationMail;
import com.ai.opt.sys.dao.mapper.bo.StationMailCriteria;
import com.ai.opt.sys.dao.mapper.interfaces.StationMailMapper;
import com.ai.opt.sys.service.business.interfaces.IGnStationMailBusiSV;
import com.ai.opt.sys.util.SystemSeqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GnStationMailBusiSVImpl implements IGnStationMailBusiSV{

    @Autowired
    private StationMailMapper stationMailMapper;

    @Override
    public PageInfo<StationMailVo> getStationMails(StationMailsPageQueryVo mailsPageQueryVo) {
        StationMailCriteria example = new StationMailCriteria();
        example.setOrderByClause("sendtime desc");
        example.setLimitStart((mailsPageQueryVo.getPageNo()-1)*mailsPageQueryVo.getPageSize());
        example.setLimitEnd(mailsPageQueryVo.getPageSize());
        List<StationMail> stationMails = stationMailMapper.selectByExample(example);
        PageInfo<StationMailVo> mailVoPageInfo = new PageInfo<>();
        if(!CollectionUtil.isEmpty(stationMails)){
            List<StationMailVo> stationMailList = new ArrayList<>(stationMails.size());
            for (StationMail stationMail:stationMails){
                StationMailVo stationMailVo = new StationMailVo();
                BeanUtils.copyProperties(stationMailVo,stationMail);
                stationMailList.add(stationMailVo);
            }
            mailVoPageInfo.setResult(stationMailList);
            mailVoPageInfo.setPageNo(mailsPageQueryVo.getPageNo());
            mailVoPageInfo.setPageSize(stationMails.size());

            int total = stationMailMapper.countByExample(null);
            int pageSize = (total + mailsPageQueryVo.getPageSize() - 1) / mailsPageQueryVo.getPageSize();
            mailVoPageInfo.setCount(total);
            mailVoPageInfo.setPageCount(pageSize);
        }else{
            mailVoPageInfo.setPageSize(0);
            mailVoPageInfo.setPageNo(mailsPageQueryVo.getPageNo());
            mailVoPageInfo.setCount(0);
            mailVoPageInfo.setPageCount(0);
        }
        return mailVoPageInfo;
    }

    @Override
    public void saveStationMails(StationMails stationMails) throws BusinessException {
        if(CollectionUtil.isEmpty(stationMails.getMailVoList())){
            throw new BusinessException("000001","mailVoList不能为空");
        }

        for (StationMailVo stationMailVo:stationMails.getMailVoList()){
            if(StringUtil.isBlank(stationMailVo.getRecipient())){
                throw new BusinessException("000002","recipient(收件人)不能为空");
            }
            if(StringUtil.isBlank(stationMailVo.getSender())){
                throw new BusinessException("000003","sender(发件人)不能为空");
            }
            if(StringUtil.isBlank(stationMailVo.getTitle())){
                throw new BusinessException("000004","title(邮件主题)不能为空");
            }
            if(StringUtil.isBlank(stationMailVo.getContent())){
                throw new BusinessException("000005","content(邮件内容)不能为空");
            }
            if(StringUtil.isBlank(stationMailVo.getLevel())){
                throw new BusinessException("000006","level(邮件重要度)不能为空");
            }
            StationMail stationMail = new StationMail();
            BeanUtils.copyProperties(stationMail,stationMailVo);
            stationMail.setMailId(SystemSeqUtil.createMailId());
            stationMailMapper.insertSelective(stationMail);
        }
    }

    @Override
    public StationMailVo getStationMailById(Long mailId) {
        StationMailVo mailVo = new StationMailVo();
        StationMail stationMail = stationMailMapper.selectByPrimaryKey(mailId);
        if(stationMail!=null){
            BeanUtils.copyProperties(mailVo,stationMail);
        }
        return mailVo;
    }
}
