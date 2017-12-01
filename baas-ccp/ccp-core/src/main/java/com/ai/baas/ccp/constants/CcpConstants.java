package com.ai.baas.ccp.constants;

public final class CcpConstants {
    
    public static final class NoticeSubscribe {
        public static final class State {
            public static final String VALID = "1";

            private State() {}
        }

        private NoticeSubscribe() {}
    }

    public static final class NoticeSubscribeRecord {
        private NoticeSubscribeRecord() {}
        public static final class State {
            public static final String WAIT = "1";
            public static final String FAILURE = "4";
            public static final String SUCCESS = "3";

            private State() {}
        }
        
    }

    public static final class NoticeSubject {
        private NoticeSubject() {}
        public static final class SubjectId {
            /**
             * 中信信控停机
             */
            public static final String ECITIC_CCP_STOP = "ECITIC_CCP_STOP";
            public static final String ECITIC_CCP_START = "ECITIC_CCP_START";
            public static final String ECITIC_CCP_WARNING_SMS = "ECITIC_CCP_WARNING_SMS";
            public static final String ECITIC_CCP_WARNING_EMAIL = "ECITIC_CCP_WARNING_EMAIL";

            private SubjectId() {}
        }
        
    }

    private CcpConstants() {}
    
    public final class OmcScoutPolicy {
        
        private OmcScoutPolicy() {}
        
        public final class Status {
            
            private Status() {}
            
            public static final String VALID = "1";
            
            public static final String INVALID = "0";
        }
    }
}
