package com.project.githering.JWT.Repository;

import com.project.githering.JWT.Entity.RefreshToken;
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
    void deleteById(String loginUser);

    //READ
    Optional<RefreshToken> findById(String loginUser);

    boolean existsById(String loginUser);
}
