package com.snowlightpay.money.query.adapter.axon;

import com.snowlightpay.common.event.RequestFirmbankingEvent;
import com.snowlightpay.money.query.application.port.out.GetMemberAddressInfoPort;
import com.snowlightpay.money.query.application.port.out.InsertMoneyIncreaseEventByAddress;
import com.snowlightpay.money.query.application.port.out.MemberAddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoneyIncreaseEventHandler {
    @EventHandler
    public void handler(RequestFirmbankingEvent event,
                        GetMemberAddressInfoPort getMemberAddressInfoPort,
                        InsertMoneyIncreaseEventByAddress insertMoneyIncreaseEventByAddress) {
        System.out.println("Money Increase Event Received: "+ event.toString());


        // 고객의 주소 정보
        MemberAddressInfo memberAddressInfo = getMemberAddressInfoPort.getMemberAddressInfo(event.getMembershipId());

        // Dynamodb Insert!
        String address = memberAddressInfo.getAddress();
        int moneyIncrease = event.getMoneyAmount();
        log.info("DynamoDB Insert - " + address + " - " + moneyIncrease);

        insertMoneyIncreaseEventByAddress.insertMoneyIncreaseEventByAddress(address, moneyIncrease);
    }
}