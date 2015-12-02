/**
 *
 * Project:shop
 * Date:2014-12-8
 * Author: Allen.Z
 * Desc: 
 *
 */
package com.zl.bgec.basicapi.common;

import java.util.Random;

public class GeneratorKey {
	
	
	public String createKey(){
		Random r =new Random();
		long l = System.currentTimeMillis();
		int i = r.nextInt(10000000);
		return l+""+i;
	}

	
}
