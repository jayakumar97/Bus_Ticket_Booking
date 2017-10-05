package buses_and_routes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class route {
	private int route_id;
	public String start,end;
	private int temp;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet myres;
	private Statement stmt;
	public void add_route(Scanner s)
	{
		
		System.out.println("\nEnter starting point and ending point");
		start=s.next();
		end=s.next();
		
	try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
			pstmt=con.prepareStatement("insert into route(start,end) values(?,?)");
		    pstmt.setString(1,start);
		    pstmt.setString(2,end);
		    temp=pstmt.executeUpdate();
		    System.out.println(temp+" Route Added\n");
		  
		}
		catch(Exception e) {
			e.printStackTrace();
	}
	finally {
        try {
            if (con != null) {
                con.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException sqlee) {
            sqlee.printStackTrace();
        }
    }
	}
	public void delete_route(int id)
	{
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    String s1;
		    s1="Delete from route where route_id ="+String.valueOf(id);
		    temp=stmt.executeUpdate(s1);
		    System.out.println(temp+" Route deleted,bus and driver related to route also deleted\n");
		   
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	    }
	}
	public void get_route(int id)
	{
		try {
		route_id=-1;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
	    stmt=con.createStatement();
	    myres=stmt.executeQuery("select * from route");
	    System.out.println("id\tFrom\tTo");
	    while(myres.next())
	    {	
	    	route_id=myres.getInt("route_id");
	    	start=myres.getString("start");
	    	end=myres.getString("end");
	    	if(id==-1||route_id==id)
	    	System.out.println(route_id+"\t"+start+"\t"+end);
	    	
	    }
	} catch (Exception e) {
		// TODO Auto-generated catch block
			
		e.printStackTrace();
		}
		finally {
	        try {
	        	if(myres!=null)
	        	{
	        		myres.close();
	        	}
	            if (con != null) {
	                con.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	    }
	}
}
