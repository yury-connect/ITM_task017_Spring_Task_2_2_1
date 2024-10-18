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
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
//              .createQuery("from User u WHERE u.car.model = :model AND u.car.series = :series", User.class); // можно было оставить так, но в классе 'User' поставить 'FetchType.EAGER' жадную инициализацию, что может привести к проблемам с производительностью.
              .createQuery("from User u join fetch u.car WHERE u.car.model = :model AND u.car.series = :series", User.class);

      query.setParameter("model", model);
      query.setParameter("series", series);

      List<User> users = query.getResultList();
      return users.get(0);
   }
}

/*
Сейчас рассмотрим детально этроку запроса:
.createQuery("from User u join fetch u.car WHERE u.car.model = :model AND u.car.series = :series", User.class);

 "from User u join fetch u.car"
from User: это означает, что мы запрашиваем сущность User из базы данных.
u: это алиас для сущности User. С его помощью мы можем ссылаться на поля этого объекта в дальнейшем
 join fetch u.car: это инструкция для жадного соединения (fetch join) с другой сущностью, связанной с User.
 В данном случае, u.car — это связь между сущностью User и сущностью Car.

 Как работает join fetch?
join: выполняет соединение двух сущностей (User и Car) на уровне объекта.
fetch: указывает Hibernate, что нужно немедленно загрузить данные связанной сущности (Car).
 Это отличается от стандартного ленивого (lazy) соединения, при котором данные связанной сущности загружаются
 только при прямом обращении к ней, что и вызывает ошибку LazyInitializationException, если сессия уже закрыта.
Вместо получения лишь ссылки на объект Car (при ленивой загрузке), Hibernate загружает все поля объекта Car в один запрос.

 "WHERE u.car.model = :model AND u.car.series = :series"
WHERE: добавляет фильтрацию данных. Мы указываем условие для выборки, которое должно быть выполнено.
u.car.model = :model: фильтрует пользователей по модели автомобиля. :model — это именованный параметр,
 значение которого будет установлено программно с помощью метода query.setParameter("model", model).
u.car.series = :series: аналогично фильтрует по серии автомобиля.

 "User.class"
Второй параметр метода 'createQuery' — это тип, возвращаемый запросом. createQuery —
 это класс, который указывает Hibernate, что ожидается результат
 в виде объектов типа User. Таким образом, запрос возвращает список объектов User, а не массивы данных.
 */

