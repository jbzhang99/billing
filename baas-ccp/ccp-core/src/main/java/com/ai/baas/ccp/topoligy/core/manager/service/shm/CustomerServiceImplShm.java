package com.ai.baas.ccp.topoligy.core.manager.service.shm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.CustomerService;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.util.CacheClient;

/**
 * 获取客户资料信息
 */
@Component
public final class CustomerServiceImplShm implements CustomerService {
	
	private  static final CacheClient cacheClient = CacheClient.getInstance();
	
	@Override
	public Customer getCustomer(String tenantid, String custId) throws OmcException {
		Customer customer = null;
		try{
			StringBuilder table = new StringBuilder();
			table.append("bl_custinfo");
			Map<String, String> params = new TreeMap<String, String>();
			params.put("CUST_ID",custId);
			params.put("TENANT_ID",tenantid);

			List<Map<String, String>> result = cacheClient.doQuery(table.toString(), params);
			if(result == null || result.size()==0){
				throw new OmcException("OMC-SUBS0001B","bl_custinfo表没有找到客户信息!" + params.toString());
			}
			List<Customer> customerList = getCustomers(result);
			if (customerList!=null && customerList.size()>0)
				customer = customerList.get(0);
		}catch (Exception e){
			throw new OmcException("OMC-SUBS0001B",e);
		}
		return customer;
	}
	
	private List<Customer> getCustomers(List<Map<String, String>> result){
		List<Customer> customers = new ArrayList<Customer>();
		for (Map<String, String> map : result) {
			Customer customer = new Customer();
			customer.setCustomerId(map.get("cust_id"));
			customer.setCustType(map.get("cust_type"));
			customer.setCustLevel(map.get("cust_grade"));
			customer.setTenantId(map.get("tenant_id"));
			customer.setPolicyId(map.get("policy_id"));
			customer.setExtCustId(map.get("ext_cust_id"));
			customers.add(customer);
		}
		
		return customers;
	}

}
