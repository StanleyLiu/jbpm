package com.zl.bgec.basicapi.order.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderUtils {
	public static String parseStrNull(String s){
		return s==null?"":s;
	}
	
	/**
	 * 生成订单NO
	 * @Title: genOrderNo 
	 * @Description: TODO
	 * @return  
	 * @author: yong
	 * @date: 2013-1-4下午04:40:28
	 */
	public static String genOrderNo() {
		return System.currentTimeMillis() + RandomUtils.getRandomNumberStr(6);
	}
	/**
	 * 订单条目NO
	 * @Title: genOrderItemNo 
	 * @Description: TODO
	 * @return  
	 * @author: yong
	 * @date: 2013-4-1上午09:52:34
	 */
	public static String genOrderItemNo() {
		return System.currentTimeMillis() + RandomUtils.getRandomNumberStr(6);
	}
	/**
	 * 支付状态
	 * @return 未支付 7; 部分支付 8; 已支付 9; 退款申请中 10; 部分退款 11; 全额退款 12; 退款失败 13; add
	 	1	待支付
		2	已支付
		3	部分支付
		4	已退款
		5	部分退款
	 * @Title: getPayStatus 
	 * @Description: TODO
	 * @return  
	 * @author: Stanley
	 * @date: 2013-4-19下午3:12:10
	 */
	public static Map<String, String> getPayStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("1", "待支付");
		reMap.put("2", "已支付");
		reMap.put("3", "部分支付");
		reMap.put("4", "已退款");
		reMap.put("5", "部分退款");
		reMap = sortMapByKey(reMap);
		return reMap;
	}
	/**
	 *  1	在线支付
	 *	2	货到付款
	 *	3	到店支付
	 *	4	电子账户
	 *	5	混合支付
	 *  6	代金券支付
	 * @return
	 */
	public static Map<String, String> getPayType() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("1", "在线支付");
		 reMap.put("2", "货到付款");
		reMap.put("3", "到店支付");
		reMap.put("4", "电子账户");
		reMap.put("5", "混合支付");
		 reMap.put("6", "代金券支付");
		reMap = sortMapByKey(reMap);
		return reMap;
	}

	/**
	 * 基本状态
	 * 
	 * @return 未处理(默认值) 1; 已确认 2; 已挂起 (订单表加入挂起类型 类型有缺货挂起、预售挂起) 3; 已完成 4; 已作废 5;
	 *        1	待审核
 	 * 		2	待复核
 	 * 		3	已取消（已作废）
	 * 		4	已审核
	 * 		5	已同步
	 * 		6	待发货
	 * 		7	已发货
	 * 		8	部分发货
	 * 		9	已完成
	 * 		10	锁定
	 * 		11	售后中
	*/
	public static Map<String, String> getBasicStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("1", "待审核");
		reMap.put("2", "待复核");
		reMap.put("3", "已取消（已作废）");
		reMap.put("4", "已审核");
		reMap.put("5", "已同步");
		reMap.put("6", "待发货");
		reMap.put("7", "已发货");
		reMap.put("8", "部分发货");
		reMap.put("9", "已完成");
		reMap.put("10", "锁定");
		reMap.put("11", "售后中");
		reMap = sortMapByKey(reMap);
		return reMap;
	}

	/**
	 * 配送状态 准备 14; 部分发货 15; 已发货 16; 已收货 17; 拒收 18; 部分退货 19; 已取消发货 20; 已终止发货 21;
	 * 拣货中 22;
	 * @Title: getDeliveryStatus 
	 * @Description: TODO
	 * @return  
	 * @author: Stanley
	 * @date: 2013-4-19下午3:11:47
	 */
	public static Map<String, String> getDeliveryStatus() {
		Map<String, String> reMap = new HashMap<String, String>();
		reMap.put("14", "准备");
		// reMap.put("15", "部分发货");
		reMap.put("16", "已发货");
		reMap.put("17", "已收货");
		reMap.put("18", "拒收");
		// reMap.put("19", "部分退货");
		reMap.put("20", "已取消发货");
		reMap.put("21", "已终止发货");
		reMap.put("22", "拣货中");
		reMap = sortMapByKey(reMap);
		return reMap;
	}
	
	/**
	 * 支持key 是数字类型
	 * 
	 * @param oriMap
	 * @return add sxw
	 */
	private static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String key1, String key2) {
						int intKey1 = 0, intKey2 = 0;
						try {
							intKey1 = getInt(key1);
							intKey2 = getInt(key2);
						} catch (Exception e) {
							intKey1 = 0;
							intKey2 = 0;
						}
						return intKey1 - intKey2;
					}
				});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}
	/**
	 * 
	 * @Title: getInt 
	 * @Description: TODO
	 * @param str
	 * @return  
	 * @author: Stanley
	 * @date: 2013-4-19下午3:16:04
	 */
	private static int getInt(String str) {
		int i = 0;
		try {
			Pattern p = Pattern.compile("^\\d+");
			Matcher m = p.matcher(str);
			if (m.find()) {
				i = Integer.valueOf(m.group());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * @Title: readExcelContent 
	 * @Description: TODO
	 * @param is
	 * @return  
	 * @author: Stanley
	 * @throws IOException 
	 * @date: 2013-4-24上午10:09:47
	 */
    public static List< String> readExcelContent(InputStream is) throws IOException {
        List< String> content = new ArrayList<String>();
//         POIFSFileSystem fs=new POIFSFileSystem(is);
//         HSSFWorkbook wb =new HSSFWorkbook(fs);
//         HSSFSheet sheet= wb.getSheetAt(0);
//        // 得到总行数
//        int rowNum = sheet.getLastRowNum();
//        HSSFRow row = sheet.getRow(0);
//        // 正文内容应该从第二行开始,第一行为表头的标题
//        for (int i = 1; i <= rowNum; i++) {
//            row = sheet.getRow(i);
//            content.add(row.getCell(1).getRichStringCellValue().getString());
//        }
        return content;
    }
	public static void main(String[] args) {
		try {
			InputStream is=new FileInputStream(
			new File("E:\\workspace2013\\ECOS\\EC-BizMgt\\src\\main\\webapp\\20130423112000855.xls"));
			List<String> list=readExcelContent(is);
			for (String string : list) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
