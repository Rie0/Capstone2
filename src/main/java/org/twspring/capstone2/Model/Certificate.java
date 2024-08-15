package org.twspring.capstone2.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //added to users
    //has id's, stored as pdf file...........not sure if i want to keep this one
}
