package com.wearwiz.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    private long toId;

    private long fromMemberId;

    private LikeTypeEnum type;

}
