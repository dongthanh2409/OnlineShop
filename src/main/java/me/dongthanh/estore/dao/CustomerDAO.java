package me.dongthanh.estore.dao;

import java.util.List;

import me.dongthanh.estore.entity.Customer;

public interface CustomerDAO {
	
	Customer create(Customer entity);
	
	List<Customer> findAll();
	
	Customer findById(String id);
	
	void update(Customer entity);
	
	Customer delete(String id);

	long getPageCount(int pageSize);

	List<Customer> getPage(int pageSize, int pageNo);
		
}
