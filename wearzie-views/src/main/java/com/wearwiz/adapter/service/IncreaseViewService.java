package com.wearwiz.adapter.service;

import com.wearwiz.common.kafka.IncreaseViewRequest;

public interface IncreaseViewService {

    void increaseView(IncreaseViewRequest request);
}
