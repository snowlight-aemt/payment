package com.snowlightpay.membership.adapter.out.persistence;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "membership")
public class MembershipJpaEntity {
    @GeneratedValue
    @Id
    private Long membershipId;
    private String name;
    private String email;
    private String address;

    private boolean isValid;
    private boolean isCorp;
    private String refreshToken;

    public MembershipJpaEntity(String name, String email, String address, boolean isValid, boolean isCorp, String refreshToken) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;
        this.refreshToken = refreshToken;
    }

    public MembershipJpaEntity clone() {
        return new MembershipJpaEntity(this.membershipId, this.name, this.email, this.address, this.isValid, this.isCorp, this.refreshToken);
    }

    @Override
    public String toString() {
        return "MembershipJpaEntity{" +
                "membershipId=" + membershipId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isValid=" + isValid +
                ", isCorp=" + isCorp +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
