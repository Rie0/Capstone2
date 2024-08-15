package org.twspring.capstone2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    private String name;

    //other info


    //volunteers apply to volunteering opportunities

    //can be enlisted in one opportunity until withdrawn or finished

    //top 3 volunteers per month/week will be displayed (Get request to get top 3 volunteers by gained hours)

    //top 3 volunteers of all time (by <Integer> badge_Ids count)

    //has reviews by organization worked with

    //had important attributes like gender/employment condition/age/is physically fit to determinate what opportunities to apply for
    //has their own search, but can search all
}
