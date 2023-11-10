package com.snowlightpay.membership.adapter.out.jwt;

import com.snowlightpay.membership.application.port.out.AuthMembershipPort;
import com.snowlightpay.membership.domain.Membership;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider implements AuthMembershipPort {
    private String jwtSecret;
    private Long jwtTokenExpirationInMs;
    private Long refreshTokenExpirationInMs;

    public JwtTokenProvider() {
        this.jwtSecret = "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwK";
        this.jwtTokenExpirationInMs = 1000L * 20;
        this.refreshTokenExpirationInMs = 1000L * 60;
    }

    @Override
    public String generateJwtToken(Membership.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtTokenExpirationInMs);
        return Jwts.builder()
                .setSubject(membershipId.getMembershipId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    @Override
    public String generateRefreshToken(Membership.MembershipId membershipId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpirationInMs);
        return Jwts.builder()
                .setSubject(membershipId.getMembershipId())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    @Override
    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                    .build().parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            // 만료 시간
        } catch (UnsupportedJwtException e) {
            // 지원하지 않는 Jwt
        } catch (MalformedJwtException e) {
            // 유효하지 않는 JwtToken
        } catch (SecurityException e) {
//            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
//            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Membership.MembershipId parseMembershipIdFromToken(String jwtToken) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(jwtToken).getBody();
        return new Membership.MembershipId(claims.getSubject());
    }
}
