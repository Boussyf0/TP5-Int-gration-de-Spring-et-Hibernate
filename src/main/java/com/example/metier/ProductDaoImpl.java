package com.example.metier;

import com.example.dao.IDao;
import com.example.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDaoImpl implements IDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
        return true;
    }

    // Méthodes restantes à implémenter :
    @Override
    public boolean delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
        return true;
    }

    @Override
    public boolean update(Product product) {
        sessionFactory.getCurrentSession().update(product);
        return true;
    }

    @Override
    public Product findById(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Product.class)
                .list();
    }
}
