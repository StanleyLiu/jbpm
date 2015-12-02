package com.zl.bgec.basicapi.commodity.dao;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCollect;


/**
 * 
 * @ClassName: CommodityCollectDao 
 * @Description: 商品收藏
 */
public interface ICommodityCollectDao extends IHibernateBaseDao<CommodityCollect> {

	/**
	 * 
	 * @Title: addCommodityCollect 
	 * @Description: 1.1.21添加会员关注商品
	 * @param commodityCollect
	 * @return
	 * @throws Exception  
	 */
	public CommodityCollect addCommodityCollect(CommodityCollect commodityCollect)throws Exception;
    
	/**
	 * 
	 * @Title: updaeCommodityCollect 
	 * @Description: 更新商品收藏
	 * @param commodityCollect
	 * @return
	 * @throws Exception  
	 */
	public CommodityCollect updaeCommodityCollect(CommodityCollect commodityCollect)throws Exception;
	/**
	 * 
	 * @Title: deleteCommodityCollect 
	 * @Description: 1.1.22删除会员关注商品
	 * @param commodityCollect
	 * @return
	 * @throws Exception  
	 */
	public int deleteCommodityCollect(String memberId)throws Exception;

	/**
	 * 1.1.23查询会员关注商品接口(列表)
	 * @param commodityId
	 * @return
	 * @throws Exception
	 */
	public List<CommodityCollect> getCommodityByQuery(String commodityId) throws Exception;
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------
	
	
	
	//没用
	/**
	 * 
	 * @Title: getCommodityCollectList 
	 * @Description:查询商品收藏列表
	 * @param commodityCollect
	 * @return
	 * @throws Exception  
	 */
	public List<CommodityCollect> getCommodityCollectList(CommodityCollect commodityCollect)throws Exception;
	
	/**
	 * 
	 * @Title: getCommodityCollectPage 
	 * @Description: 分页查询收藏商品
	 * @param commodityCollect
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception  
	 */
	public List<CommodityCollect> getCommodityCollectPage(CommodityCollect commodityCollect,int pageNo, int pageSize)throws Exception;
	
	/**
	 * 
	 * @Title: getCommodityCollectPage 
	 * @Description: 分页查询收藏商品
	 * @param commodityCollect
	 * @return
	 * @throws Exception  
	 */
	public int getCommodityCollectTotal(CommodityCollect commodityCollect)throws Exception;
    
	/**
	 * @Description: 通过会员编号，商品编号，查询数据
	 * @param memberNo
	 * @param commdityNo
	 * @return  
	 */
	public List<CommodityCollect> getCommodityCollect(String memberNo,String commdityNo)throws Exception;
	
}
