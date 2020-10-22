package me.dongthanh.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class ReportDAOimpl implements ReportDAO{
	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Object[]> inventory() {
		String hql = "SELECT p.category.nameVN,"
				+ " SUM(p.quantity),"
				+ " SUM(p.unitPrice*p.quantity),"
				+ " MIN(p.unitPrice),"
				+ " MAX(p.unitPrice),"
				+ " AVG(p.unitPrice)"
				+ " FROM Product p"
				+ " GROUP BY p.category.nameVN";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByCategory() {
		String hql = "SELECT d.product.category.nameVN,"
				+ " SUM(d.quantity),"
				+ " SUM(d.unitPrice*d.quantity),"
				+ " MIN(d.unitPrice),"
				+ " MAX(d.unitPrice),"
				+ " AVG(d.unitPrice)"
				+ " FROM OrderDetail d"
				+ " WHERE d.order.completed=true"
				+ " GROUP BY d.product.category.nameVN";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByCustomer() {
		String hql = "SELECT d.order.customer.id,"
				+ " SUM(d.quantity),"
				+ " SUM(d.unitPrice*d.quantity)"
				+ " FROM OrderDetail d"
				+ " WHERE d.order.completed=true"
				+ " GROUP BY d.order.customer.id"
				+ " ORDER BY SUM(d.unitPrice*d.quantity) DESC";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByYear() {
		String hql = "SELECT YEAR(d.order.orderDate),"
				+ " SUM(d.quantity),"
				+ " SUM(d.unitPrice*d.quantity)"
				+ " FROM OrderDetail d"
				+ " WHERE d.order.completed=true"
				+ " GROUP BY YEAR(d.order.orderDate)"
				+ " ORDER BY YEAR(d.order.orderDate) DESC";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByQuarter() {
		String hql = "SELECT CEILING((MONTH(d.order.orderDate)+1)/3.0),"
				+ " SUM(d.quantity),"
				+ " SUM(d.unitPrice*d.quantity)"
				+ " FROM OrderDetail d"
				+ " WHERE d.order.completed=true"
				+ " AND YEAR(d.order.orderDate)=2020"
				+ " GROUP BY CEILING((MONTH(d.order.orderDate)+1)/3.0)"
				+ " ORDER BY CEILING((MONTH(d.order.orderDate)+1)/3.0) ASC";
		
		Session session = factory.getCurrentSession();
		
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByMonth() {
		String hql = "SELECT MONTH(d.order.orderDate),"
				+ " SUM(d.quantity),"
				+ " SUM(d.unitPrice*d.quantity)"
				+ " FROM OrderDetail d"
				+ " WHERE d.order.completed=true"
				+ " AND YEAR(d.order.orderDate)=2020"
				+ " GROUP BY MONTH(d.order.orderDate)"
				+ " ORDER BY MONTH(d.order.orderDate) ASC";
		
		Session session = factory.getCurrentSession();
		TypedQuery<Object[]>query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

}
