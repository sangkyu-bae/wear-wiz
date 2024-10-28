package com.wearwiz.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncreaseViewRequest {

    private long postId;

    private long fromMemberId;

    private ViewTypeEnum viewTypeEnum;
}
