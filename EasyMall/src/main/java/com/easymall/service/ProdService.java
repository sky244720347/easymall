package com.easymall.service;

import com.easymall.pojo.Product;

import java.util.List;

public interface ProdService extends Service {
    /**
     * 查询所有商品, 返回所有商品组成的list集合
     *
     * @return List<Product>
     */
    List<Product> findAll();

    /**
     * 修改指定商品的库存数量
     *
     * @param id   商品的id
     * @param pnum 商品的库存数量
     * @return boolean true表示修改成功!
     */
    boolean updatePnum(String id, int pnum);

    /**
     * 根据商品的id删除指定的商品
     *
     * @param id 商品的id
     * @return boolean true表示删除成功!
     */
    boolean delProd(String id);

    /**
     * 根据条件查询所有符合条件的商品集合
     *
     * @param _name     商品的名称
     * @param _category 商品的分类
     * @param _minprice 商品的最低价格
     * @param _maxprice 商品的最高价格
     * @return List<Product>
     */
    List<Product> findAllByCondition(String _name, String _category,
                                     double _minprice, double _maxprice);

    /**
     * 根据条件查询素所有符合条件的商品
     *
     * @param search 查询条件(商品名称/商品分类/商品描述)
     * @return List<Product>
     */
    List<Product> findAllBySearch(String search);

    /**
     * 根据商品的id查询指定的商品
     *
     * @param pid 商品id
     * @return Product
     */
    Product findProdById(String pid);
}
