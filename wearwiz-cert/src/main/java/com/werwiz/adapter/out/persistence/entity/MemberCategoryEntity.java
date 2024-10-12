package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id",callSuper = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberCategoryEntity that = (MemberCategoryEntity) o;

        if (id == null || that.id == null) {
            return member.equals(that.member) && category.equals(that.category);
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            int memberHash = (member != null) ? member.hashCode() : 0;
            int categoryHash = (category != null) ? category.hashCode() : 0;
            return memberHash + categoryHash;
        }
        return id.hashCode();
    }

    public boolean isContain(long categoryId){
        if(this.category.getId() == categoryId){
            return true;
        }

        return false;
    }

}
