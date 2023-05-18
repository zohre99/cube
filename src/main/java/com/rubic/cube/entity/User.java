package com.rubic.cube.entity;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Data
@Audited
public class User extends BaseEntity {

    @Id
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    private Long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

}
