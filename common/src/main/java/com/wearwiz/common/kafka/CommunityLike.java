package com.wearwiz.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityLike {

    private long postId;

    private long fromMemberId;

}
