package com.ai.baas.bmc.srv.flow.billing.rule;

import com.ai.baas.bmc.srv.flow.billing.rule.impl.Cunit;
import com.ai.baas.bmc.srv.flow.billing.rule.impl.Package;
import com.ai.baas.bmc.srv.flow.billing.rule.impl.Step;
import com.ai.baas.bmc.srv.flow.billing.rule.impl.Unit;
import com.ai.baas.bmc.srv.util.BaasConstants;


public class BillingRuleFactory {
	
	public static IBilling getRuleInstance(String rule){
		IBilling ibilling = null;
		switch (rule) {
		case BaasConstants.CHARGE_TYPES_UNIT:
			ibilling = new Unit();
			break;
		case BaasConstants.CHARGE_TYPES_CUNIT:
			ibilling = new Cunit();
			break;
		case BaasConstants.CHARGE_TYPES_PACKAGE:
			ibilling = new Package();
			break;
		case BaasConstants.CHARGE_TYPES_STEP:
			ibilling = new Step();
			break;
		default:
			break;
		}
		return ibilling;
	}
}
