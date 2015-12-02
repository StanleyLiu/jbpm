package com.zl.bgec.basicapi.common.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CreatorNoUtil {

    private static AtomicInteger counter = new AtomicInteger(0);

    /**
     * 生成编号
     */
    public synchronized static String getCode() {
        if (counter.get() > 99998) {
            counter.set(0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmSS");
		String date=sdf.format(new Date());
        long returnValue = counter.incrementAndGet();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumIntegerDigits(5);
        nf.setMaximumIntegerDigits(5);
        String tail = nf.format(returnValue).replace(",", "");
        
        return date+tail;
    }
    public static void main(String[] args) {
		for(int i=0;i<100;i++){
			String a = getCode();
			System.out.println(a);
		}
	}
}