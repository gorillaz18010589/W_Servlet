package text;
/*com.auth0.jwt.JWT
 *create():回傳一個用於建立sign跟token的建構器(回傳JWTCreator.Builder)
 *decode:(java.lang.String token)	解碼你給的token(你要解碼的token),此方法不會驗證有sign的token(回傳JWT)
 *require:(Algorithm algorithm)	回傳有用於驗證token跟sign的算法的建構器(將要用於驗證token的sigin)(回傳Verification)
 * */

/*com.auth0.jwt.JWTCreator.Builder
sign(Algorithm algorithm):用你給予的演算法創建一個簽章(要演算的方式) (回傳java.lang.String)
withHeader(java.util.Map<java.lang.String,java.lang.Object> headerClaims):添加header(要給的key:value) (回傳JWTCreator.Builder)
withAudience(java.lang.String... audience):添加特定的觀眾群體(要添加的觀眾屬性) (回傳JWTCreator.Builder)
withClaim(java.lang.String name, java.lang.Boolean value):添加自訂義的公共訊息(key:value) (回傳JWTCreator.Builder)
withExpiresAt(java.util.Date expiresAt):設定token到期時間(要到期的時間) (回傳JWTCreator.Builder)
withIssuer(java.lang.String issuer):添加發行者(要添加的發行者名稱) (回傳JWTCreator.Builder)
withIssuedAt(java.util.Date issuedAt):添加發行日期(發行時間) (回傳JWTCreator.Builder)
withJWTId(java.lang.String jwtId):添加特定的id(要添加的id) (回傳JWTCreator.Builder)
withSubject(java.lang.String subject):添加特定的主題(要添加的主題名稱) (回傳JWTCreator.Builder)
*/

/*com.auth0.jwt.interfaces.DecodedJWT
java.lang.String	getHeader()	取得token中的上部段header編碼字串
java.lang.String	getPayload()	取得token中的Payload編碼字串
java.lang.String	getSignature()	取得token中的尾段Sign編碼字串
java.lang.String	getToken()	取得整串Token編碼字串
*/

/*com.auth0.jwt.interfaces.Payload
getAudience()=>取得接收的觀眾list<String>(回傳值java.util.List<java.lang.String>)
getClaim(java.lang.String name)=>取得指定的聲明Claim物件(指定的Claim名稱) (回傳值Claim)
getClaims()=>取得所有聲明的key:value資料結構 (回傳值java.util.Map<java.lang.String,Claim>)
getExpiresAt()=>取得到期時間 (回傳值java.util.Date)
getId()=>取得id (回傳值java.lang.String)
getIssuedAt()=>取得發行時間 (回傳值java.util.Date)
getIssuer()=>取得發行者 (回傳值java.lang.String)
getNotBefore()=>取得定義在什麼時間之前,該時間之前jwt都不可用的時間 (回傳值java.util.Date)
getSubject()=>取得主題 (回傳值java.lang.String)
*/

/*com.auth0.jwt.interfaces.Claim
asString()=>取得聲明的String資料(回傳值java.lang.String)
*/


import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Mac;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.sun.org.apache.bcel.internal.generic.ALOAD;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import model.JwtUtils;

@WebServlet("/T2_JWT_Token")
public class T2_JWT_Token extends HttpServlet {
       
 
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.service(req, resp);
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//A.創建一個JWT
		String key ="key";//4.設置一個私鑰也可以用KeyProvider產生
		//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
		//eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJVc2VyIiwiaXNzIjoiaGFuayIsInB1bGljIG1zZyI6ImhlbGxvIiwiZXhwIjoxNTg5NDM0NzI1LCJqdGkiOiIwMDEifQ.
		//wCGVb1EHSjcYT-7eKCKZEAmJIsM2p4n9mU_jM9IAL-g

		
		try {
			//3.簽章所需的演算法
			 Algorithm alg = Algorithm.HMAC256(key);
			
			//2.要設定到期時間
			Date currentTime = new Date();
			
			//1.建立Jwt
			String jwt = JWT.create()
				.withIssuer("hank")//添加發行者(要添加的發行者名稱)
				.withSubject("userId")//添加特定的主題(要添加的主題名稱)
				.withAudience("User")//添加特定的觀眾群體(要添加的觀眾屬性)
				.withExpiresAt(new Date(currentTime.getTime() + 24*3600*1000L))//設定到期時間一天有效期(要到期的時間)
				.withJWTId("001")//添加特定的id(要添加的id)
				.withClaim("pulic msg", "hello")//添加自訂義的公共訊息(key:value)
				.sign(alg);//用你給予的演算法創建一個簽章(要演算的方式)(回傳String)
				System.out.println(jwt);
				
				
				//B.取得header,payload,sign三中編碼方式
				//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
				//eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJVc2VyIiwiaXNzIjoiaGFuayIsInB1bGljIG1zZyI6ImhlbGxvIiwiZXhwIjoxNTg5NDM0NzI1LCJqdGkiOiIwMDEifQ.
				//wCGVb1EHSjcYT-7eKCKZEAmJIsM2p4n9mU_jM9IAL-g
				DecodedJWT decodedJWT =  JWT.decode(jwt);//
				String sHeader = decodedJWT.getHeader();
				String sPayload = decodedJWT.getPayload();
				String sSignature = decodedJWT.getSignature();
				System.out.println(sHeader +"\n" + sPayload +"\n" + sSignature +"\n");
				
				
				//C.從decodedJWT取得各種質
				String subject = decodedJWT.getSubject();//取得主題
				Date expiresTime = decodedJWT.getExpiresAt();//取得到期時間
				String issuer =	decodedJWT.getIssuer();//取得發行者
				String id = decodedJWT.getId();//取得id
				System.out.println(subject +"\n" + expiresTime +"\n" + issuer +"\n" + id);

				
				//D.從decodedJWT取得所有Claim的質
				Map<String, Claim> claimMap = decodedJWT.getClaims();//取得所有聲明的key:value資料結構
				System.out.println(claimMap);//{sub=com.auth0.jwt.impl.JsonNodeClaim@389d64cb, aud=com.auth0.jwt.impl.JsonNodeClaim@15d179e)
				Iterator<Entry<String,Claim>> iter = claimMap.entrySet().iterator();				
				while(iter.hasNext()) {
					Entry<String, Claim> entry = iter.next();
					String claimKey = entry.getKey();
					Claim claim = entry.getValue();
					String claimValue = claim.asString();//取得聲明的value字串
					System.out.println(claimKey +":" + claimValue);
				}
				
				//E.自己寫的解析JWT的token,取得Claim訊息
				JwtUtils.parseJWTshowClaimMsg("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJ1c2VyIiwiYWRkYXNkYXNAZ21haWwuY29tIjoiZm9ybWJvZHkiLCJpc3MiOiJoYW5rIiwiaWF0IjoxNTg5MzU5NDgwfQ.Ba30vId-EXk-_PRo6PgYOJDKnqIrvOp1WqRlye2FWsU");

			
				
		}catch (Exception e) {
			System.out.println("錯誤" +e.toString());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	

}
