package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "staffs")
    private List<BuildingEntity> buildings = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<CustomerEntity> customers = new ArrayList<>();

//    @OneToMany(mappedBy="users", fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();

}
