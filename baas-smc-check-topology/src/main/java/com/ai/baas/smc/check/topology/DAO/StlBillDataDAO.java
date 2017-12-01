package com.ai.baas.smc.check.topology.DAO;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.baas.smc.check.topology.vo.StlBillData;
import com.ai.baas.storm.jdbc.JdbcTemplate;
import com.ai.opt.sdk.util.StringUtil;

public class StlBillDataDAO {
    public List<StlBillData> query(Connection conn, String yyyyMm, StlBillData stlBillData) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" select BILL_ID billId, BILL_FROM billFrom, BATCH_NO batchNo, ");
        sqlBuilder.append(" TENANT_ID tenantId, POLICY_CODE policyCode, ");
        sqlBuilder.append(" STL_OBJECT_ID stlObjectId, ");
        sqlBuilder.append(" STL_ELEMENT_ID stlElementId, STL_ELEMENT_SN stlElementSn, ");
        sqlBuilder.append(" BILL_STYLE_SN billStyleSn, BILL_TIME_SN billTimeSn, ");
        sqlBuilder.append(" BILL_START_TIME billStartTime, BILL_END_TIME billEndTime, ");
        sqlBuilder.append(" ORIG_FEE origFee, CHECK_STATE checkState, DIFF_FEE diffFee, ");
        sqlBuilder.append(" CHECK_STATE_DESC checkStateDesc, CHECK_TIME checkTime, ");
        sqlBuilder.append(" ADJUST_FEE adjustFee, FINAL_FEE finalFee, ADJUST_TIME adjustTime, ");
        sqlBuilder.append(" ADJUST_OPER_ID adjustOperId, ADJUST_DESC adjustDesc, ");
        sqlBuilder.append(" EXTEND_STR extendStr,");
        sqlBuilder.append(" CREATE_DEPT_ID createDeptId, CREATE_OPER_ID createOperId, ");
        sqlBuilder.append(" CREATE_TIME createTime");
        sqlBuilder.append(" from stl_bill_data_").append(yyyyMm);
        if (stlBillData != null) {
            sqlBuilder.append(" where tenant_id = '").append(stlBillData.getTenantId()).append("'");
            if (!StringUtil.isBlank(stlBillData.getBatchNo())) {
                sqlBuilder.append(" and batch_no = '").append(stlBillData.getBatchNo()).append("'");
            }
            if (!StringUtil.isBlank(stlBillData.getBillFrom())) {
                sqlBuilder.append(" and bill_from = '").append(stlBillData.getBillFrom())
                        .append("'");
            }
            if (!StringUtil.isBlank(stlBillData.getBillTimeSn())) {
                sqlBuilder.append(" and bill_time_sn = '").append(stlBillData.getBillTimeSn())
                        .append("'");
            }
            if (!StringUtil.isBlank(stlBillData.getStlElementSn())) {
                sqlBuilder.append(" and STL_ELEMENT_SN = '").append(stlBillData.getStlElementSn())
                        .append("'");
            }
        }
        sqlBuilder.append(" order by create_time desc ");

        return JdbcTemplate.query(sqlBuilder.toString(), conn, new BeanListHandler<>(
                StlBillData.class));
    }
}
