package com.ai.baas.prd.constants;

public final class PrdConstants {
	
	private PrdConstants() {
	}
	
	 public final static class SEQ {
	        private SEQ() {
	        }

	        public static final String PM_POLICY_INFO$POLICY_ID$SEQ = "PM_POLICY_INFO$POLICY_ID$SEQ";

	        public static final String PM_POLICY_VARIABLE$ID$SEQ = "PM_POLICY_VARIABLE$ID$SEQ";
	        
	        public static final String PM_POLICY_DETAIL$ID$SEQ = "PM_POLICY_DETAIL$ID$SEQ";
	        
	        public static final String PM_POLICY_FACTOR$ID$SEQ = "PM_POLICY_FACTOR$ID$SEQ";
	        
	        public static final String PM_DIMENSION_INFO$ID$SEQ="PM_DIMENSION_INFO$ID$SEQ";
	        
	        public static final String PM_DIMENSION_BRANCH$ID$SEQ="PM_DIMENSION_BRANCH$ID$SEQ";
	        
	        public static final String PM_CATEGORY_INFO$ID$SEQ="PM_CATEGORY_INFO$ID$SEQ";
	    }
	 
	 public final static class PRODUCT{
		 private PRODUCT(){
			 
		 }
		 
		 public static final String CATEGORY_TYPE="ZJ";
		 
		 public static final String LEVEL1="1";
		 
		 public static final String LEVEL2="2";
		 
		 
		 
		 
	 }
	 
	 public static final String POLICY_TYPE_STEP = "STEP";
	 public static final String VAR_TYPE_SINGLE = "SINGLE";
	 public static final String VAR_TYPE_INTERVAL = "INTERVAL";
	 public static final String STEP_PRICE_DELIMITER = "##";
	 
}
