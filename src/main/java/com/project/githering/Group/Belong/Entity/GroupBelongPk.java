package com.project.githering.Group.Belong.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupBelongPk implements Serializable {
    private Long groupId;
    private Long userId;
}
