package com.wearwiz.adapter.entity;


import com.wearwiz.domain.community.CommunityEntity;
import com.wearwiz.domain.frommember.FromMemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_from_member_community",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "MEMBER_VIEW_UNIQUE",
                        columnNames = {"from_member_view_id", "community_view_id"}
                ),
                @UniqueConstraint(
                        name = "MEMBER_LIKE_UNIQUE",
                        columnNames = {"from_member_like_id","community_view_id"}
                )
        })
@Builder
public class FromMemberCommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_member_view_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FromMemberEntity viewFromMember;

    @JoinColumn(name = "from_member_like_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FromMemberEntity likeFromMember;

    @JoinColumn(name = "community_view_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityEntity communityView;

}
