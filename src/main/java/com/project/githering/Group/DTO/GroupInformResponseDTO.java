package com.project.githering.Group.DTO;

import com.project.githering.Group.Entity.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupInformResponseDTO {

    @NotNull
    private Long groupId;

    @NotBlank
    private String groupName;

    public GroupInformResponseDTO(Group group) {
        this.groupId = group.getGroupId();
        this.groupName = group.getGroupName();
    }
}
