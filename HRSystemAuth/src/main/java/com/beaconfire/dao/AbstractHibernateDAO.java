package com.beaconfire.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDAO<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<T> clazz;

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	public T findById(final Integer id) {
		return getCurrentSession().get(clazz, id);
	}

	public List<T> findByField(final String field, final String value) {
		CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(field), value));
		return getCurrentSession().createQuery(criteriaQuery).getResultList();
	}

	public void persist(T t) {
		getCurrentSession().persist(t);
	}

	public void flush() {
		getCurrentSession().flush();
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
