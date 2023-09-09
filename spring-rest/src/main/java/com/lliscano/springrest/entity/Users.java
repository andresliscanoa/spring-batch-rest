package com.lliscano.springrest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(sequenceName = "users_sequence", name = "users_sequence", schema = "public")
    private Long id;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "gender", nullable = false, length = 100)
    private String gender;

}
