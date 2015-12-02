package com.zl.bgec.basicapi.commodity.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IOtherTableNoDao;
import com.zl.bgec.basicapi.commodity.po.OtherTableNo;

@Repository
public class OtherTableNoDaoImpl extends HibernateBaseDao<OtherTableNo>
		implements IOtherTableNoDao {
	@Override
	public OtherTableNo getOtherTableNo(String noType) {
		String sql = "select otn.* from tbl_other_table_no otn where otn.no_type=:noType FOR UPDATE";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter("noType", noType);
		query.addEntity(OtherTableNo.class);

		return (OtherTableNo) query.uniqueResult();
	}

	@Override
	public List<OtherTableNo> getOtherTableNoList() {
		return this.getAll();
	}

	@Override
	public void saveOtherTableNo(OtherTableNo otherTableNo) {
		this.save(otherTableNo);
	}

	@Override
	public int updateOtherTableNo(String noType, int seq) {
		String sql = "update tbl_other_table_no otn set otn.seq = :seq where otn.no_type=:noType";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter("seq", seq);
		query.setParameter("noType", noType);
		return query.executeUpdate();
	}
}
