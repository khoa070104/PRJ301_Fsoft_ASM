package com.se4f7.prj301.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtTokenUtil {

	private static final ResourceBundle rb = ResourceBundle.getBundle("application");
	private static final String JWT_SECRET = rb.getString("jwt.secretKey");
	private static final long JWT_EXPIRATION = Long.parseLong(rb.getString("jwt.expiration"));

	public static String generateToken(Long id, String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("id", id);
		claims.put("username", username);
		return Jwts.builder()
				.setSubject(Long.toString(id))
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}

	public static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

	public static boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			System.err.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.err.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.err.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.err.println("JWT claims string is empty.");
		}
		return false;
	}

	public static String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		// Kiểm tra xem header Authorization có chứa thông tin jwt không
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
