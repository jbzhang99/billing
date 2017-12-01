package test;



import org.junit.Before;
import org.junit.Test;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.constants.SDKConstants;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;

public class PackageModeCCSTest {

    private IConfigClient client;

    @Before
    public void initData() {
        this.client = CCSClientFactory.getDefaultConfigClient();
    }

    @Test
    public void addMdsConfig() throws ConfigException {
        // 
        String mds001 = "MDS024";
        // 空间
        String mdssnsConfig = "{\"bmc_service_out_queue\":\"" + mds001
               + "\"}";

        // MDS空间配置
        if (!client.exists(SDKConstants.PAAS_MDSNS_MDS_MAPPED_PATH))
            client.add(SDKConstants.PAAS_MDSNS_MDS_MAPPED_PATH,
                    mdssnsConfig);
        else {
            client.modify(SDKConstants.PAAS_MDSNS_MDS_MAPPED_PATH,
                    mdssnsConfig);
        }
    }
}