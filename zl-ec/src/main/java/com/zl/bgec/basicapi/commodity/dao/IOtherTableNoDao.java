package com.zl.bgec.basicapi.commodity.dao;

import java.util.List;



import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.OtherTableNo;

public interface IOtherTableNoDao extends IHibernateBaseDao<OtherTableNo>{
	
	public OtherTableNo getOtherTableNo(String onType);

    public List<OtherTableNo> getOtherTableNoList();

    public void saveOtherTableNo(OtherTableNo otherTableNo);

    public int updateOtherTableNo(String onType, int seq);
}
