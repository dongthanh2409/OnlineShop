package me.dongthanh.estore.dao;

import java.util.List;

import me.dongthanh.estore.entity.Product;

public interface ProductDAO {
	
	Product create(Product entity);
	
	List<Product> findAll();
	
	Product findById(int id);
	
	void update(Product entity);
	
	Product delete(int id);

	List<Product> findByCategoryId(int categoryId);

	List<Product> findByKeywords(String keywords);

	List<Product> findByIds(String ids);

	List<Product> findBySpecial(int id);

}
