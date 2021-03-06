package com.pluralsight.demo.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

import com.pluralsight.demo.jdbc.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}

	public List<Person> findAllWithNativeQuery() {
		Query query = entityManager.createNativeQuery("SELECT * from person",Person.class);
		return query.getResultList();
	}

	public List<Person> findAllWithNativeQueryId(String name) {
		Query query = entityManager.createNativeQuery("SELECT * from person where name = :name",Person.class);
		query.setParameter("name",name);
		return query.getResultList();
	}

	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

	public Person update(Person person) {
		return entityManager.merge(person);
	}

	public Person insert(Person person) {
		return entityManager.merge(person);
	}

	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);
	}
}
