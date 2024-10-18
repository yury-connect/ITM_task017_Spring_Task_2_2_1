package hiber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long id;

   @Column(name = "user_first_name")
   private String firstName;

   @Column(name = "user_last_name")
   private String lastName;

   @Column(name = "user_email")
   private String email;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // позволяет применять все операции (например, сохранение или удаление) каскадно к связанному объекту Car.
   @JoinColumn(name = "fk_car_id", referencedColumnName = "car_id") // fk_car_id ссылается на car_id в таблице cars
   private Car car;


   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.car = car;
   }
}
