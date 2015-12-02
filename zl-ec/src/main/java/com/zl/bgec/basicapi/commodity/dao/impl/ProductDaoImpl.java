package com.zl.bgec.basicapi.commodity.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.po.Product;

@Repository
public class ProductDaoImpl extends HibernateBaseDao<Product> implements IProductDao{

	@Override
	public String getPrice(String commoNo) throws Exception {
		String sql = "select IF(count(tp.price)>1,CONCAT(CONCAT(MIN(tp.price),'~'),MAX(tp.price)),tp.price) as price from tbl_product tp where commo_no=:commoNo";
		Query query = this.createSQLQuery(sql);
		query.setParameter("commoNo", commoNo);
		return String.valueOf( query.list().get(0));
	}

}
