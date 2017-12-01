package com.ai.baas.smc.api.queryimportlog.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryImportLogResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 数据导入日志表查询
     */
    private PageInfo<ImportLogVo> pageInfo;

    public PageInfo<ImportLogVo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ImportLogVo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
