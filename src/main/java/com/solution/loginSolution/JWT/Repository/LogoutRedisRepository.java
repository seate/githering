package com.solution.loginSolution.JWT.Repository;

import com.solution.loginSolution.JWT.Entity.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutRedisRepository extends CrudRepository<LogoutAccessToken, String> {
    @Override
    <S extends LogoutAccessToken> S save(S entity);

    boolean existsById(String accessToken);
}
