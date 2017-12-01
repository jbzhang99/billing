/* 此表数据较多，固按业务拆分成多个文件

定价产品ID序列规则：
定价产品id编码规则： 序列 + 地区（两位）+ 定价产品类型（两位）


产品定价类型：
ECS-INSTANCE 01
ECS-SYSTEM-DISK 02
ECS-DATA-DISK 03
ECS-BANDWITH 04
RDS 05
KVS 06
smartcloud 07
20e7fd16-e5b0-46ec-bc8a-5a4a8232b313(友人才) 08
a732806f-46c5-4386-9877-41c5687a5fbc(友云彩) 09
slb-instance 10
slb-bandwith 11
5836a0462fbaaf0aee0db7a2(disk) 12
58465454b992230c4edfe723(waf) 13
58255cdfa71e5008f7e2a14a(高速通道） 14
43a3921d-7479-43d0-a970-031699dcf9b6(创宇盾) 15
a732806f-46c5-4386-9877-41c5687a5fbd(友报账) 16
97acb1b1-fbb3-4b4f-9c23-95acba084ac6(正邦) 17
ce4f19ab-d83f-4164-9974-9487b97cddcd(cmstop) 18

地区：
cn-beijing      华北2    01                               
cn-hangzhou     华东1   02                                 
cn-qingdao      华北1    04                     
cn-shanghai     华东2   05                   
cn-shenzhen     华南1   06                 
cn-hongkong     香港    07           
us-west-1       美西1     08     
ap-southeast-1  新加坡  09         
us-east-1       美东1 10

1、负载均衡  5785e232b9aa1e3769039c19（服务ID）
2、云磁盘    5836a0462fbaaf0aee0db7a2（服务ID）
3、高速通道  58255cdfa71e5008f7e2a14a（服务ID）
4、专有网络VPC 58184aafcbac2f0a712c735d（服务ID）

1、负载均衡  {"cost_center":"8270d509-fc7a-49a0-b74f-2653073595df=中信云测试=100","prices":"","RegionId":"cn-shenzhen","AddressType":"intranet","InternetChargeType":"paybytraffic","property":"1=开发","app_base_line":"T994=中信云测试3","instance_num":"1"}
2、云磁盘    {"cost_center":"8270d509-fc7a-49a0-b74f-2653073595df=中信云测试=100","prices":"","RegionId":"cn-shanghai","ZoneId":"cn-shanghai-a","DiskCategory":"cloud_ssd","Size":"25","property":"1=开发","app_base_line":"T994=中信云测试3","instance_num":"1"}
3、高速通道 {"cost_center":"8270d509-fc7a-49a0-b74f-2653073595df=中信云测试=100","prices":"","vpcOwnerId":"111111","fromVpcRegionId":"1111111111111","toVpcRegionId":"1111111111","fromVpcRouterId":"11111111111","toVpcRouterId":"1111111111","spec":"111","description":"1111111","property":"1=开发","app_base_line":"T994=中信云测试3","instance_num":"1"}
4、专有网络VPC {"cost_center":"8270d509-fc7a-49a0-b74f-2653073595df=中信云测试=100","prices":"","RegionId":"cn-hangzhou","CidrBlock":"100","property":"1=开发","app_base_line":"T994=中信云测试3","instance_num":"1"}


*/