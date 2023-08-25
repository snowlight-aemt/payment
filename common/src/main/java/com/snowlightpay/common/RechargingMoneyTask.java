package com.snowlightpay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RechargingMoneyTask {
    private String taskID;
    private String taskName;
    private String membershipId;
    private List<SubTask> subTaskList;

    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
}


