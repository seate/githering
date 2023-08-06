package com.project.githering.User.General.Repository;

import com.project.githering.User.General.Entity.GeneralUser;
import com.project.githering.User.General.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {

    //CREATE
    @Override
    <S extends GeneralUser> S save(S entity);

    //DELETE
    void deleteByLoginUser(String loginUser);

    //READ
    boolean existsByLoginUser(String loginUser);

    Optional<GeneralUser> findById(Long id);

    Optional<GeneralUser> findByLoginUser(String loginUser);

    List<GeneralUser> findAllByRole(Role role);
}
