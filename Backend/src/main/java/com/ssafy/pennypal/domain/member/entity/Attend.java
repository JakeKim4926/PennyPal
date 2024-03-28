package com.ssafy.pennypal.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attend {
    @Id
    @GeneratedValue
    @Column(name = "attend_id")
    private Long attendId;

    @Setter
    @Column(name = "attend_last_date")
    private LocalDateTime attendLastDate;

    @Setter
    @Column(name = "attend_is_attended")
    private Boolean attendIsAttended;

    @Builder
    public Attend(LocalDateTime attendLastDate, Boolean attendIsAttended) {
        this.attendLastDate = attendLastDate;
        this.attendIsAttended = attendIsAttended;
    }
}
