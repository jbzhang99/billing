DROP PROCEDURE IF EXISTS AAAA;

DELIMITER//
CREATE PROCEDURE AAAA()
BEGIN
      
   DECLARE Done INT DEFAULT 0;
   DECLARE FACT INT DEFAULT 0;
   declare num int;
   declare max int;
   declare numfactor int;
   declare maxfactor int;
   
   DECLARE InstanceId VARCHAR(100);
   DECLARE FactorCode VARCHAR(32);
   DECLARE CunitPriceCode VARCHAR(32);
   DECLARE PriceCode VARCHAR(32);
   DECLARE DetailCode VARCHAR(32);
   
       /* 声明游标 */
      DECLARE rs CURSOR FOR SELECT INSTANCE_ID FROM bl_userinfo_zx a where product_id is null limit 0,5;
--       DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
      DECLARE factorCodeRs Cursor for select factor_code from cp_factor_info where factor_name = 'instance_id' and factor_value = InstanceId; 
--       DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
      set @max = (select count(*)  from bl_userinfo_zx where product_id is null limit 0,5);
      select @max;
      set @num = 0;
--       select @num;
      /* 打开游标 */
      OPEN rs;
      
      /* 遍历数据表 */     
      FETCH rs INTO InstanceId;
--       while (@num < @max) do   
      REPEAT  
              FETCH rs INTO InstanceId; 
--               select InstanceId;
              set @maxfactor = (select count(*) from cp_factor_info where factor_name = 'instance_id' and factor_value = InstanceId); 
--               select @maxfactor;
              set @numfactor = 0;
              OPEN factorCodeRs;
              while (@numfactor < @maxfactor) DO
--                 select @numfactor;
                FETCH factorCodeRs INTO FactorCode;
                select FactorCode;
                set @CunitPriceCode = (select cunit_price_code from cp_cunitprice_info where factor_code= FactorCode);
                set @DetailCode = (select detail_code from cp_step_info where factor_code = FactorCode  limit 1);
                select @DetailCode, @CunitPriceCode;
                IF  ISNULL(@CunitPriceCode) THEN
--                   select '阶梯产品', @DetailCode;
                  set @PriceCode = (select price_code from cp_price_detail where detail_code= @DetailCode) ;
                  UPDATE bl_userinfo_zx  SET  product_id = @PriceCode where instance_id= InstanceId;
                END IF;
                
                IF ISNULL(@DetailCode) THEN
--                   select '复合单价产品' , @CunitPriceCode;
                  set @PriceCode = (select price_code from cp_price_detail where detail_code=@CunitPriceCode);
                  UPDATE bl_userinfo_zx SET product_id = @PriceCode where instance_id= InstanceId;
                END IF;
                set @numfactor = @numfactor + 1;
              end while;
              CLOSE factorCodeRs;
              set @num = @num + 1;
              select @num;
      UNTIL (@num >= 4) END REPEAT;
      CLOSE rs;
 END;
 //
 DELIMITER;
 
