package com.lliscano.springbatch.repository;

import com.lliscano.springbatch.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Override
    List<Users> findAll();
}
