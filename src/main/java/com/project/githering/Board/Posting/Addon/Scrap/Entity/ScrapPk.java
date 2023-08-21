package com.project.githering.Board.Posting.Addon.Scrap.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapPk implements Serializable {

    private Long postingId;

    private Long userId;
}
