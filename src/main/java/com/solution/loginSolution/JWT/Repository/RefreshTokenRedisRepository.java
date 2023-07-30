package com.solution.loginSolution.JWT.Repository;

import com.solution.loginSolution.JWT.Entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {

    //CREATE
    @Override
    <S extends RefreshToken> S save(S entity);

    //DELETE
    @Override
    void deleteById(String userEmail);

    //READ
    Optional<RefreshToken> findById(String userEmail);

    boolean existsById(String userEmail);
}
