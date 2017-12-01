package com.ai.baas.smc.service.atom.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ai.baas.dshm.api.dshmprocess.interfaces.IdshmSV;
import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.dao.mapper.bo.StlImportLog;
import com.ai.baas.smc.dao.mapper.bo.StlImportLogCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.baas.smc.service.atom.interfaces.IImportLogAtomSV;
import com.ai.baas.smc.vo.dshm.ImportLogDshmVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.alibaba.fastjson.JSON;

@Component
public class ImportLogAtomSVImpl implements IImportLogAtomSV {
    private static final Logger LOG = LogManager.getLogger(ImportLogAtomSVImpl.class);

    @Override
    public boolean isExistBatchNo(String tenantId, String batchNo) {
        StlImportLogCriteria importLogCriteria = new StlImportLogCriteria();
        importLogCriteria.createCriteria().andTenantIdEqualTo(tenantId).andBatchNoEqualTo(batchNo);
        int count = MapperFactory.getStlImportLogMapper().countByExample(importLogCriteria);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void writeLogToCache(StlImportLog stlImportLog) {
        LOG.info("将文件导入日志写入共享缓存...");
        ImportLogDshmVO dshmVO = new ImportLogDshmVO();
        dshmVO.setBatch_no(stlImportLog.getBatchNo());
        dshmVO.setBill_time_sn(stlImportLog.getBillTimeSn());
        dshmVO.setData_type(stlImportLog.getDataType());
        dshmVO.setImp_file_name(stlImportLog.getImpFileName());
        dshmVO.setImp_file_url(stlImportLog.getImpFileUrl());
        dshmVO.setImport_records(stlImportLog.getImportRecords());
        dshmVO.setImport_time(stlImportLog.getImportTime());
        dshmVO.setLog_id(stlImportLog.getLogId());
        dshmVO.setObject_id(stlImportLog.getObjectId());
        dshmVO.setTenant_id(stlImportLog.getTenantId());

        IdshmSV idshmSV = DubboConsumerFactory.getService("idshmSV");
        int a;
        try {
            a = idshmSV.initLoader(SmcCacheConstant.Dshm.TableName.STL_IMPORT_LOG,
                    JSON.toJSONString(dshmVO), SmcCacheConstant.Dshm.OptType.INSERT);
        } catch (BusinessException e) {
            LOG.error("写入共享缓存失败", e);
            throw e;
        } catch (Exception e) {
            LOG.error("写入共享缓存失败", e);
            throw new SystemException(e);
        }
        LOG.info(a);
    }

}
