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
	 * @param
	 * @param
	 * */
	public static String createToken(String userName ,String userAccout) throws Exception  {
		
		Algorithm alg = Algorithm.HMAC256("key");
		Date date = new Date();
		
		String token = 	JWT.create()
			.withIssuer("hank")
			.withIssuedAt(date)
			.withSubject("userId")
			.withAudience("user")
			.withClaim(userAccout, userName)
			.sign(alg);
		
		return token;
		
	}
}
