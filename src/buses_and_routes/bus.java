package buses_and_routes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class bus {
	private int bus_id,temp;
	public int route_id;
	private int total_seat;
	private String reg_no,bus_type;
	public float fare;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet myres;
	private Statement stmt;
	public void add_bus(Scanner b)
	{
		
		System.out.println("\nEnter reg_no,bus_type,fare,total_seat,route_id\n");
		reg_no=b.next();
		bus_type=b.next();
		fare=b.nextFloat();
		total_seat=b.nextInt();
		route_id=b.nextInt();
		
	try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		 
		    pstmt=con.prepareStatement("insert into bus(reg_no,bus_type,fare,total_seat,route_id) values(?,?,?,?,?)");
		    pstmt.setString(1,reg_no);
		    pstmt.setString(2,bus_type);
		    pstmt.setFloat(3,fare);
		    pstmt.setInt(4,total_seat);
		    pstmt.setInt(5,route_id);
		    temp=pstmt.executeUpdate();
		    System.out.println(temp+" Bus Added\n");
		  
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
	public void get_bus(int id)
	{
		try {
		bus_id=-1;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
	    stmt=con.createStatement();
	    myres=stmt.executeQuery("select * from bus");
	    System.out.println("\nBus_id\tRegno\tBus_type\tFare_per_ticket\tTotal_seat\tRoute_id");
	    while(myres.next())
	    {	
	    	route_id=myres.getInt("route_id");
	    	bus_id=myres.getInt("bus_id");
	    	reg_no=myres.getString("reg_no");
	    	bus_type=myres.getString("bus_type");
	    	fare=myres.getFloat("fare");
	    	total_seat=myres.getInt("total_seat");
	    	if(id==-1||bus_id==id)
	    	System.out.println(bus_id+"\t"+reg_no+"\t"+bus_type+"\t"+fare+"\t"+total_seat+"\t"+route_id);
	    	
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
	public void delete_bus(int id)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    String s1;
		    
		    s1="Delete from bus where bus_id ="+String.valueOf(id);
		    
		    temp=stmt.executeUpdate(s1);
		    System.out.println(temp+" bus deleted\n");
		   
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
	public int get_busid_by_routeid(int id)
	{	
		
		try {
			bus_id=-1;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    myres=stmt.executeQuery("select * from bus");
		    while(myres.next())
		    {	
		    	route_id=myres.getInt("route_id");
		    	bus_id=myres.getInt("bus_id");
		    	reg_no=myres.getString("reg_no");
		    	bus_type=myres.getString("bus_type");
		    	fare=myres.getFloat("fare");
		    	total_seat=myres.getInt("total_seat");
		    	if(route_id==id)
		    	{	System.out.println("here");
		    		con.close();
		    		stmt.close();
		    		myres.close();
		    		return bus_id;
		    	}
		    }
		    return (-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return (-1);	
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
	
}
