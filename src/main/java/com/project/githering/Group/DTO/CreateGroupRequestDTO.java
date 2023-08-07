package com.project.githering.Group.DTO;

import com.project.githering.Group.Entity.Group;
import com.project.githering.Group.Enum.GroupType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupRequestDTO {

    private GroupType groupType;

    @NotBlank
    private String groupName;

    private String groupDescription;

    public Group toEntity(Long groupMasterId) {
        return Group.builder()
                .groupMasterId(groupMasterId)
                .groupType(groupType)
                .groupName(groupName)
                .groupDescription(groupDescription)
                .build();
    }
}
