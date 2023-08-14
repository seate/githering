package com.project.githering.Board.Posting.Addon.Scrap.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@IdClass(ScrapPk.class)
public class Scrap {

    @Id
    @Column(nullable = false)
    private Long postingId;

    @Id
    @Column(nullable = false)
    private Long userId;

    public Scrap(Long postingId, Long userId) {
        this.postingId = postingId;
        this.userId = userId;
    }
}
