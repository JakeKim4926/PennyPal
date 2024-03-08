package com.example.demo.domain;

import com.example.demo.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String teamName;

    private Integer teamPeopleNumber;

    @OneToMany(
            mappedBy = "member",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Member> members;


}
