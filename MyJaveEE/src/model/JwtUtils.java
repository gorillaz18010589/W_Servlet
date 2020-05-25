package model;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.el.parser.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;

public class JwtUtils {
	private static String SSU ="wexlu";
	private static String SUB ="userId";
	private static String AUD ="user";

	/*1.解析token取得資訊的name跟value
	 * @param String jwt =>要解析的token
	 * return => 回傳token取得資訊的name跟value
	 */
	public static void parseJWTshowClaimMsg(String jwt) {
		Map<String, Claim> claimsMap = JWT.decode(jwt).getClaims();
		Iterator<Entry<String, Claim>> iterator = claimsMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Claim> entry = iterator.next();
			String claimKey = entry.getKey();
			Claim claim = entry.getValue();
			String claimValue = claim.asString();
			System.out.println(claimKey + ":" + claimValue);
		}
	}
	
	/*2.創造JWT_Token
	 * @param String userAccout => 1.使用者帳號當主key
	 * @param String uerName=> 2.使用者HashCode
	 * retun => 回傳用Client帳號跟hashCode產生的Token
	 * */
	public static String createToken(String userAccount ,String hashCode) throws Exception  {
		
		Algorithm alg = Algorithm.HMAC256("key");
		Date date = new Date();
		
		String token = 	JWT.create()
			.withIssuer(SSU)
			.withIssuedAt(date)
			.withSubject(SUB)
			.withAudience(AUD)
			.withClaim(userAccount,hashCode)
			.sign(alg);
		
		return token;
		
	}
}
