package com.snowlightpay.settlement.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    private Long paymentId;
    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;              // 0 승인, 1 실패, 2 정산 완료
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
//    private LocalDateTime approvedAt;
}
