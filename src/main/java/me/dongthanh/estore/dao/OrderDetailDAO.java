package me.dongthanh.estore.dao;

import java.util.List;

import me.dongthanh.estore.entity.Order;
import me.dongthanh.estore.entity.OrderDetail;

public interface OrderDetailDAO {
	
	OrderDetail create(OrderDetail entity);
	
	List<OrderDetail> findAll();
	
	OrderDetail findById(int id);
	
	void update(OrderDetail entity);
	
	OrderDetail delete(int id);

	List<OrderDetail> findByOrder(Order order);
		
}
