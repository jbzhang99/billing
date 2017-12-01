package com.ai.baas.ccp.topoligy.core.manager.service.shm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.AccountService;
import com.ai.baas.ccp.topoligy.core.pojo.Account;
import com.ai.baas.ccp.topoligy.core.util.CacheClient;

/**
 * 获取账户资料信息
 */
@Component
public final class AccountServiceImplShm implements AccountService {

    private static final CacheClient cacheClient = CacheClient.getInstance();

    static final String TABLE_NAME = "bl_acct_info";

    @Override
    public Account selectById(String tenantid, String acctId) throws OmcException {
        try {
            Map<String, String> params = new TreeMap<String, String>();
            params.put("ACCT_ID", acctId);
            params.put("TENANT_ID", tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(TABLE_NAME, params);
            if (result == null || result.isEmpty()) {
                throw new OmcException("OMC-SUBS0001B", "BL_ACCTINFO表没有找到账户信息!");
            }
            return getAccounts(result).get(0);
        } catch (Exception e) {
            throw new OmcException("OMC-SUBS0001B", e);
        }
    }

    @Override
    public List<Account> selectBycustId(String tenantid, String custId) throws OmcException {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("CUST_ID", custId);
            params.put("TENANT_ID", tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(TABLE_NAME, params);
            if (result == null || result.size() == 0) {
                throw new OmcException("OMC-SUBS0001B", "BL_ACCTINFO表没有找到账户信息!");
            }
            return getAccounts(result);
        } catch (Exception e) {
            throw new OmcException("OMC-SUBS0001B", e);
        }
    }

    private List<Account> getAccounts(List<Map<String, String>> result) {
        List<Account> accounts = new ArrayList<Account>();
        for (Map<String, String> map : result) {
            Account account = new Account();
            account.setAccountId(map.get("acct_id"));
            account.setOwnerType(map.get("owner_type"));
            account.setOwnerId(map.get("owner_id"));
            account.setAcctType(map.get("acct_type"));
            account.setTenantId(map.get("tenant_id"));
            accounts.add(account);
        }

        return accounts;
    }

}
