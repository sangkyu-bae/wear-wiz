package com.wearwiz.domain.community.view;


import com.wearwiz.adapter.entity.FromMemberViewEntity;
import com.wearwiz.domain.frommember.FromMemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_community_view") @Builder
public class CommunityViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="community_view_id")
    private Long communityViewId;

    private int viewCount;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "communityView")
    private Set<FromMemberViewEntity> fromMembers;

    public void addFromMemberView(FromMemberViewEntity fromMemberViewEntity){
        fromMemberViewEntity.setCommunityView(this);

        if(fromMembers == null){
            fromMembers = new HashSet<>();
        }

        this.fromMembers.add(fromMemberViewEntity);
    }

}
