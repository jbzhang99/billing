package com.ai.opt.sys.constants;

public final class SystemExceptCode {
    private SystemExceptCode() {

    }

    public final static class ErrorCode {
        private ErrorCode() {
        }

        public static final String PARAM_NULL_ERROR = "10001";
        public static final String PARAM_VALUE_ERROR = "10002";
        //功能有下级功能
        public static final String FUNC_HAVECHILD_ERROR = "10003";
    }
    
}
