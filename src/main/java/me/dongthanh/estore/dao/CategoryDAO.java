package me.dongthanh.estore.dao;

import java.util.List;

import me.dongthanh.estore.entity.Category;

public interface CategoryDAO {
	
	Category create(Category entity);
	
	List<Category> findAll();
	
	Category findById(int id);
	
	void update(Category entity);
	
	Category delete(int id);
		
}
