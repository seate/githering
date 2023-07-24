package com.solution.loginSolution.JWT.Repository;

import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoutRedisRepository extends CrudRepository<LogoutAccessToken, Long> {
    @Override
    <S extends LogoutAccessToken> S save(S entity);


    Optional<LogoutAccessToken> findByAccessToken(String accessToken);
}
