package com.project.githering.JWT.Repository;

import com.project.githering.JWT.Entity.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutRedisRepository extends CrudRepository<LogoutAccessToken, String> {
    @Override
    <S extends LogoutAccessToken> S save(S entity);

    boolean existsById(String accessToken);
}
