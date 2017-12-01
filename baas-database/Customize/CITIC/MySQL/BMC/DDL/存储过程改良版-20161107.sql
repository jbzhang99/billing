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
   
   declare count int;
   
   declare codestr VARCHAR(32);
   declare tempstr VARCHAR(32);
   
   DECLARE InstanceId VARCHAR(100);
   DECLARE FactorCode VARCHAR(32);
   DECLARE CunitPriceCode VARCHAR(32);
   DECLARE PriceCode VARCHAR(32);
   DECLARE DetailCode VARCHAR(32);
   
       /* 声明游标 */
      DECLARE rs CURSOR FOR SELECT INSTANCE_ID FROM bl_userinfo_zx a where product_id is null;
--       DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
      DECLARE factorCodeRs Cursor for select factor_code from cp_factor_info where factor_name = 'instance_id' and factor_value = InstanceId; 
--       DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
      set @max = (select count(*)  from bl_userinfo_zx where product_id is null limit 0,5);
--       select @max;
      set @num = 1;
--       select @num;
      /* 打开游标 */
      OPEN rs;
      
      /* 遍历数据表 */     
--      FETCH rs INTO InstanceId;
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
--                 select FactorCode;
                set @CunitPriceCode = (select cunit_price_code from cp_cunitprice_info where factor_code= FactorCode);
                set @DetailCode = (select detail_code from cp_step_info where factor_code = FactorCode  limit 1);
--                 select @DetailCode, @CunitPriceCode;
                IF  ISNULL(@CunitPriceCode) THEN
--                   select '阶梯产品', @DetailCode;
                  set @PriceCode = (select price_code from cp_price_detail where detail_code= @DetailCode) ;
                  set @tempStr = (select product_id from bl_userinfo_zx where instance_id =InstanceId);
                  set @count = (select count(*) from bl_subs_comm where product_id=@PriceCode);
                  if(@tempStr is not null) then
                     
                     if(@count > 0) then
                       set @codeStr = null;
                       set @codeStr = CONCAT(@tempStr,';',@PriceCode);
                     end if;
                  else
                     set @codeStr = @PriceCode;
                  end if;
--                   select 'codeStr',@codeStr,'阶梯产品',@DetailCode;
                  UPDATE bl_userinfo_zx  SET  product_id = @codeStr where instance_id= InstanceId;
                END IF;
                
                IF ISNULL(@DetailCode) THEN
--                   select '复合单价产品' , @CunitPriceCode;
                  set @PriceCode = (select price_code from cp_price_detail where detail_code=@CunitPriceCode);
                  set @tempStr = (select product_id from bl_userinfo_zx where instance_id =InstanceId);
                  set @count = (select count(*) from bl_subs_comm where product_id=@PriceCode);
--                   select (@tempStr is not null);
                  if(@tempStr is not null) then
                     
                     if(@count > 0) then
                       set @codeStr = null;
                       set @codeStr = CONCAT(@tempStr,';',@PriceCode);
                     end if;
                  else
                     set @codeStr = @PriceCode;
                  end if;
--                   select 'codeStr',@codeStr,'复合单价产品',@CunitPriceCode;
                  
                  UPDATE bl_userinfo_zx SET product_id = @codeStr where instance_id= InstanceId;
                END IF;
                set @numfactor = @numfactor + 1;
              end while;
              CLOSE factorCodeRs;
              set @num = @num + 1;
              select @num,@max,(@num >= @max);
      UNTIL (@num > @max) END REPEAT;
      CLOSE rs;
 END;
 //
 DELIMITER;
 
