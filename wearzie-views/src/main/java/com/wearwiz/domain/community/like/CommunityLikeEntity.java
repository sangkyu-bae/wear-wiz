//package com.wearwiz.domain.community.like;
//
//import com.wearwiz.adapter.entity.FromMemberViewEntity;
//import com.wearwiz.domain.frommember.FromMemberEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@EqualsAndHashCode(of="communityLikeId")
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "tb_community_like") @Builder
//public class CommunityLikeEntity {
//
//    @Id
//    @Column(name = "community_like_id")
//    private Long communityLikeId;
//
//    private int likeCount;
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "communityLike")
//    private Set<FromMemberViewEntity> fromMembers;
//
//}
