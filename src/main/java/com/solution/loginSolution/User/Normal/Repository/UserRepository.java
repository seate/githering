package com.solution.loginSolution.User.Normal.Repository;

import com.solution.loginSolution.User.Normal.Entity.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<NormalUser, Long> {

    //CREATE
    @Override
    <S extends NormalUser> S save(S entity);

    //DELETE
    void deleteByUserEmail(String userEmail);

    //READ
    boolean existsByUserEmail(String userEmail);

    Optional<NormalUser> findById(Long id);

    Optional<NormalUser> findByUserEmail(String userEmail);
}
