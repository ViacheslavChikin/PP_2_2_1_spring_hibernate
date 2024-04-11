package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override

    public User getUserByCarIdAndModel(String model, int series) {
        User user = sessionFactory.getCurrentSession()
                .createQuery("from User where car = (from Car where model = ?1 and series = ?2)", User.class)
                .setParameter(1, model)
                .setParameter(2, series)
                .getSingleResult();
        return user;
    }
}
