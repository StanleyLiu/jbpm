package com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.StringType;
import org.springframework.util.Assert;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ReflectionUtils;

public class HibernateBaseDao<T> implements IHibernateBaseDao<T> {

    private final Class<T> entityClass;
    @Resource
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public HibernateBaseDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(this.getClass(), 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(Serializable id) {
        Assert.notNull(id, "id is required");
        return (T) this.getSession().load(this.entityClass, id);
    }

    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Serializable id) {
        Assert.notNull(id, "id is required");
        return (T) this.getSession().get(this.entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> get(Serializable[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + this.entityClass.getName() + " as model where model.id in(:ids)";
        return this.getSession().createQuery(hql).setParameterList("ids", ids).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String propertyName, Object value) {
//        Assert.hasText(propertyName, "propertyName must not be empty");
//        Assert.notNull(value, "value is required");
        if (value == null) {
            return null;
        }
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
        return (T) this.getSession().createQuery(hql).setParameter(0, value).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T get(Map<String,Object> values) {
//        Assert.hasText(propertyName, "propertyName must not be empty");
//        Assert.notNull(value, "value is required");
        if (values == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from " + this.entityClass.getName() + " as model where 1=1 ");
        for(String key:values.keySet()){
        	if(values.get(key) instanceof Collection){
        		hql.append(" and model."+key+" in (:"+key+") ");
        	}else{
        		hql.append(" and model."+key+" = :"+key+" ");
        	}
        }
        Query query = this.getSession().createQuery(hql.toString());
        this.setParameter(query, values);
        return (T) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
        return this.getSession().createQuery(hql).setParameter(0, value).list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(List<String> propertyNames, List<Object> values) {
    	
    	String hql = "from " + this.entityClass.getName() + " as model where ";
    	for(int i = 0; i< propertyNames.size(); i ++) {
    		String propertyName = propertyNames.get(i);
    		Object value = values.get(i);
	        Assert.hasText(propertyName, "propertyName must not be empty");
	        Assert.notNull(value, "value is required");
	        if(i == 0) {
	        	hql += " model." + propertyName + " = ?";
	        }else {
	        	hql += " and model." + propertyName + " = ?";
	        }
    	}
    	Query query = this.getSession().createQuery(hql);
    	for (int i = 0; i< propertyNames.size(); i ++) {
			query.setParameter(i, values.get(i));
		}
        return query.list();
    }
    
    

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String propertyName, Object[] values) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(values, "values is required");
        String hql = "from " + this.entityClass.getName() + " as model where model." + propertyName + " in(:values)";
        return this.getSession().createQuery(hql).setParameterList("values", values).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        String hql = "from " + this.entityClass.getName();
        return this.getSession().createQuery(hql).list();
    }

    @Override
    public Long getTotalCount() {
        String hql = "select count(*) from " + this.entityClass.getName();
        return (Long) this.getSession().createQuery(hql).uniqueResult();
    }

    @Override
    public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(newValue, "newValue is required");
        if (newValue == oldValue || newValue.equals(oldValue)) {
            return true;
        }
        if (newValue instanceof String) {
            if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
                return true;
            }
        }
        T object = this.get(propertyName, newValue);
        return object == null;
    }

    @Override
    public boolean isExist(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        T object = this.get(propertyName, value);
        return object != null;
    }

    @Override
    public Serializable save(T entity) {
        Assert.notNull(entity, "entity is required");
        return this.getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        Assert.notNull(entity, "entity is required");
        this.getSession().update(entity);
    }
    
    @Override
    public void merge(T entity) {
        Assert.notNull(entity, "entity is required");
        this.getSession().merge(entity);
    }
    

    @Override
    public T saveOrUpdate(T o) {
        this.getSession().saveOrUpdate(o);
        return o;
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "entity is required");
        this.getSession().delete(entity);
    }

    @Override
    public void delete(Serializable id) {
        Assert.notNull(id, "id is required");
        T entity = this.load(id);
        this.getSession().delete(entity);
    }

    @Override
    public void delete(Serializable[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        for (Serializable id : ids) {
            T entity = this.load(id);
            this.getSession().delete(entity);
        }
    }

    @Override
    public void delete(String propertyName, Object value) {
        Assert.notNull(propertyName, "propertyName is required");
        Assert.notNull(value, "value is required");
        String hql = "delete from " + this.entityClass.getName() + " as model where model." + propertyName + " = ?";
        this.getSession().createQuery(hql).setParameter(0, value).executeUpdate();
    }

    @Override
    public int delete(Map<String, Object> conditions) throws Exception {
        if (null == conditions || conditions.isEmpty()) {
            throw new Exception("No conditions!");
        }

        StringBuffer hql = new StringBuffer("delete from " + this.entityClass.getName() + " as model ");
        if (null != conditions && conditions.size() > 0) {
            hql.append(" where ");

            int i = 1;
            Set<String> keySet = conditions.keySet();
            for (String key : keySet) {
                Object value = conditions.get(key);
                if (i > 1) {
                    hql.append(" AND ");
                }
                if (value instanceof Collection<?> || value instanceof Object[]) {
                    hql.append(" model." + key + " IN(:" + key + ") ");
                } else {
                    hql.append(" model." + key + " = :" + key + " ");
                }
                ++i;
            }
        }

        Query createQuery = this.getSession().createQuery(hql.toString());
        createQuery = this.setParameter(createQuery, conditions);
        return createQuery.executeUpdate();
    }
    
    
    @Override
    public void evict(Object object) {
        Assert.notNull(object, "object is required");
        this.getSession().evict(object);
    }

    @Override
    public void flush() {
        this.getSession().flush();
    }

    @Override
    public void clear() {
        this.getSession().clear();
    }

    @Override
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    @Override
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = this.createCriteria(criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }

        return criteria;
    }

    @Override
    public List<T> getAllByOrder(String orderBy, boolean isAsc, boolean useCache) {
        return this.getLimitByOrder(orderBy, isAsc, -1, useCache);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getLimitByOrder(String orderBy, boolean isAsc, int limit, boolean useCache) {
        Assert.hasText(orderBy);

        Order order = isAsc ? Order.asc(orderBy) : Order.desc(orderBy);
        Criteria criteria = this.createCriteria();
        if (limit > 0) {
            criteria.setMaxResults(limit);
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(order).setCacheable(useCache);
        return criteria.list();
    }

    @Override
    public int getRowCount(Criteria criteria) {
        criteria.setProjection(Projections.rowCount());
        Long totalRows = (Long) criteria.uniqueResult();
        return totalRows.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByCriteria(Criteria criteria) {
        criteria = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByCriteria(Criteria criteria, int fistRow, int rowNum, boolean useCache) {
        criteria = criteria.setFirstResult(fistRow).setMaxResults(rowNum)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setCacheable(useCache);
        return criteria.list();
    }

    @Override
    public PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize) {
        int totalRows = this.getRowCount(criteria);
        criteria.setProjection(null);
        if (totalRows < 1) {
            PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows);
            finder.setData(new ArrayList<T>());
            return finder;
        } else {
            PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows);
            List<T> list = this.getListByCriteria(criteria, finder.getStartOfPage(), finder.getPageSize(), false);
            finder.setData(list);
            return finder;
        }
    }


    @Override
    public Query createQuery(String hql, Object... values) {
        Assert.hasText(hql, "sql 不能为空");
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    @Override
    public Query createQuery(String hql, Map<String, ?> values) {
        Assert.hasText(hql, "sql 不能为空");
        Query query = this.createQuery(hql);
        if (values != null) {
            query = this.setParameter(query, values);
        }
        return query;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getObjectByHql(String hql, Map<String, Object> values) {
        Query query = this.createQuery(hql, values);
        return (T) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByHql(String hql, Map<String, Object> values) {
        Query query = this.createQuery(hql);
        query = this.setParameter(query, values);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByHql(String hql, int firstRow, int maxNum, Map<String, Object> values) {
        Query query = this.createQuery(hql);
        query = this.setParameter(query, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        return query.list();
    }

    @Override
    public PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Map<String, Object> values) {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(hql));
        List<T> countlist = this.getListByHql(countQueryString, values);
        Long totalCount = (Long) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            List<T> list = this.getListByHql(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getListByHQL(String datasql, Map<String, Object> values) {
        Query dataQuery = this.createQuery(datasql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getListByHQL(String datasql, int firstRow, int maxNum, Map<String, Object> values) {
        Query dataQuery = this.createQuery(datasql, values);
        dataQuery.setFirstResult(firstRow);
        dataQuery.setMaxResults(maxNum);
        return dataQuery.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageFinder<Object> pagedObjectByHQL(String countHql, String hql, int toPage, int pageSize,
            Map<String, Object> values) {
        Query query = this.createQuery(countHql, values);
        Long totalCount = (Long) query.uniqueResult();
        if (totalCount.intValue() < 1) {
            return new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
        } else {
            PageFinder<Object> finder = new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
            List<Object> list = this.getListByHQL(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObjectByHql(String hql, Object... values) {
        Query query = this.createQuery(hql, values);
        List<T> list = query.list();
        if (null != list && list.size() > 0) {
            T first = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) != first) {
                    throw new NonUniqueResultException(list.size());
                }
            }
            return first;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getListByHql(String hql, Object... values) {
        Query dataQuery = this.createQuery(hql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByHql(String hql, int firstRow, int maxNum, Object... values) {
        Query query = this.createQuery(hql, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        return query.list();
    }
    
    
    @Override
    public PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Object... values) {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(hql));
        List<T> countlist = this.getListByHql(countQueryString, values);
        Long totalCount = (Long) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            List<T> list = this.getListByHql(hql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    @Override
    public SQLQuery createSQLQuery(String sql, Object... values) {
        Assert.hasText(sql, "sql 不能为空");
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    @Override
    public SQLQuery createSQLQuery(String sql, Map<String, ?> values) {
        Assert.hasText(sql, "sql 不能为空");
        Query query = this.createSQLQuery(sql);
        if (values != null) {
            query = this.setParameter(query, values);
        }
        return (SQLQuery) query;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getListBySQL(String datasql, Map<String, Object> values) {
        SQLQuery dataQuery = this.createSQLQuery(datasql, values);
        return dataQuery.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getListBySQL(String datasql, int firstRow, int maxNum, Map<String, Object> values) {
        SQLQuery dataQuery = this.createSQLQuery(datasql, values);
        dataQuery.setFirstResult(firstRow);
        dataQuery.setMaxResults(maxNum);
        return dataQuery.list();
    }

    @Override
    public PageFinder<Object> pagedObjectBySQL(String countsql, String datasql, int toPage, int pageSize,
            Map<String, Object> values) {
        SQLQuery query = this.createSQLQuery(countsql, values);
        Long totalCount = Long.parseLong(query.uniqueResult().toString());
        if (totalCount.intValue() < 1) {
            return new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
        } else {
            PageFinder<Object> finder = new PageFinder<Object>(toPage, pageSize, totalCount.intValue());
            List<Object> list = this.getListBySQL(datasql, finder.getStartOfPage(), finder.getPageSize(), values);
            finder.setData(list);
            return finder;
        }
    }

    /**
     * 取得对象的主键值,辅助函数.
     */
    @SuppressWarnings("unused")
    private Serializable getId(Object entity) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Assert.notNull(entity);
        return (Serializable) PropertyUtils.getProperty(entity, this.getIdName());
    }

    /**
     * 取得对象的主键名,辅助函数.
     */
    private String getIdName() {
        ClassMetadata meta = this.sessionFactory.getClassMetadata(this.entityClass);
        Assert.notNull(meta, "Class " + this.entityClass + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, this.entityClass.getSimpleName() + " has no identifier property define.");
        return idName;
    }

    /**
     * hql 设置参数
     * 
     * @Title: setParameter
     * @Description: TODO
     * @param query
     * @param map
     * @return
     * @throws
     * @author: yong
     * @date: 2012-12-17下午05:56:15
     */
    private Query setParameter(Query query, Map<String, ?> map) {
        if (map != null && !map.isEmpty()) {
            Set<String> keySet = map.keySet();
            for (String string : keySet) {
                Object obj = map.get(string);
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(string, (Object[]) obj);
                } else {
                    query.setParameter(string, obj);
                }
            }
        }
        return query;
    }
    /**
     * hql 设置参数
     * 
     * @Title: setParameter
     * @Description: TODO
     * @param query
     * @param map
     * @return
     * @throws
     * @author: yong
     * @date: 2012-12-17下午05:56:15
     */
    private SQLQuery setParameter(SQLQuery query, Map<String, ?> map) {
        if (map != null && !map.isEmpty()) {
            Set<String> keySet = map.keySet();
            for (String string : keySet) {
                Object obj = map.get(string);
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(string, (Object[]) obj);
                } else {
                    query.setParameter(string, obj);
                }
            }
        }
        return query;
    }
    /**
     * 去除hql的select 子句，未考虑union的情况用于pagedQuery.
     * 
     * @param hql
     * @return
     */
    private String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     * 
     * @param hql
     * @return
     */
    private String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    /**
     * 更新多条数据的某个字段
     */
	@Override
	public void updateList(String propertyName, Object propertyValue,
			Object[] ids) throws Exception {
		 	Assert.hasText(propertyName, "propertyName must not be empty");
		 	Assert.notNull(propertyValue, "propertyValue is required");
	        Assert.notNull(ids, "ids is required");
	        String hql = "update " + this.entityClass.getName() + " as model set model." + propertyName+" = ?  where model.id in(:ids)";
	        this.getSession().createQuery(hql).setParameter(0, propertyValue).setParameterList("ids", ids).executeUpdate();
	}
	
	/**
     * 更新某条条数据的某个字段
     */
	@Override
	public void updateList(String propertyName, Object propertyValue,
			String conditionName,Object conditionValue) throws Exception {
		 	Assert.hasText(propertyName, "propertyName must not be empty");
		 	Assert.notNull(propertyValue, "propertyValue is required");
	        Assert.notNull(conditionName, "conditionName is required");
	        Assert.notNull(conditionValue, "conditionValue is required");
	        String hql = "update " + this.entityClass.getName() + " as model set model." + propertyName+" = ?  where model."+conditionName+" = :values";
	        this.getSession().createQuery(hql).setParameter(0, propertyValue).setParameter("values", conditionValue).executeUpdate();
	}
	
	/**
     * 更新多条数据的某个字段
     */
	@Override
	public void updateList(String propertyName, Object propertyValue,
			String conditionName,Object[] conditionValues) throws Exception {
		 	Assert.hasText(propertyName, "propertyName must not be empty");
		 	Assert.notNull(propertyValue, "propertyValue is required");
		 	Assert.notNull(conditionName, "conditionName is required");
		    Assert.notNull(conditionValues, "conditionValue is required");
	        String hql = "update " + this.entityClass.getName() + " as model set model." + propertyName+" = ?  where model."+conditionName+" in(:values)";
	        this.getSession().createQuery(hql).setParameter(0, propertyValue).setParameterList("values", conditionValues).executeUpdate();
	}
	/**
     * 更新多条数据的多个字段
     */
	@Override
	public void updateList(Map<String,Object> updateParams,Map<String,Object> conditions) throws Exception {
		 	Assert.notNull(updateParams, "updateParams is required");
		 	Assert.notNull(conditions, "conditions is required");
		 	StringBuffer hql = new StringBuffer();
		 	hql.append("update " + this.entityClass.getName() + " as model set ");
		 	for(String updatekey:updateParams.keySet()){
		 		hql.append(" model."+updatekey+" = :"+updatekey+" ,");
		 	}
		 	hql.deleteCharAt(hql.length()-1);
		 	hql.append(" where 1=1 ");
		 	for(String key:conditions.keySet()){
		 		if(conditions.get(key) instanceof Collection){
		 			hql.append(" and model."+key+" in (:"+key+") ");
		 		}else{
		 			hql.append(" and model."+key+" = :"+key+" ");
		 		}
		 	}
		 	Query query=this.getSession().createQuery(hql.toString());
		 	Map<String,Object> values = new HashMap<String, Object>();
		 	values.putAll(updateParams);
		 	values.putAll(conditions);
		 	this.setParameter(query,values);
	        query.executeUpdate();
	}
	
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getListBySql(String sql, Object... values) throws Exception{
    	SQLQuery dataQuery = this.createSQLQuery(sql, values);
    	dataQuery.addEntity(entityClass);
        return dataQuery.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListBySql(String sql, int firstRow, int maxNum, Object... values) throws Exception{
        SQLQuery query = this.createSQLQuery(sql, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        query.addEntity(entityClass);
        return query.list();
    }
    
    @Override
	public List<T> getListBySql(String sql, int firstRow, int maxNum,
			Map<String, Object> values) throws Exception {
    	SQLQuery query = this.createSQLQuery(sql);
        query = this.setParameter(query, values);
        query.setFirstResult(firstRow);
        query.setMaxResults(maxNum);
        query.addEntity(entityClass);
        return query.list();
	}

	@Override
	public List<T> getListBySql(String sql, Map<String, Object> values)
			throws Exception {
		SQLQuery query = this.createSQLQuery(sql);
        query = this.setParameter(query, values);
        query.addEntity(entityClass);
		return query.list();
	}

	@Override
    public PageFinder<T> pagedBySQL(String sql, int toPage, int pageSize, Map<String, Object> values) throws Exception {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(sql));
        SQLQuery query = this.createSQLQuery(countQueryString);
        query = this.setParameter(query, values);
        List countlist =  query.list();
        BigInteger totalCount = (BigInteger) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            SQLQuery dataQuery = this.createSQLQuery(sql, values);
            dataQuery.setFirstResult(finder.getStartOfPage());
            dataQuery.setMaxResults( finder.getPageSize());
            dataQuery.addEntity(entityClass);
            List<T> list = dataQuery.list();
            finder.setData(list);
            return finder;
        }
    }
    
    @Override
    public PageFinder<T> pagedBySQL(String sql, int toPage, int pageSize, Object... values) throws Exception {
        String countQueryString = " select count(*) " + this.removeSelect(this.removeOrders(sql));
        SQLQuery query = this.createSQLQuery(countQueryString, values);
        List countlist =  query.list();
        BigInteger totalCount = (BigInteger) countlist.get(0);

        if (totalCount.intValue() < 1) {
            return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
        } else {
            final PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
            SQLQuery dataQuery = this.createSQLQuery(sql, values);
            dataQuery.setFirstResult(finder.getStartOfPage());
            dataQuery.setMaxResults( finder.getPageSize());
            dataQuery.addEntity(entityClass);
            List<T> list = dataQuery.list();
            finder.setData(list);
            return finder;
        }
    }
    
}
