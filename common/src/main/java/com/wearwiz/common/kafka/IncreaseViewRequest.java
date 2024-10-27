package com.wearwiz.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseViewRequest {

    private long postId;

    private long fromMemberId;

    private ViewTypeEnum viewTypeEnum;
}
