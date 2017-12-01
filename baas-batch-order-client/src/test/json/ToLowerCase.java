package json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.batch.client.util.JSONtoLowerTools;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ToLowerCase {

    
    @Test
    public void toLowerCase(){
        String json = "{\"orders\":[{\"ID\":\"0C743573-3F76-479b-9b90-9c5fcc499fd6\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"5762107c6ae6ca04e14595b8\",\"parameters\":{\"engine\":\"MySQL\",\"engineVersion\":\"5.6\",\"dBInstanceClass\":\"rds.mysql.s2.large\",\"dBInstanceStorage\":\"10\",\"dBInstanceNetType\":\"Internet\",\"securityIPList\":\"0.0.0.0/0\",\"regionId\":\"cn-beijing\",\"zoneId\":\"cn-beijing-c\",\"payType\":\"Postpaid\",\"instanceNetworkType\":\"VPC\",\"vpcId\":\"vpc-jrwfspj5s\",\"vSwitchId\":\"vsw-9dlv1ldos\"},\"service_name\":\"云数据库RDS\",\"instances\":[{\"Instance_id\":\"f1549750-9ed9-4ab5-8b51-b53bf66c0732\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"5762107c6ae6ca04e14595b8\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"rm-2jd103cr86gb94a6x\",\"use_id\":\"2\"}],\"list_id\":\"a643f557-af92-4677-8291-dc03acbb9a5d\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 34: 52\"},{\"id\":\"35a57764-caea-49ac-9553-85a1d4831e0e\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"57721e052fa45f06e1c013da\",\"parameters\":{\"ServiceCode\":\"ons\",\"StatusKey1\":\"enabled\",\"StatusValue1\":\"true\"},\"service_name\":\"消息队列\",\"instances\":[{\"Instance_id\":\"af9f8408-fa7a-4bf3-84ff-b224afc9d74a\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"57721e052fa45f06e1c013da\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"1596769687493741\",\"use_id\":\"2\"}],\"list_id\":\"e9c96fb2-26c6-434c-8808-f271df6cb45e\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 35: 34\"}]}";
        System.out.println(json);
        JSONObject object = JSONObject.fromObject(json);
        
        System.out.println(object.toString());
    
        JSONObject afterTrans = JSONtoLowerTools.transObject(object);
        
        System.err.println(afterTrans.toString());
    }
    
}
