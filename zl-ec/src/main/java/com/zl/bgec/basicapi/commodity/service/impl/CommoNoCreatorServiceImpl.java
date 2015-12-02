package com.zl.bgec.basicapi.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.zl.bgec.basicapi.commodity.dao.ICommodityCatNoDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityNoDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatNoDao;
import com.zl.bgec.basicapi.commodity.dao.IOtherTableNoDao;
import com.zl.bgec.basicapi.commodity.dao.IProductNoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCatNo;
import com.zl.bgec.basicapi.commodity.po.CommodityNo;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCatNo;
import com.zl.bgec.basicapi.commodity.po.OtherTableNo;
import com.zl.bgec.basicapi.commodity.po.ProductNo;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.LogicUtil;
@Service
public class CommoNoCreatorServiceImpl implements ICommoNoCreatorService{
	
	
	@Resource
    private IOtherTableNoDao otherTableNoDao;
	
	@Resource
    private ICommodityOperCatNoDao commodityOperCatNoDao;
	
	@Resource
    private ICommodityCatNoDao commodityCatNoDao;
	
	@Resource
	private ICommodityNoDao commodityNoDao;
	
	@Resource
	private IProductNoDao productNoDao;
	
	private Map<String, List<Integer>> commoNoSeqMap = new HashMap<String, List<Integer>>();
    private Map<String, List<Integer>> otherTableNoMap = new HashMap<String, List<Integer>>();
	
	private final static int COMMO_NO_STEP = 5;// 每次获取编号个数
    private final static int OTHER_TAB_NO_STEP = 5;// 每次获取编号个数

    private final static int COMMO_NO_SEQ_LENGTH = 9;// 9位序列数字
    private final static int PROD_NO_SEQ_LENGTH = 2;// 2位序列数字
    private final static int CAT_NO_SEQ_LENGTH = 2;// 2位序列数字
    private final static int OPRT_CAT_NO_SEQ_LENGTH = 2;// 2位序列数字
    private final static int OTHER_NO_SEQ_LENGTH = 8;// 8位序列数字

    private final static String BRAND_NO_TYPE = "brand_no_type";// 品牌编号
    private final static String TAG_NO_TYPE = "tag_no_type";// 标签编号
    private final static String GROUP_NO_TYPE = "group_no_type";// 属性组编号
    private final static String PROP_NO_TYPE = "prop_no_type";// 属性编号
    private final static String VAL_OPTION_NO_TYPE = "val_option_no_type";// 属性值选项编号
    private final static String COMMO_PIC_NO_TYPE = "commo_pic_no_type";// 商品图片编号
    private final static String PRICE_CHANGE_NO_TYPE = "price_change_no_type";// 调价单编号
    private final static String RECEIPT_TYPE_NO = "receipt_type_no";// 发票类型编号
    private final static String BASE_SHOP_NO = "base_shop_no";// 基础店铺编号
	
	
	@Override
	@Transactional
	public String createCatNo(String noType, String catPno) throws Exception{
		int seq = -1;
        synchronized (this) {
            seq = this.getCategoryNo(noType, catPno);
        }
        if (seq > 0) {
            if (CommodityConstant.TOP_LEVEL_PNO.equals(catPno)) {
                catPno = "";
            }
            return catPno + this.getStr(seq, CAT_NO_SEQ_LENGTH);
        }
        return null;
	}

	@Override
	@Transactional
	public String createOprtCatNo(String noType, String opertCatPno) throws Exception{
		int seq = -1;
        synchronized (this) {
            seq = this.getOperatingCategoryNo(noType, opertCatPno);
        }
        if (seq > 0) {
            if (CommodityConstant.TOP_LEVEL_PNO.equals(opertCatPno)) {
                opertCatPno = "";
            }
            return opertCatPno + this.getStr(seq, OPRT_CAT_NO_SEQ_LENGTH);
        }
        return null;
	}

	@Override
	@Transactional
	public String createCommoNo(String catNo)throws Exception {
		int seq = -1;
        synchronized (this.commoNoSeqMap) {
            seq = this.getCommodityNo(catNo);
        }
        if (seq > 0) {
            return catNo + this.getStr(seq, COMMO_NO_SEQ_LENGTH);
        }
        return null;
	}

	@Override
	@Transactional
	public String createProdNo(String commoNo)throws Exception {
		 int seq = -1;
	        synchronized (this) {
	            seq = this.getProductNo(commoNo);
	        }
	        if (seq > 0) {
	            return commoNo + this.getStr(seq, PROD_NO_SEQ_LENGTH);
	        }
	     return null;
	}
	
	private int getCommodityNo(String catNo) {
        // 启动始初始化
        if (LogicUtil.isNull(this.commoNoSeqMap.get(catNo))) {
            this.commoNoSeqMap.put(catNo, new ArrayList<Integer>());
        }

        // MAP中没有值则读取数据库记录
        if (this.commoNoSeqMap.get(catNo).size() == 0) {
        	CommodityNo commodityNo=commodityNoDao.get("catNo", catNo);
            if (LogicUtil.isNotNull(commodityNo)) {
                int seq = commodityNo.getSeq();
                int step = commodityNo.getStep();
                commodityNo.setSeq(seq+step);
                commodityNoDao.update(commodityNo);
                this.commoNoSeqMap.put(catNo, this.getList(this.commoNoSeqMap.get(catNo), seq, step));

            } else {
                // 数据库中不存在该分类的商品编号序列，则新增一个
                int seq = 1;
                int step = COMMO_NO_STEP;

                commodityNo = new CommodityNo();
                commodityNo.setCatNo(catNo);
                commodityNo.setSeq(seq + step);
                commodityNo.setStep(step);// 设置每次取值个数

                commodityNoDao.save(commodityNo);
                this.commoNoSeqMap.put(catNo, this.getList(this.commoNoSeqMap.get(catNo), seq, step));
            }
        }

        // MAP中有值则直接读取MAP记录
        int seq = -1;
        if (this.commoNoSeqMap.get(catNo).size() > 0) {
            seq = this.commoNoSeqMap.get(catNo).get(0);
            this.commoNoSeqMap.get(catNo).remove(0);
        }

        return seq;
    }

    private int getProductNo(String commoNo) {
        ProductNo noObj = productNoDao.get("commoNo",commoNo);
        // 获取该商品编号下的货品编号
        if (LogicUtil.isNull(noObj)) {
            int seq = 1;
            noObj = new ProductNo();
            noObj.setCommoNo(commoNo);
            noObj.setSeq(seq + 1);
            noObj.setStep(1);// 设置每次取值个数

            productNoDao.save(noObj);
            return seq;

        } else {
            int seq = noObj.getSeq();
            noObj.setSeq(seq+1);
            productNoDao.update(noObj);
            return seq;
        }
    }
	
	@Override
    @Transactional
    public String createBrandNo()throws Exception {
        int seq = this.getOtherTableNo(BRAND_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createTagNo()throws Exception {
        int seq = this.getOtherTableNo(TAG_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createGroupNo()throws Exception {
        int seq = this.getOtherTableNo(GROUP_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createPropNo() throws Exception{
        int seq = this.getOtherTableNo(PROP_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createValOptionNo() throws Exception{
        int seq = this.getOtherTableNo(VAL_OPTION_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createCommoPicNo() throws Exception{
        int seq = this.getOtherTableNo(COMMO_PIC_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }

    @Override
    @Transactional
    public String createPriceChangeNo() throws Exception{
        int seq = this.getOtherTableNo(PRICE_CHANGE_NO_TYPE);
        if (seq > -1) {
            return this.getStr(seq, OTHER_NO_SEQ_LENGTH);
        }
        return null;
    }
    
	
	private int getOtherTableNo(String noType)throws Exception {
        if (otherTableNoMap.get(noType)==null) {
            this.otherTableNoMap.put(noType, new ArrayList<Integer>());
        }

        if (this.otherTableNoMap.get(noType).size() == 0) {
            OtherTableNo otherTableNo = otherTableNoDao.getOtherTableNo(noType);
            if (null != otherTableNo) {

                int seq = otherTableNo.getSeq();
                int step = otherTableNo.getStep();

                this.otherTableNoDao.updateOtherTableNo(noType, seq + step);
                this.otherTableNoMap.put(noType, getList(otherTableNoMap.get(noType), seq, step));

            } else {
                int seq = 1;
                int step = OTHER_TAB_NO_STEP;

                otherTableNo = new OtherTableNo();
                otherTableNo.setNoType(noType);
                otherTableNo.setSeq(seq + step);
                otherTableNo.setStep(step);// 设置每次取值个数

                this.otherTableNoDao.saveOtherTableNo(otherTableNo);
                this.otherTableNoMap.put(noType, getList(otherTableNoMap.get(noType), seq, step));
            }
        }

        int seq = -1;
        if (this.otherTableNoMap.get(noType).size() > 0) {
            seq = this.otherTableNoMap.get(noType).get(0);
            this.otherTableNoMap.get(noType).remove(0);
        }

        return seq;
    }
	
	private String getStr(int seq, int seqLength) {
        String s = String.valueOf(seq);
        for (int i = s.length(); i < seqLength; i++) {
            s = "0" + s;
        }
        return s;
    }

    private List<Integer> getList(List<Integer> seqList, int seq, int step) {
        for (int i = 0; i < step; i++) {
            seqList.add(seq + i);
        }
        return seqList;
    }
	
    private int getOperatingCategoryNo(String noType, String oprtCatPno)throws Exception {
    	CommodityOperCatNo noObj = this.commodityOperCatNoDao.getOperatingCategoryNo(noType, oprtCatPno);
        // 数据库不存在则保存该父编号
        if (LogicUtil.isNull(noObj)) {
            int seq = 1;
            noObj = new CommodityOperCatNo();
            noObj.setNoType(noType);
            noObj.setOprtCatPno(oprtCatPno);
            noObj.setSeq(seq + 1);
            noObj.setStep(1);

            this.commodityOperCatNoDao.save(noObj);
            return seq;

        } else {
            // 获取该父编号下的子分类编号
            int seq = noObj.getSeq();
            this.commodityOperCatNoDao.updateOperatingCategoryNo(noType, oprtCatPno, seq + 1);
            return seq;
        }
    }
    
    private int getCategoryNo(String noType, String catPno) throws Exception {
        CommodityCatNo catNo = this.commodityCatNoDao.getCommoCategoryNo(noType, catPno);
        // 数据库不存在则保存该父编号
        if (LogicUtil.isNull(catNo)) {
            int seq = 1;
            catNo = new CommodityCatNo();
            catNo.setNoType(noType);
            catNo.setCatPno(catPno);
            catNo.setSeq(seq + 1);
            catNo.setStep(1);

            this.commodityCatNoDao.save(catNo);
            return seq;

        } else {
            // 获取该父编号下的子分类编号
            int seq = catNo.getSeq();
            this.commodityCatNoDao.updateCommoCategoryNo(noType, catPno, seq + 1);
            return seq;
        }
    }
}
