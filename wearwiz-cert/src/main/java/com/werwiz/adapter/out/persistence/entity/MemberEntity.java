package com.werwiz.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member") @Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String area;

    private Integer counsel;

    private Double rate;

    @Column(name = "nickName", nullable = false)
    private String nickName;

    private String introduce;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemberCategoryEntity> category = new ArrayList<>();

    @ManyToOne
    private PortfolioEntity portfolio;

}
