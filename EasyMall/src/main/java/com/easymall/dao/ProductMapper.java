package com.easymall.dao;

import com.easymall.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    /**
     * 查询所有商品
     *
     * @return 所有商品
     */
    List<Product> findAll();

    /**
     * 根据商品id修改商品数量
     *
     * @param p 需设置商品id和pnum
     * @return 是否修改成功
     */
    int updatePnum(Product p);

    /**
     * 删除指定商品
     *
     * @param p 需设置商品id
     * @return 是否删除成功
     */
    int delProd(Product p);

    /**
     * 根据条件查询商品
     *
     * @param params 可以指定name, category, minprice, maxprice
     * @return
     */
    List<Product> findAllByCondition(Map<String, Object> params);

    /**
     * 根据关键词查询商品
     *
     * @param keyword 关键词
     * @return
     */
    List<Product> findAllBySearch(String keyword);

    /**
     * 查找指定商品
     *
     * @param p 需设置商品id
     * @return
     */
    Product findProdById(Product p);

    /**
     * 修改指定商品数量
     *
     * @param params 需要指定商品id和要修改的数量pnumAdd
     */
    void changePnum(Map<String, Object> params);
}