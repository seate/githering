package com.project.githering.Group.Belong.Service;


public interface GroupBelongService {

    void joinGroup(Long userId, Long groupId);

    void withdrawalGroup(Long userId, Long groupId);

    boolean isJoined(Long userId, Long groupId);

    Integer countByGroupId(Long groupId);
}
