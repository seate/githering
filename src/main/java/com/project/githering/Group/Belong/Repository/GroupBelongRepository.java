package com.project.githering.Group.Belong.Repository;

import com.project.githering.Group.Belong.Entity.GroupBelong;
import com.project.githering.Group.Belong.Entity.GroupBelongPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupBelongRepository extends JpaRepository<GroupBelong, GroupBelongPk> {

    //CREATE
    @Override
    <S extends GroupBelong> S save(S entity);

    //DELETE
    void delete(GroupBelong groupBelong);

    void deleteAllByGroupId(Long groupId);

    //READ
    boolean existsByUserIdAndGroupId(Long userId, Long groupId);

    Integer countByGroupId(Long groupId);
}
