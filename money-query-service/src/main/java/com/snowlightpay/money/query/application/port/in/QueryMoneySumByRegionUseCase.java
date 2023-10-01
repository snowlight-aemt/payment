package com.snowlightpay.money.query.application.port.in;

import com.snowlightpay.money.query.domain.MoneySumByRegion;

public interface QueryMoneySumByRegionUseCase {
    MoneySumByRegion queryMoneySumByRegion (QueryMoneySumByRegionQuery query);
}
