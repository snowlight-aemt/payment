package com.snowlightpay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubTask {
    private String membershipId;
    private String subTypeName;
    private String taskType; // booking membership
    private String status; // success 성공 , fail 실패 , ready 준비
}
