package userdetails;
import java.sql.*;
import java.util.Scanner;

 
  public class driver extends user{
	private float salary;
	private int bus_id,temp;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet myres;
	private Statement stmt;
	public void add_driver(Scanner s)
	{	
		
		System.out.println("enter name,gender,age,username,pwd,salary,bus_id");
		getdetails(s);
		System.out.println("here");
		
		
		salary=s.nextFloat();
		bus_id=s.nextInt();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    System.out.println("here");
		    pstmt=con.prepareStatement("insert into driver(name,age,salary,username,pwd,bus_id,gender) values(?,?,?,?,?,?,?)");
		    
		    pstmt.setString(1,name);
		    pstmt.setInt(2,age);
		    pstmt.setFloat(3,salary);
		    pstmt.setString(4,username);
		    pstmt.setString(5, pwd);
		    pstmt.setInt(6, bus_id);
		    pstmt.setString(7,gender);
		    temp=pstmt.executeUpdate();
		    System.out.println(temp+" Driver Added\n");
		   
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
	public void get_driver_details(int id)
	{ 
		try {int driver_id=-1;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    myres=stmt.executeQuery("select * from driver");
		    System.out.println("\nDriver_id\tName\tAge\tGender\tSalary\tUsername\tPwd");
		    while(myres.next())
		    {	
		    	driver_id=myres.getInt("driver_id");
		    	age=myres.getInt("age");
		    	name=myres.getString("name");
		    	bus_id=myres.getInt("bus_id");
		    	salary=myres.getFloat("salary");
		    	gender=myres.getString("gender");
		    	username=myres.getString("username");
		    	pwd=myres.getString("pwd");
		    	if(id==-1||driver_id==id)
		    	System.out.println(driver_id+"\t"+name+"\t"+age+"\t"+gender+"\t"+salary+"\t"+username+"\t"+pwd);
		    	
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
	public void delete_driver(int id,String s)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    String s1;
		    if(id!=-1)
		    s1="Delete from driver where driver_id ="+String.valueOf(id);
		    else
		    s1="Delete from driver where name ="+"\""+s+"\"";
		    
		    temp=stmt.executeUpdate(s1);
		   System.out.println(temp+" Driver data deleted\n");
		  
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
	public int is_driver_there(String u,String p)
	{ 
		try {int driver_id=-1;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    ResultSet myres=stmt.executeQuery("select * from driver");
		    while(myres.next())
		    {	
		    	driver_id=myres.getInt("driver_id");
		    	username=myres.getString("username");
		    	pwd=myres.getString("pwd");
		    	if(u.equals(username)&& p.equals(pwd))
		    	return driver_id;
		    	
		    }
		  

		    return 0;
			} catch (Exception e) {
			// TODO Auto-generated catch block
				
			e.printStackTrace();
			return 0;
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
    
  