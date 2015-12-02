package com.zl.bgec.basicapi.commodity.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatNoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCatNo;
@Repository
public class CommodityCatNoDaoImpl extends HibernateBaseDao<CommodityCatNo> implements ICommodityCatNoDao{
	
	@Override
    public CommodityCatNo getCommoCategoryNo(String noType, String catPno)throws Exception {
        String sql = "select ccn.* from tbl_commo_category_no ccn where ccn.no_type=:noType and ccn.cat_pno=:catPno FOR UPDATE";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setParameter("noType", noType);
        query.setParameter("catPno", catPno);
        query.addEntity(CommodityCatNo.class);

        return (CommodityCatNo) query.uniqueResult();
    }


    @Override
    public int updateCommoCategoryNo(String noType, String catPno, int seq)throws Exception  {
        String sql = "update tbl_commo_category_no ccn set ccn.seq = :seq where ccn.no_type=:noType and ccn.cat_pno=:catPno";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setParameter("seq", seq);
        query.setParameter("noType", noType);
        query.setParameter("catPno", catPno);
        return query.executeUpdate();
    }
}
