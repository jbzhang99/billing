package com.ai.baas.batch.client.mainflow.cron;

import java.util.Calendar;

public class CreateCronString implements ICreateCronString{

	public String createNextDayCron() {
        Calendar c  = Calendar.getInstance();  
        String tomorrow = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);
        String thisMonth = String.valueOf(c.get(Calendar.MONTH)+1);
        String thisYear = String.valueOf(c.get(Calendar.YEAR)); 
        
        String cron = "* * * "+tomorrow+" "+thisMonth+" ? "+thisYear;
        
        return cron;
    }
}
