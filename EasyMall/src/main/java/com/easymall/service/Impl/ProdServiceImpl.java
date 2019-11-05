package com.easymall.service.Impl;

import com.easymall.dao.ProductMapper;
import com.easymall.pojo.Product;
import com.easymall.service.ProdService;
import com.easymall.utils.TranManager;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdServiceImpl implements ProdService {
    public List<Product> findAll() {
        SqlSession session = TranManager.getConn();
        List<Product> list = session.getMapper(ProductMapper.class).findAll();
        return list;
    }

    public boolean updatePnum(String id, int pnum) {
        SqlSession session = TranManager.getConn();
        Product p = new Product();
        p.setId(id);
        p.setPnum(pnum);
        int result = session.getMapper(ProductMapper.class).updatePnum(p);
        return result > 0;
    }

    public boolean delProd(String id) {
        SqlSession session = TranManager.getConn();
        Product p = new Product();
        p.setId(id);
        int result = session.getMapper(ProductMapper.class).delProd(p);
        return result > 0;
    }

    public List<Product> findAllByCondition(String _name, String _category, double _minprice, double _maxprice) {
        Map map = new HashMap();
        map.put("name", _name == null ? "" : _name.trim());
        map.put("category", _category == null ? "" : _category.trim());
        map.put("minprice", _minprice);
        map.put("maxprice", _maxprice);
        SqlSession session = TranManager.getConn();
        List<Product> list = session.getMapper(ProductMapper.class).findAllByCondition(map);
        return list;
    }

    public List<Product> findAllBySearch(String keyword) {
        SqlSession session = TranManager.getConn();
        List<Product> list = session.getMapper(ProductMapper.class).findAllBySearch(keyword);
        return list;
    }

    public Product findProdById(String id) {
        SqlSession session = TranManager.getConn();
        Product p = new Product();
        p.setId(id);
        p = session.getMapper(ProductMapper.class).findProdById(p);
        return p;
    }
}