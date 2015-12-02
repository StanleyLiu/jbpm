package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.commodity.po.ProductChangePrice;
import com.zl.bgec.basicapi.commodity.po.ProductStock;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.commodity.vo.ProductChangePriceVo;
import com.zl.bgec.basicapi.commodity.vo.ProductPropVo;
import com.zl.bgec.basicapi.commodity.vo.ProductStockVo;
import com.zl.bgec.basicapi.commodity.vo.ProductVo;

public interface IProductService {
	/**
	 * 保存货品
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public String saveProduct(ProductVo productVo)throws Exception;
	/**
	 * 保存货品规格属性
	 * @param productPropVo
	 * @return
	 * @throws Exception
	 */
	public void saveProductProp(ProductPropVo productPropVo)throws Exception;
	/**
	 * 批量保存货品规格属性
	 * @param productPropVo
	 * @return
	 * @throws Exception
	 */
	public void saveBatchProductProp(List<ProductPropVo> productPropVos)throws Exception;
	/**
	 * 修改货品
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public void updateProduct(ProductVo productVo)throws Exception;
	/**
	 * 删除货品
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public void deleteProduct(ProductVo productVo)throws Exception;
	/**
	 * 查询货品
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public List<Product> getProductList(ProductVo productVo)throws Exception;
	/**
	 * 查询货品（分页）
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Product> pagedProductList(ProductVo productVo)throws Exception;
	
	/**
	 * 新建货品调价
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public String createProductChangePrice(ProductChangePriceVo productChangePriceVo)throws Exception;
	
	/**
	 * 货品调价审核
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public void conserProductChangePrice(ProductChangePriceVo productChangePriceVo)throws Exception;
	
	/**
	 * 查询货品调价单
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductChangePrice> getProductChangePriceList(ProductChangePriceVo ProductChangePriceVo)throws Exception;
	/**
	 * 查询货品调价单（分页）
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public PageFinder<ProductChangePrice> pagedProductChangePriceList(ProductChangePriceVo ProductChangePriceVo)throws Exception;
	/**
	 * 操作库存
	 * @param stockVo
	 * @throws Exception
	 */
	public void handleStock(ProductStockVo stockVo)throws Exception;
	/**
	 * 初始库存
	 * @param stockVo
	 * @throws Exception
	 */
	public void initStock(ProductStockVo stockVo)throws Exception;
	/**
	 * 查询库存
	 * @param stockVo
	 * @return
	 * @throws Exception
	 */
	public ProductStock getStock(ProductStockVo stockVo)throws Exception;
	/**
	 * 根据货品编号查询商品信息
	 * @param stockVo
	 * @return
	 * @throws Exception
	 */
	public List<Commodity> queryCommodity(ProductVo productVo)throws Exception;
	/**
	 *  批量操作库存
	 * @param body
	 */
	public void batchHandleStock(List<ProductStockVo> stockVos)throws Exception;
	/**
	 * 查询商品详情
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
	public CommodityVo getCommodityDetail(String productNo,String memberNo)throws Exception;
	/**
	 * 分页查询商品评价
	 * @param productNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageFinder<CommodityComment> pageCommdityCommentList(String productNo,
			int pageNo, int pageSize) throws Exception;
	public CommodityVo getCommodityDetailNoUserId(String prodNo, String memberNo) throws Exception;
	public ProductVo checkProdStock(String prodNo) throws Exception;
}
