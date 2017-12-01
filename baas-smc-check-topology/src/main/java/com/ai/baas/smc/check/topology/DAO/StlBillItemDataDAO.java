package com.ai.baas.smc.check.topology.DAO;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ai.baas.smc.check.topology.vo.StlBillItemData;
import com.ai.baas.storm.jdbc.JdbcTemplate;

public class StlBillItemDataDAO {
    public List<StlBillItemData> query(Connection conn, String yyyyMm,
            StlBillItemData stlBillItemData) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" select BILL_ITEM_ID  billItemId         ,    ");
        sqlBuilder.append("  BILL_ID             billId             ,    ");
        sqlBuilder.append("  TENANT_ID           tenantId           ,    ");
        sqlBuilder.append("  ITEM_TYPE           itemType           ,    ");
        sqlBuilder.append("  FEE_ITEM_ID         feeItemId          ,    ");
        sqlBuilder.append("  TOTAL_FEE           totalFee           ,    ");
        sqlBuilder.append("  CHECK_STATE         checkState         ,    ");
        sqlBuilder.append("  DIFF_FEE            diffFee            ,    ");
        sqlBuilder.append("  CHECK_STATE_DESC    checkStateDesc     ,    ");
        sqlBuilder.append("  CHECK_TIME          checkTime          ,    ");
        sqlBuilder.append("  ADJUST_TIME         adjustTime         ,    ");
        sqlBuilder.append("  ADJUST_OPER_ID      adjustOperId       ,    ");
        sqlBuilder.append("  ADJUST_DESC         adjustDesc         ,    ");
        sqlBuilder.append("  CREATE_DEPT_ID      createDeptId       ,    ");
        sqlBuilder.append("  CREATE_OPER_ID      createOperId       ,    ");
        sqlBuilder.append("  CREATE_TIME         createTime             ");
        sqlBuilder.append(" from stl_bill_item_data_").append(yyyyMm);
        if (stlBillItemData != null) {
            sqlBuilder.append(" where tenant_id = '").append(stlBillItemData.getTenantId())
                    .append("'");
            if (null != stlBillItemData.getBillId() && stlBillItemData.getBillId() != 0) {
                sqlBuilder.append(" and BILL_ID = ").append(stlBillItemData.getBillId());
            }
        }

        return JdbcTemplate.query(sqlBuilder.toString(), conn, new BeanListHandler<>(
                StlBillItemData.class));
    }
}
