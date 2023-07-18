package com.tpe.repository;

import com.tpe.domain.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CourseRepositoryImpl  implements CourseRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course course) {

       Session session = sessionFactory.openSession();
      Transaction tx= session.beginTransaction();

      session.saveOrUpdate(course);//it will be update   the object if is exist but if it is not exist it will be save the object

      tx.commit();
      session.close();

    }

    @Override
    public List<Course> getAll() {

        Session session = sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

       List<Course> courseList = session.createQuery("FROM Course").getResultList();

        tx.commit();
        session.close();
        return courseList;
    }


    @Override
    public Optional<Course> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Course course = session.get(Course.class, id);

        Optional<Course> optCourse = Optional.ofNullable(course);
        //if it takes a null value, it says throw an exception. Instead of null, an empty optional is returned.

        tx.commit();
        session.close();
        return optCourse;

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Course course=session.load(Course.class,id);
        session.delete(course);

        tx.commit();
        session.close();
    }




}
