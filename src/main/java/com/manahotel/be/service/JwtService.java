package com.manahotel.be.service;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.model.entity.Token;
import com.manahotel.be.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
@RequiredArgsConstructor
@Service
public class JwtService {

    @Autowired
    private final TokenRepository tokenRepository;
    private static final String SECRET_KEY = "60a3f06f089e0c4afe33e71941c1a8d80d54219bcb1d5aa85728bbdaff030c85";
    private static final long JWT_EXPIRATION = 60 * 60 * 24 * 1000; // 1 hour

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Staff userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            Staff userDetails
    ) {
        return buildToken(extraClaims, userDetails);
    }

    public String generateRefreshToken(
            Staff userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            Staff userDetails

    ) {
        extraClaims.put("staff_id", userDetails.getStaffId());
        extraClaims.put("role", userDetails.getRole());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public void createTokenForUser(Staff staff, String passwordToken) {
        Token passwordRestToken = new Token(passwordToken, staff);
        tokenRepository.save(passwordRestToken);
    }
    public void revokeAllUserTokens(Staff staff) {
        var validUserTokens = tokenRepository.findByStaff(staff);
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpirationTime(new Date(80,1,1));
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public String validatePasswordResetToken(String theToken){
        Token passwordToken = tokenRepository.findByToken(theToken);
        if(passwordToken == null){
            return "Invalid password reset token";
        }
        Staff staff = passwordToken.getStaff();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Link already expired, resend link";
        }
        return "valid";

    }
    public Optional<Staff> findStaffByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(tokenRepository.findByToken(passwordResetToken).getStaff());
    }

}
