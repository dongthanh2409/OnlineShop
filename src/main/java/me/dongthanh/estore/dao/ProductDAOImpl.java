package me.dongthanh.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import me.dongthanh.estore.entity.Product;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public Product create(Product entity) {
		Session session = factory.getCurrentSession();
		session.persist(entity);	
		return entity;
	}

	@Override
	public List<Product> findAll() {
		String hql = "FROM Product";
		Session session = factory.getCurrentSession();
		TypedQuery<Product>query = session.createQuery(hql, Product.class);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public Product findById(int id) {
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		return entity;
	}

	@Override
	public void update(Product entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);	
		
	}

	@Override
	public Product delete(int id) {
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<Product> findByCategoryId(int categoryId) {
		String hql = "FROM Product p WHERE p.category.id=:cid";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("cid", categoryId);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		String hql = "FROM Product p "
				+ "WHERE p.name LIKE :kw OR p.category.name LIKE :kw OR p.category.nameVN LIKE :kw";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("kw", "%"+keywords+"%");
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByIds(String ids) {
		String hql = "FROM Product p WHERE p.id IN (" + ids + ")";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);		
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findBySpecial(int id) {
		Session session = factory.getCurrentSession();
		String hql = "";
		switch (id) {
		case 0:	//new
			hql = "FROM Product p ORDER BY p.productDate DESC";		
			break;
		case 1:	//best seller
			hql = "FROM Product p ORDER BY size(p.orderDetails) DESC";	
			break;
		case 2:	//most viewed
			hql = "FROM Product p ORDER BY p.viewCount DESC";
			break;
		case 3:	//on sale
			hql = "FROM Product p ORDER BY p.discount DESC";
			break;
		}

		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setMaxResults(2);
		List<Product> list = query.getResultList();
		return list;
	}

}
