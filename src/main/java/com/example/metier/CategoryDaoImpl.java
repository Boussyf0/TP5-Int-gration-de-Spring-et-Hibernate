package com.example.metier;

import com.example.dao.IDao;
import com.example.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDaoImpl implements IDao<Category> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Category category) {
        sessionFactory.getCurrentSession().update(category);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category c left join fetch c.products where c.id = :id", Category.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category c left join fetch c.products", Category.class)
                .list();
    }
}