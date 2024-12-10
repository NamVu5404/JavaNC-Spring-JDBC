package com.javaweb.repository;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository {
    UserEntity findOneByUserNameAndStatus(String name, int status);

    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
                                                                                                  Pageable pageable);

    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);

    Page<UserEntity> findByStatusNot(int status, Pageable pageable);

    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);

    long countByStatusNot(int status);

    UserEntity findOneByUserName(String userName);

    UserEntity findByUserName(String userName);

    boolean existsByUserName(String userName);

    List<UserEntity> findAllById(List<Long> ids);

    UserEntity findById(Long id);

    UserEntity save(UserEntity user);
}
