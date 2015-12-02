package com.zl.bgec.basicapi.commodity.dao.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCollectDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCollect;
import com.zl.bgec.basicapi.common.util.LogicUtil;

/**
 * 会员关注商品
 * 
 * @ClassName: MemberAddressDaoImpl
 * @Description: TODO
 * 
 */
@Repository
public class CommodityCollectDaoImpl extends HibernateBaseDao<CommodityCollect> implements ICommodityCollectDao {

	
	//1.1.21添加会员关注商品
	public CommodityCollect addCommodityCollect(CommodityCollect commodityCollect) throws Exception {
		
		   if(LogicUtil.isNull(commodityCollect)){
			   return null;
		   }
		   String id = (String)this.save(commodityCollect);
		   Date createTime = new Date(System.currentTimeMillis());
		   
		   commodityCollect.setId(id);
		   commodityCollect.setCreateTime(createTime);
		return commodityCollect;
	}

	
	//1.1.22删除会员关注商品
	public int deleteCommodityCollect(String memberId)throws Exception {
		   
		Map<String,Object> commodityMap = new HashMap<String,Object>();
		commodityMap.put("memberId", memberId);
		return this.delete(commodityMap);
	}

	
	//更新会员关注商品
	public CommodityCollect updaeCommodityCollect(CommodityCollect commodityCollect)throws Exception {
		
//		    this.update(commodityCollect);
//		    return MemberConstant.SUCCESS;
			return this.saveOrUpdate(commodityCollect);
	}
	
	
	
	
	//1.1.23查询会员关注商品接口(列表)
	public List<CommodityCollect> getCommodityByQuery(String commodityId) throws Exception{
		
		String hql = "from CommodityCollect where commodity_id=?";
		List<CommodityCollect> list = this.getListByHql(hql, commodityId);
		if(LogicUtil.isNotNull(list) && list.size()>0){
			return list;
		}
		return null;
	}
	
	
	
	
//-------------------------------------------------------------------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	//没用
	public List<CommodityCollect> getCommodityCollectList(CommodityCollect commodityCollect) throws Exception {
		   return this.getList("commodityNo", commodityCollect.getCommodityNo());
	}

	public List<CommodityCollect> getCommodityCollect(String memberNo,String commdityNo)throws Exception {	
		
		StringBuffer hql=new StringBuffer();
		hql.append("from CommodityCollect where 1=1 ");
		if(null!=memberNo&&!"".equals(memberNo)){
			hql.append(" and memberNo='"+memberNo+"'");
		}
		if(null!=commdityNo&&!"".equals(commdityNo)){
			hql.append(" and commodityNo='"+commdityNo+"'");		
		}
		return this.getListByHql(hql.toString());
	}
	
	public int getCommodityCollectTotal(CommodityCollect commodityCollect)
			throws Exception {
		if(null != commodityCollect && (null != commodityCollect.getCommodityNo() && !"".equals(commodityCollect.getCommodityNo()))){
			String sb = "select count(*) from tbl_commodity_collect where commodity_no="+commodityCollect.getCommodityNo();
			SQLQuery query=this.createSQLQuery(sb.toString());
			if(null!=query){	
				List<BigInteger> list=query.list();			
				if(null!=list.get(0)){
					BigInteger bt=list.get(0);
					return bt.intValue();
				}
			}
		}
		return 0;
	}
	
	public List<CommodityCollect> getCommodityCollectPage(CommodityCollect commodityCollect, int pageNo, int pageSize)
			throws Exception {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		int startRow = (pageNo - 1) * pageSize;
		//int endRow = pageNo * pageSize;
		String hql = "FROM CommodityCollect CC WHERE CC.memberId = :memberId";
		Map<String, Object> mapValues = new HashMap<String, Object>();
		mapValues.put("memberId", commodityCollect.getMemberNo());

		List<CommodityCollect> list = this.getListByHQL(hql, startRow, pageSize,
				mapValues);
		return list;
		 //  return this.pagedByHQL(hql, pageNo, pageSize, mapValues);
	}
}