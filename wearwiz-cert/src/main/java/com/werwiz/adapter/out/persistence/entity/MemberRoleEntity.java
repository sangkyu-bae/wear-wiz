package com.werwiz.adapter.out.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member_role",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "MEMBER_ROLE_UNIQUE",
                        columnNames = {"member_id", "role_id"}
                )
        })
@Builder
public class MemberRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RoleEntity role;

}
