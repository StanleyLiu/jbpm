package com.zl.bgec.basicapi.commodity.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatNoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCatNo;
@Repository
public class CommodityOperCatNoDaoImpl extends HibernateBaseDao<CommodityOperCatNo> implements ICommodityOperCatNoDao{

    @Override
    public CommodityOperCatNo getOperatingCategoryNo(String noType, String oprtCatPno) {
        String sql = "select ocn.* from tbl_operating_category_no ocn where ocn.no_type=:noType and ocn.oprt_cat_pno=:oprtCatPno FOR UPDATE";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setParameter("noType", noType);
        query.setParameter("oprtCatPno", oprtCatPno);
        query.addEntity(CommodityOperCatNo.class);

        return (CommodityOperCatNo) query.uniqueResult();
    }


    @Override
    public int updateOperatingCategoryNo(String noType, String oprtCatPno, int seq) {
        String sql = "update tbl_operating_category_no ocn set ocn.seq = :seq where ocn.no_type=:noType and ocn.oprt_cat_pno=:oprtCatPno";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.setParameter("seq", seq);
        query.setParameter("noType", noType);
        query.setParameter("oprtCatPno", oprtCatPno);

        return query.executeUpdate();
    }

}
