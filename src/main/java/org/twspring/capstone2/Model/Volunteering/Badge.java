package org.twspring.capstone2.Model.Volunteering;

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
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //granted automatically or via admin to volunteers

    //each badge has a level from 1-5 when a higher level badge is earned the previous is replaced/deleted

    //has a family, ex f1:volunteering hours level1 =5 level2=20 level3=50 level4=100 level5=200 (a bit too much, no?)
}
