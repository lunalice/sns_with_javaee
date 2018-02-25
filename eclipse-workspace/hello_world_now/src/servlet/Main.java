package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tubu = request.getParameter("tubu");
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		Mutter mut = new Mutter(loginUser.getName(), tubu);
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		mutterList.add(mut);
		application.setAttribute("mutterList", mutterList);

		Connection conn;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.120.120.20/tubuyaki", "tubu", "rR1QIjCd");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.37.133:1521:XE", "TUBU", "TUBU");
			// ステートメントを作成
			Statement stmt = conn.createStatement();
			// 問合せの実行つぶやき挿入
			String sql = "INSERT INTO TUBUYAKI (NAME,TUBU) VALUES('" + loginUser.getName() + "','" + tubu + "')";
			// 挿入
			stmt.executeQuery(sql);
			// コミット
			stmt.executeQuery("commit");
			// ステートメントをクローズ
			stmt.close();
			// 接続をクローズ
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		// つぶやきリストがいない場合作成する。
		if (mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}
		// セッションよりログインユーザーを確保したい。
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			// ログインユーザーいない時トップ画面にリダイレクト
			response.sendRedirect("/hello_world_now/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

}
