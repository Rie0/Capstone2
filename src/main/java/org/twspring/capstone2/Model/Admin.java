package org.twspring.capstone2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    private String name;

    //add email n password?? ASK!

    //Admin approves volunteering requests and might apply badges to volunteers

    //has no special attributes

}
