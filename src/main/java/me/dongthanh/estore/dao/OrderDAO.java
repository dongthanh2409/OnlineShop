package me.dongthanh.estore.dao;

import java.util.List;

import me.dongthanh.estore.entity.Customer;
import me.dongthanh.estore.entity.Order;
import me.dongthanh.estore.entity.OrderDetail;

public interface OrderDAO {
	
	Order create(Order entity);
	
	List<Order> findAll();
	
	Order findById(int id);
	
	void update(Order entity);
	
	Order delete(int id);

	void create(Order order, List<OrderDetail> details);

	List<Order> findByUser(Customer user);
		
}
