package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO(){
		try{
			String dbURL = "jdbc:mariadb://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "qwe123!@#";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void test(){
		try{
			System.out.println("Hi.");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 
	public int test2(){
		String sql = "use bbs; select * from user";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals("1234"))
					return 1;
				else
					return 0;
			}
			return -1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return -2;
	}
	
	public int login(String userID, String userPassword) {
		String sql = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(sql); //sql�������� ��� ��Ų��
			pstmt.setString(1, userID); //ù��° '?'�� �Ű������� �޾ƿ� 'userID'�� ����
			rs = pstmt.executeQuery(); //������ ������ ����� rs�� ����
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				}else
					return 0; //��й�ȣ Ʋ��
			}
			return -1; //���̵� ����
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //����
	}
	
}
