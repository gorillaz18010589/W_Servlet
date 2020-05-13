package text;
/*com.auth0.jwt.JWT
 *create():�^�Ǥ@�ӥΩ�إ�sign��token���غc��(�^��JWTCreator.Builder)
 *decode:(java.lang.String token)	�ѽX�A����token(�A�n�ѽX��token),����k���|���Ҧ�sign��token(�^��JWT)
 *require:(Algorithm algorithm)	�^�Ǧ��Ω�����token��sign����k���غc��(�N�n�Ω�����token��sigin)(�^��Verification)
 * */

/*com.auth0.jwt.JWTCreator.Builder
sign(Algorithm algorithm):�ΧA�������t��k�Ыؤ@��ñ��(�n�t�⪺�覡) (�^��java.lang.String)
withHeader(java.util.Map<java.lang.String,java.lang.Object> headerClaims):�K�[header(�n����key:value) (�^��JWTCreator.Builder)
withAudience(java.lang.String... audience):�K�[�S�w���[���s��(�n�K�[���[���ݩ�) (�^��JWTCreator.Builder)
withClaim(java.lang.String name, java.lang.Boolean value):�K�[�ۭq�q�����@�T��(key:value) (�^��JWTCreator.Builder)
withExpiresAt(java.util.Date expiresAt):�]�wtoken����ɶ�(�n������ɶ�) (�^��JWTCreator.Builder)
withIssuer(java.lang.String issuer):�K�[�o���(�n�K�[���o��̦W��) (�^��JWTCreator.Builder)
withIssuedAt(java.util.Date issuedAt):�K�[�o����(�o��ɶ�) (�^��JWTCreator.Builder)
withJWTId(java.lang.String jwtId):�K�[�S�w��id(�n�K�[��id) (�^��JWTCreator.Builder)
withSubject(java.lang.String subject):�K�[�S�w���D�D(�n�K�[���D�D�W��) (�^��JWTCreator.Builder)
*/

/*com.auth0.jwt.interfaces.DecodedJWT
java.lang.String	getHeader()	���otoken�����W���qheader�s�X�r��
java.lang.String	getPayload()	���otoken����Payload�s�X�r��
java.lang.String	getSignature()	���otoken�������qSign�s�X�r��
java.lang.String	getToken()	���o���Token�s�X�r��
*/

/*com.auth0.jwt.interfaces.Payload
getAudience()=>���o�������[��list<String>(�^�ǭ�java.util.List<java.lang.String>)
getClaim(java.lang.String name)=>���o���w���n��Claim����(���w��Claim�W��) (�^�ǭ�Claim)
getClaims()=>���o�Ҧ��n����key:value��Ƶ��c (�^�ǭ�java.util.Map<java.lang.String,Claim>)
getExpiresAt()=>���o����ɶ� (�^�ǭ�java.util.Date)
getId()=>���oid (�^�ǭ�java.lang.String)
getIssuedAt()=>���o�o��ɶ� (�^�ǭ�java.util.Date)
getIssuer()=>���o�o��� (�^�ǭ�java.lang.String)
getNotBefore()=>���o�w�q�b����ɶ����e,�Ӯɶ����ejwt�����i�Ϊ��ɶ� (�^�ǭ�java.util.Date)
getSubject()=>���o�D�D (�^�ǭ�java.lang.String)
*/

/*com.auth0.jwt.interfaces.Claim
asString()=>���o�n����String���(�^�ǭ�java.lang.String)
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
		
		
		//A.�Ыؤ@��JWT
		String key ="key";//4.�]�m�@�Өp�_�]�i�H��KeyProvider����
		//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
		//eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJVc2VyIiwiaXNzIjoiaGFuayIsInB1bGljIG1zZyI6ImhlbGxvIiwiZXhwIjoxNTg5NDM0NzI1LCJqdGkiOiIwMDEifQ.
		//wCGVb1EHSjcYT-7eKCKZEAmJIsM2p4n9mU_jM9IAL-g

		
		try {
			//3.ñ���һݪ��t��k
			 Algorithm alg = Algorithm.HMAC256(key);
			
			//2.�n�]�w����ɶ�
			Date currentTime = new Date();
			
			//1.�إ�Jwt
			String jwt = JWT.create()
				.withIssuer("hank")//�K�[�o���(�n�K�[���o��̦W��)
				.withSubject("userId")//�K�[�S�w���D�D(�n�K�[���D�D�W��)
				.withAudience("User")//�K�[�S�w���[���s��(�n�K�[���[���ݩ�)
				.withExpiresAt(new Date(currentTime.getTime() + 24*3600*1000L))//�]�w����ɶ��@�Ѧ��Ĵ�(�n������ɶ�)
				.withJWTId("001")//�K�[�S�w��id(�n�K�[��id)
				.withClaim("pulic msg", "hello")//�K�[�ۭq�q�����@�T��(key:value)
				.sign(alg);//�ΧA�������t��k�Ыؤ@��ñ��(�n�t�⪺�覡)(�^��String)
				System.out.println(jwt);
				
				
				//B.���oheader,payload,sign�T���s�X�覡
				//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
				//eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJVc2VyIiwiaXNzIjoiaGFuayIsInB1bGljIG1zZyI6ImhlbGxvIiwiZXhwIjoxNTg5NDM0NzI1LCJqdGkiOiIwMDEifQ.
				//wCGVb1EHSjcYT-7eKCKZEAmJIsM2p4n9mU_jM9IAL-g
				DecodedJWT decodedJWT =  JWT.decode(jwt);//
				String sHeader = decodedJWT.getHeader();
				String sPayload = decodedJWT.getPayload();
				String sSignature = decodedJWT.getSignature();
				System.out.println(sHeader +"\n" + sPayload +"\n" + sSignature +"\n");
				
				
				//C.�qdecodedJWT���o�U�ؽ�
				String subject = decodedJWT.getSubject();//���o�D�D
				Date expiresTime = decodedJWT.getExpiresAt();//���o����ɶ�
				String issuer =	decodedJWT.getIssuer();//���o�o���
				String id = decodedJWT.getId();//���oid
				System.out.println(subject +"\n" + expiresTime +"\n" + issuer +"\n" + id);

				
				//D.�qdecodedJWT���o�Ҧ�Claim����
				Map<String, Claim> claimMap = decodedJWT.getClaims();//���o�Ҧ��n����key:value��Ƶ��c
				System.out.println(claimMap);//{sub=com.auth0.jwt.impl.JsonNodeClaim@389d64cb, aud=com.auth0.jwt.impl.JsonNodeClaim@15d179e)
				Iterator<Entry<String,Claim>> iter = claimMap.entrySet().iterator();				
				while(iter.hasNext()) {
					Entry<String, Claim> entry = iter.next();
					String claimKey = entry.getKey();
					Claim claim = entry.getValue();
					String claimValue = claim.asString();//���o�n����value�r��
					System.out.println(claimKey +":" + claimValue);
				}
				
				//E.�ۤv�g���ѪRJWT��token,���oClaim�T��
				JwtUtils.parseJWTshowClaimMsg("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VySWQiLCJhdWQiOiJ1c2VyIiwiYWRkYXNkYXNAZ21haWwuY29tIjoiZm9ybWJvZHkiLCJpc3MiOiJoYW5rIiwiaWF0IjoxNTg5MzU5NDgwfQ.Ba30vId-EXk-_PRo6PgYOJDKnqIrvOp1WqRlye2FWsU");

			
				
		}catch (Exception e) {
			System.out.println("���~" +e.toString());
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	

}
