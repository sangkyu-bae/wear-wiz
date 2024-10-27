package com.wearwiz.adapter.entity;


import com.wearwiz.domain.community.view.CommunityViewEntity;
import com.wearwiz.domain.frommember.FromMemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_from_member_view",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "MEMBER_VIEW_UNIQUE",
                        columnNames = {"from_id", "community_view_id"}
                )
        })
@Builder
public class FromMemberViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FromMemberEntity fromMember;

    @JoinColumn(name = "community_view_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityViewEntity communityView;
}
