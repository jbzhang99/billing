//package api.test;
//
//import com.ai.baas.batch.client.api.usage.interfaces.IUsageMessageProcessMockSV;
//import com.ai.baas.batch.client.api.usage.params.UsageRecord;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "classpath:context/core-context.xml" })
//public class UsageMessageProcessSVTest {
//
//    @Resource
//    private IUsageMessageProcessMockSV usageMessageProcessMockSV;
//
//    @Test
//    public void test(){
//        UsageRecord record = new UsageRecord();
//        record.setServiceId("57721cb62fa45f06e1c013d6");
//        record.setUsageJson("{\n" +
//                "    \"requestId\": \"58c8ae167387f300098de862\",\n" +
//                "    \"state\": \"succeeded\",\n" +
//                "    \"usage_and_expenses_data\": [\n" +
//                "        {\n" +
//                "            \"RegionId\": \"cn-beijing\",\n" +
//                "            \"bandwidth\": \"0\",\n" +
//                "            \"cloud_data_disk_size\": \"0\",\n" +
//                "            \"cpus\": \"4\",\n" +
//                "            \"data_disk_size\": \"0\",\n" +
//                "            \"disk\": \"0\",\n" +
//                "            \"end_time\": \"2017-02-01T01:00:00Z\",\n" +
//                "            \"image_os\": \"Ubuntu\",\n" +
//                "            \"image_size\": \"42949672960\",\n" +
//                "            \"instance_id\": \"6673883a-3b5b-4c56-811b-4eeeb9eca79b\",\n" +
//                "            \"instance_type_name\": \"ecs.s3.large\",\n" +
//                "            \"io_optimized\": \"true\",\n" +
//                "            \"memory\": \"8589934592\",\n" +
//                "            \"network_in\": \"0\",\n" +
//                "            \"network_out\": \"0\",\n" +
//                "            \"os_disk_size\": \"107374182400\",\n" +
//                "            \"os_disk_type\": \"cloud_efficiency\",\n" +
//                "            \"provider_id\": \"1991067246874857\",\n" +
//                "            \"region\": \"cn-beijing-btc-a01\",\n" +
//                "            \"ssd_data_disk_size\": \"0\",\n" +
//                "            \"start_time\": \"2017-02-01T00:00:00Z\",\n" +
//                "            \"supplier_instance_id\": \"i-250um9kwm\",\n" +
//                "            \"tmp_data_disk_size\": \"0\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"RegionId\": \"cn-beijing\",\n" +
//                "            \"bandwidth\": \"0\",\n" +
//                "            \"cloud_data_disk_size\": \"0\",\n" +
//                "            \"cpus\": \"4\",\n" +
//                "            \"data_disk_size\": \"0\",\n" +
//                "            \"disk\": \"0\",\n" +
//                "            \"end_time\": \"2017-02-01T03:00:00Z\",\n" +
//                "            \"image_os\": \"Ubuntu\",\n" +
//                "            \"image_size\": \"42949672960\",\n" +
//                "            \"instance_id\": \"6673883a-3b5b-4c56-811b-4eeeb9eca79b\",\n" +
//                "            \"instance_type_name\": \"ecs.s3.large\",\n" +
//                "            \"io_optimized\": \"true\",\n" +
//                "            \"memory\": \"8589934592\",\n" +
//                "            \"network_in\": \"0\",\n" +
//                "            \"network_out\": \"0\",\n" +
//                "            \"os_disk_size\": \"107374182400\",\n" +
//                "            \"os_disk_type\": \"cloud_efficiency\",\n" +
//                "            \"provider_id\": \"1991067246874857\",\n" +
//                "            \"region\": \"cn-beijing-btc-a01\",\n" +
//                "            \"ssd_data_disk_size\": \"0\",\n" +
//                "            \"start_time\": \"2017-02-01T02:00:00Z\",\n" +
//                "            \"supplier_instance_id\": \"i-250um9kwm\",\n" +
//                "            \"tmp_data_disk_size\": \"0\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"RegionId\": \"cn-beijing\",\n" +
//                "            \"bandwidth\": \"0\",\n" +
//                "            \"cloud_data_disk_size\": \"0\",\n" +
//                "            \"cpus\": \"4\",\n" +
//                "            \"data_disk_size\": \"0\",\n" +
//                "            \"disk\": \"0\",\n" +
//                "            \"end_time\": \"2017-02-01T04:00:00Z\",\n" +
//                "            \"image_os\": \"Ubuntu\",\n" +
//                "            \"image_size\": \"42949672960\",\n" +
//                "            \"instance_id\": \"6673883a-3b5b-4c56-811b-4eeeb9eca79b\",\n" +
//                "            \"instance_type_name\": \"ecs.s3.large\",\n" +
//                "            \"io_optimized\": \"true\",\n" +
//                "            \"memory\": \"8589934592\",\n" +
//                "            \"network_in\": \"0\",\n" +
//                "            \"network_out\": \"0\",\n" +
//                "            \"os_disk_size\": \"107374182400\",\n" +
//                "            \"os_disk_type\": \"cloud_efficiency\",\n" +
//                "            \"provider_id\": \"1991067246874857\",\n" +
//                "            \"region\": \"cn-beijing-btc-a01\",\n" +
//                "            \"ssd_data_disk_size\": \"0\",\n" +
//                "            \"start_time\": \"2017-02-01T03:00:00Z\",\n" +
//                "            \"supplier_instance_id\": \"i-250um9kwm\",\n" +
//                "            \"tmp_data_disk_size\": \"0\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}");
//        usageMessageProcessMockSV.process(record);
//    }
//}
