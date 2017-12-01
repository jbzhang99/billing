package com.ai.citic.billing.web.citicjsonmock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * 中信云接口模拟返回
 */
@RestController
@RequestMapping("/usrmgt")
public class MockJsonController {

    @RequestMapping(value = "/users.do",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String searchUser(String select_type,String select_id){
        return  "{\n" +
                "  \"users\": [{\n" +
                "    \"user_id\": \"fd634834-59a1-416d-a124-f74fb99069d9\",\n" +
                "    \"employee_id\": \"009528\",\n" +
                "    \"name\": \"张三\",\n" +
                "    \"mail\": \"someone@citic.com\",\n" +
                "    \"cell\": \"13600000000\",\n" +
                "    \"org_id\": \"52dbb3e5-ec31-4989-be82-705123c45eef\",\n" +
                "    \"roles\": [{\n" +
                "      \"role_id\": \"3\"\n" +
                "    }],\n" +
                "    \"services\": [{\n" +
                "      \"service_id\": \"cffe8d3c-7628-4378-a07f-a7bf6c3871a0\"\n" +
                "    },{\n" +
                "      \"service_id\": \"b1305db4-c5e3-4413-80fa-b0273536d0b5\"\n" +
                "    }],\n" +
                "    \"operating_services\": [{\n" +
                "      \"service_id\": \"b2b73f3b-0aa0-4000-9c8e-1449a0d3cf87\"\n" +
                "    },{\n" +
                "      \"service_id\": \"fd634834-59a1-416d-a124-f74fb99069d9\"\n" +
                "    }]\n" +
                "  }]\n" +
                "}";
    }

    @RequestMapping(value = "/service_org.do",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String searchOrg(String select_type,String select_id){
        return "{\n" +
                "  \"orgs\": [{\n" +
                "    \"org_id\": \"52dbb3e5-ec31-4989-be82-705123c45eef\",\n" +
                "    \"citic_org_id\": \"000025\",\n" +
                "    \"name\": \"中信证券股份有限公司\",\n" +
                "    \"abbreviation\": \"中信证券\",\n" +
                "    \"superior\": \"52dbb3e5-ec31-4989-be82-705123c45eef\",\n" +
                "    \"is_cost_center\": true,\n" +
                "    \"is_tenant\": true,\n" +
                "    \"is_supplier\": true,\n" +
                "    \"vpc_id\": \"b1305db4-c5e3-4413-80fa-b0273536d0b5\",\n" +
                "    \"uri\": \"http://csadaptor.citic.com/\",\n" +
                "    \"vpcs\": [{\n" +
                "      \"vpc_id\": \"2af3cdce-7862-40cc-bb98-d234dbbd4dc3\"\n" +
                "    },{\n" +
                "      \"vpc_id\": \"2784e83f-f8ea-463e-81a6-b32e09f997d3\"\n" +
                "    }],\n" +
                "    \"app_baseline\": [{\n" +
                "      \"id\": \"b2b73f3b-0aa0-4000-9c8e-1449a0d3cf87\",\n" +
                "      \"app_id\": \"CS001\",\n" +
                "      \"name\": \"集中交易系统\",\n" +
                "      \"abbreviation\": \"集中交易\"\n" +
                "    },{\n" +
                "      \"id\": \"fd634834-59a1-416d-a124-f74fb99069d9\",\n" +
                "      \"app_id\": \"CS002\",\n" +
                "      \"name\": \"根网投资交易系统\",\n" +
                "      \"abbreviation\": \"根网交易\"\n" +
                "    }],\n" +
                "    \"bank_account\": \"60193480640935824035008\"\n" +
                "  }]\n" +
                "}";
    }

}
