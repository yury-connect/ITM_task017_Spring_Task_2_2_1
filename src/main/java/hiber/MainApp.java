package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import lombok.var;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      {
         userService.add(User.builder()
                 .firstName("User1")
                 .lastName("Lastname1")
                 .email("user1@mail.ru")
                 .car(Car.builder()
                         .model("Model1")
                         .series(1)
                         .build())
                 .build());

         userService.add(User.builder()
                 .firstName("User2")
                 .lastName("Lastname2")
                 .email("user2@mail.ru")
                 .car(Car.builder()
                         .model("Model2")
                         .series(2)
                         .build())
                 .build());

         userService.add(User.builder()
                 .firstName("User3")
                 .lastName("Lastname3")
                 .email("user3@mail.ru")
                 .car(Car.builder()
                         .model("Model3")
                         .series(3)
                         .build())
                 .build());

         userService.add(User.builder()
                 .firstName("User4")
                 .lastName("Lastname4")
                 .email("user4@mail.ru")
                 .car(Car.builder()
                         .model("Model4")
                         .series(4)
                         .build())
                 .build());

         userService.add(User.builder()
                 .firstName("User5")
                 .lastName("Lastname5")
                 .email("user5@mail.ru")
                 .car(Car.builder()
                         .model("Model5")
                         .series(5)
                         .build())
                 .build());
      }


      Car foundCar = Car.builder()
              .model("Model3")
              .series(3)
              .build();

      System.out.println("\n\t Вывожу на экран всех имеющихся в базе пользователей и их машины:");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User foundUser = context.getBean(UserService.class)
              .getUserByCarModelAndSeries(foundCar.getModel(), foundCar.getSeries());

      System.out.printf("\n\n\tПроизводим поиск 'User', которому принадлежит автомобиль: \nCar(model=%s, series=%d)\n"
              , foundCar.getModel(), foundCar.getSeries());
      if (foundUser != null) {
         System.out.printf("\n\t'User' найден, вывожу результат поиска: \n%s\n\n", foundUser);
      } else {
         System.out.printf("\n\t'User', у которого есть автомобиль '%s' не найден\n\n", foundCar);
      }

      context.close();
   }
}
