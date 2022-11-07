package ru.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();

    }

   @Transactional
   public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Person.class, id);
   }

   @Transactional
   public Optional<Person> show(String email) {
        Session session = sessionFactory.getCurrentSession();
        String query ="SELECT p FROM Person p WHERE p.email = :email";
        return session.createQuery(query, Person.class)
                .setParameter("email", email)
                .uniqueResultOptional();
   }

   @Transactional
   public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setAddress(updatedPerson.getAddress());
        personToBeUpdated.setEmail(updatedPerson.getEmail());

    }

    @Transactional
    public void delete(int id) {

        Session session = sessionFactory.getCurrentSession();

        Person personToDelete = session.get(Person.class, id);

        session.delete(personToDelete);
    }

}
