package me.dongthanh.estore.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import me.dongthanh.estore.dao.ProductDAO;
import me.dongthanh.estore.entity.Product;

@SessionScope //scopedTarget.cartService
@Service
public class CartService {
	@Autowired
	ProductDAO dao;
	Map<Integer, Product> map = new HashMap<>();
	
	//get quantity of product in repository
	public Map<Integer, Integer> maxQty() {
		Set<Integer> ids =  map.keySet();
		Map<Integer, Integer> mapMaxQty = new HashMap<>();
		if(ids != null) {
			for (int id : ids) {
				mapMaxQty.put(id, dao.findById(id).getQuantity());
			}
		}
		return mapMaxQty;
	}
	
	public void add(int id) {
		Product p = map.get(id);
		if(p == null) {
			p = dao.findById(id);
			p.setQuantity(1);
			map.put(id, p);
		} else {
			p.setQuantity(p.getQuantity() + 1);
		}
	}
	
	public void remove(int id) {
		map.remove(id);
	}
	
	public void update(int id, int qty) {
		Product p = map.get(id);
		p.setQuantity(qty);
	}
	
	public void clear() {
		map.clear();
	}
	
	public int getCount() {
		Collection<Product> ps = this.getItems();
		int count = 0;
		for(Product p : ps) {
			count += p.getQuantity();
		}
		return count;
	}
	
	public double getAmount() {
		Collection<Product> ps = this.getItems();
		double amount = 0;
		for(Product p : ps) {
			amount += (p.getQuantity() * p.getUnitPrice() * (1-p.getDiscount()/100));
		}
		return amount;
	}
	
	public Collection<Product> getItems() {
		return map.values();
	}
}
