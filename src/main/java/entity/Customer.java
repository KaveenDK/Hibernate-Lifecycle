package entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * --------------------------------------------
 * @Author Dimantha Kaveen
 * @GitHub: https://github.com/KaveenDK
 * @Date 2/21/2025
 * @Project Hibernate-Lifecycle
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto - hibernate
    // Identity - auto generate on database (auto increment)
    // without @GeneratedValue, you can add customer value

    private int id;
    private String Name;
}
