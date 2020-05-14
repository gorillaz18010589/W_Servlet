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

	/*1.�ѪRJWT���r��Token,���Claim�Ҧ���key:value�T���bLOG�W
	 * @param String jwt =>�n�Q�ѪR��JWT token
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
	
	/*2.�гy�@�ӨϥΪ�Token
	 * @param String userAccout => �ϥΪ̶ǨӪ��b��,��@playload��key
	 * @param String uerName=> �ϥΪ̶ǨӪ��W�r,��@playload��value
	 * */
	public static String createToken(String userAccount ,String userName) throws Exception  {
		
		Algorithm alg = Algorithm.HMAC256("key");
		Date date = new Date();
		
		String token = 	JWT.create()
			.withIssuer(SSU)
			.withIssuedAt(date)
			.withSubject(SUB)
			.withAudience(AUD)
			.withClaim(userAccount, userName)
			.sign(alg);
		
		return token;
		
	}
}
