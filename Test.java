import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test 
{

	public static void main(String[] args)
	{
		Test pro = new Test();
		pro.createConenction();
	}
	
	void createConenction()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//sql url: jdbc:mysql://localhost:3306/cs3560
			//user name: root
			//password: bruh
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560", "root", "bruh");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE Description = 'Pain Reliever'");
			while(rs.next())
			{
				String name = rs.getString("Description");
				System.out.println(name);
			}
			
			System.out.println("Database connection success");
		} 
		catch (ClassNotFoundException ex)
		{
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
//update idk
