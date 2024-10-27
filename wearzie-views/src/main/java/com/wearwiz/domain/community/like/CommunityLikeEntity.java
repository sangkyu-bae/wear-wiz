package com.wearwiz.domain.community.like;

import com.wearwiz.domain.frommember.FromMemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="communityLikeId")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_community_like") @Builder
public class CommunityLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_like_id")
    private Long communityLikeId;

    @Column(unique = true)
    private long postId;

    /**
     * 조인으로 인한 성능 하락 예상 반정규화
     * */
    private int likeCount;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "communityLike")
    private Set<FromMemberEntity> fromMembers;

    public void addFromMember(FromMemberEntity fromMember){
        if(fromMembers == null){
            fromMembers = new HashSet<>();
        }
        fromMember.setCommunityLike(this);
        fromMembers.add(fromMember);
    }


}
