package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_portfolio") @Builder
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "portfolio", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ImageEntity> imageEntities;

    /**
     * Todo:
     * 별도 엔티티로 뺄지 정하는중
     * */
    private String license;
}
