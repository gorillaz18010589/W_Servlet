package text;
//https://blog.csdn.net/luckey_zh/article/details/61197587
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.protocol.Resultset.Type;

import jdk.nashorn.internal.parser.JSONParser;
import model.DB;
import model.JwtUtils;
import model.ResultWriter;
import netscape.javascript.JSObject;


@WebServlet("/T1_formBody")
public class T1_formBody extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doTask()");
		
		
		//�]�w�n��clent�ݪ���X�榡
		response.setContentType("application/json, charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		//4.�]�m�@�Өp�_�]�i�H��KeyProvider����
		String key ="key";
		
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
		
		//��Ʈw�s��
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			properties.put("user", "root");
			properties.put("password","root");
			properties.put("serverTimezone","Asia/Taipei");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/iii", properties);
			
			//���������Ѽ�form����k,��getParameter,���oAS�ǨӰѼ�
			String name = request.getParameter("name");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String localeName = request.getLocalName();
			System.out.println("�ϥΪ̦���J:" +"name:" + name + "/account:" + account +"/password:" + password +"localeName:" + localeName);
			
			
			//�N���o�ѼƷs�W��DB
			String sql ="INSERT INTO user(name, account, password) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, account);
			preparedStatement.setString(3, password);
			int count = preparedStatement.executeUpdate();
			if(count == 1) {
			
//				//1.�ǵ�Client����Ƶ��c�� =>{"msg":["OK"]}
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.append("msg", "OK");
//				printWriter.append(jsonObject.toString());
//				
//				//2..�ǵ�Client����Ƶ��c�� =>{"msg:OK"}
//				Gson gson = new Gson();
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				String jsonMsg = gson.toJson(map);
//				System.out.println(jsonMsg);
//				printWriter.write(jsonMsg);
				
				
				//3.�ۤv�g��API�Nresponse��msg��Json�榡�e�X
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				ResultWriter.write(response, map);
				
				//4.�ۤv�g�������ǰe{"msg":"OK"}��k
//				ResultWriter.writeOk(response);
				
				//5.�ǰetoken��Clent��
				Map<String, String> map = new HashMap<>();
				map.put("token", JwtUtils.createToken(name, account));
				ResultWriter.write(response, map);
				
			}
			printWriter.flush();//���n���M�|�|�T��
//			System.out.println("DB insert���\:" + count +"��");
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
}
