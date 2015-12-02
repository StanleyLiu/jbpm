package com.zl.bgec.basicapi.commodity.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityBrandDao;
import com.zl.bgec.basicapi.commodity.po.CommodityBrand;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Repository
public class CommodityBrandDaoImpl   extends HibernateBaseDao<CommodityBrand> implements ICommodityBrandDao{

	@Override
	public void updateBrandDisplayStatus(List<String> brandNos, byte isDisplay)
			throws Exception {
		StringBuffer sl = new StringBuffer();
	    sl.append("update CommodityBrand b set b.isDisplay=" + isDisplay + " where b.brandNo in ");
	    if (LogicUtil.isNotNullAndEmpty(brandNos)) {
	        int i = 0;
	        for (String brandNo : brandNos) {
	            if (i == 0) {
	                sl.append("(");
	            }
	            sl.append(brandNo);
	            if (i < brandNos.size() - 1) {
	                sl.append(",");
	            }
	            if (i == brandNos.size() - 1) {
	                sl.append(")");
	            }
	            i++;
	        }
	    }
	    this.getSession().createQuery(sl.toString()).executeUpdate();
	}
	
	
}
