package speechtotext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
    String server, dbname, url, user, password;// データベースの指定
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "XXXXX.XXX.XXX.jp";
        this.dbname = "hs074";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "hs074";
        this.password = "YYYYYYYYY";
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
		
	//値の無い物全て
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `images` WHERE  `age_min` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateImage(String transcript,double confidence) {
		//keywordテーブルへ格納
		StringBuffer buf = new StringBuffer();
//		int gender_id = -1;
		//if(gender.equals("MALE")) gender_id = 0;
		//else if(gender.equals("FEMALE")) gender_id = 1;
		buf.append("INSERT INTO  `hs074`.`speeches` (`transcript` ,`confidence`) VALUES (" + transcript + ", "+ confidence +"  );");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
