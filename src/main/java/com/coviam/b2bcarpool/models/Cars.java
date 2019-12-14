package com.coviam.b2bcarpool.models;

import javax.persistence.*;

@Entity
@Table(
        name="user-cars",
        uniqueConstraints = @UniqueConstraint(columnNames={"carNumber"})
)
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String carId;
    private String userId;
    private String carNumber;
    private String carType;
    private String carName;
}
