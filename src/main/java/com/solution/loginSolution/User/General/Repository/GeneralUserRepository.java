package com.solution.loginSolution.User.General.Repository;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {

    //CREATE
    @Override
    <S extends GeneralUser> S save(S entity);

    //DELETE
    void deleteByUserEmail(String userEmail);

    //READ
    boolean existsByUserEmail(String userEmail);

    Optional<GeneralUser> findById(Long id);

    Optional<GeneralUser> findByUserEmail(String userEmail);
}
