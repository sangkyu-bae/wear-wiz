package com.wearwiz.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Like {

    private long toId;

    private long fromMemberId;

    private LikeTypeEnum type;

}
