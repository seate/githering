package com.project.githering.Group.Repository;

import com.project.githering.Group.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    //CREATE
    @Override
    <S extends Group> S save(S entity);

    //DELETE
    void deleteById(Long id);

    void deleteByGroupName(String groupName);

    //READ
    @Override
    Optional<Group> findById(Long aLong);

    Optional<Group> findByGroupName(String groupName);
}
