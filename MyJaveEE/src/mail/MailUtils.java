package mail;
//1.static Message.RecipientType	BCC	秘密送信給誰
//2.tatic Message.RecipientType	CC	CC_email
//3.static Message.RecipientType	TO	TO_email給誰
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	private static MailUtils instance;
	private static final String SMTP_HOST = "smtp.gmail.com";  //設置mail主機為gmail
	private static final String SMTP_PORT ="587"; //接收port 587
	private static final String EMAIL_PASSWORD ="049310528"; //寄件人密碼
	
	public static MailUtils getInstance() {
		if(instance == null) {
			instance = new MailUtils();
		}
		return instance;
	}
	
	public MailUtils() {
		
	}
	
	/*1.使用者接註冊api後送出email信件
	 * @param String userEmail => 1.要接收註冊信件的email地址
	 * @param String hashCode =>  2.當註冊時產生的hashCode瑪
	 * */
	public void sendRegisterEmail(String userEmail, String hashCode) {
	
		//1.準備props,帶一些mail需要共享的參數,設定mail主機,auth,port等
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", SMTP_HOST);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", SMTP_PORT);
		
		//2.創建一個可以讓資訊跟對方共享的Session物件(1.要分享的資訊,2.email的Authenticator認證者)
		Session session = Session.getDefaultInstance(properties, new Authenticator() {		
			
		//3.當使用者送出帳號密碼登入信箱時 =>實作getPasswordAuthentication 取得PasswordAuthentication
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println("getPasswordAuthentication()");
				return new PasswordAuthentication(userEmail,EMAIL_PASSWORD); //抽象方法取得密碼使用者物件()
			}
		});
		
		//4.取得MimeMessage設定email訊息資料
		MimeMessage mimeMessage = new MimeMessage(session);//依你傳來的Message.Sourse參數產生一個Message物件(訊息session)
		
	
		try {
			//5.設定email,收件者,寄件者
			mimeMessage.setFrom(new InternetAddress(userEmail)); //設定寄件者從(email地址物件)
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail)); //新增收件者(1.收件類型,2.收件地址)
		
			
//			mimeMessage.setText("這是Text內容"); //設定emailText內容
//			mimeMessage.setContent(EMAIL_CONTENT, "text/html;charset=UTF-8");//設定email正是內容可以加上contentType(1,email內容,2.contenType)
			
			//6.設定email內容
			mimeMessage.setSubject("這是一封測式信subJect主題"); //設定email標題
			mimeMessage.setText("http://localhost:8080/MyJaveEE/WexLu?key1=" + userEmail +"&key2=" + hashCode +"&page=mymail");

			
			//7.運輸發送信件
			Transport.send(mimeMessage);//運輸發送訊息(你要發送的訊息物件)
			System.out.println("sendRegisterEmail成功發送()=>" + "userEmail:" + userEmail +"/hashCode:" + hashCode);
			
			
		} catch (Exception  e) {
			System.out.println("發信失敗:" + e.toString());
		
		}
		
	}
	
	
	
}
