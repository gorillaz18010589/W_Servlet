package text;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GoogleSiginAccoutBean;
import job.JobDB;
import model.GsonUtils;


@WebServlet("/TextExcelWordApi")
public class TextExcelWordApi extends HttpServlet {
	String fileName ="config/g.csv";
    String tableName = " google_signin_account ";   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req, resp);
	}
	

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JobDB jobDB = new JobDB();
//		jobDB.addExcleWord(tableName, fileName, this);

		ArrayList<GoogleSiginAccoutBean> googleList = jobDB.getExcleWord();
		String json = GsonUtils.toJson(googleList);
		System.out.println(json);
	}

}
