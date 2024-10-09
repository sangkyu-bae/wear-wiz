package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member_category",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "MEMBER_CATEGORY_UNIQUE",
                        columnNames = {"member_id", "category_id"}
                )
        })
@Builder

public class MemberCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;


}