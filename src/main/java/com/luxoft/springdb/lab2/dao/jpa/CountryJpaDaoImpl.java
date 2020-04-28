package com.luxoft.springdb.lab2.dao.jpa;

import com.luxoft.springdb.lab2.dao.CountryDao;
import com.luxoft.springdb.lab2.model.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    @Override
    public void save(Country country) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(country);
        et.commit();

        em.close();
    }

    @Override
    public List<Country> getAllCountries() {
		List<Country> countryList = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		countryList = em.createQuery("from Country", Country.class).getResultList();
		em.close();
		return countryList;
    }

    @Override
    public Country getCountryByName(String name) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Country country = null;
		country = em.createQuery("SELECT c FROM Country c WHERE c.name LIKE :name", Country.class)
				.setParameter("name", name)
				.getSingleResult();
		em.close();
        return country;
    }

}
