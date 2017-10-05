package userdetails;
import java.sql.*;
import java.util.Scanner;
import buses_and_routes.*;

interface bookings
{
	public  void book(Scanner s,int bus_id);
	  public void get_bookings(int id,int bookingid);
	}

public class customer extends user implements bookings{
	private int booking_id,bus_id,no_of_tickets,temp;
	private String date;
	public int customer_id;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private Statement stmt=null;
	private ResultSet myres=null;
	public void customer_registration(Scanner s) 
	{	
		
		System.out.println("enter name,gender,age,username,pwd");
		getdetails(s);
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    
		    pstmt=con.prepareStatement("insert into customer(name,gender,age,username,pwd) values(?,?,?,?,?)");
		    pstmt.setString(1,name);
		    pstmt.setString(2,gender);
		    pstmt.setInt(3,age);
		    pstmt.setString(4,username);
		    pstmt.setString(5, pwd);
		    pstmt.executeUpdate();
		    System.out.println("\nRegistration successful\n");
		  
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
	public void get_customer_details(int id)
	{ 
		try {customer_id=-1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    myres=stmt.executeQuery("select * from customer");
		    System.out.println("\nid\tName\tAge\tGender\tUsername\tPwd");
		    while(myres.next())
		    {	
		    	customer_id=myres.getInt("customer_id");
		    	age=myres.getInt("age");
		    	name=myres.getString("name");
		    	gender=myres.getString("gender");
		    	username=myres.getString("username");
		    	pwd=myres.getString("pwd");
		    	if(id==-1||customer_id==id)
		    	System.out.println(customer_id+"\t"+name+"\t"+age+"\t"+gender+"\t"+username+"\t"+pwd);
		    	
		    }
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
	public int delete_customer(int id,String s)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    String s1;
		    
		    if(id!=-1)
		    s1="Delete from customer where customer_id ="+String.valueOf(id);
		    else if(s!=" ")
		    s1="Delete from customer where name ="+"\""+s+"\"";
		    else 
		    	return 0;
		    temp=stmt.executeUpdate(s1);
		   System.out.println(temp+" customer data with bookings are deleted\n");
		   return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
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
	public int is_customer_there(String u,String p)
	{ 
		try {customer_id=-1;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    stmt=con.createStatement();
		    myres=stmt.executeQuery("select * from customer");
		   
		    while(myres.next())
		    {	
		    	
		    	customer_id=myres.getInt("customer_id");
		    	age=myres.getInt("age");
		    	name=myres.getString("name");
		    	gender=myres.getString("gender");
		    	username=myres.getString("username");
		    	pwd=myres.getString("pwd");
		 
		    	if(u.equals(username)&&p.equals(pwd))
		    		{
		    		
		    		return customer_id;}
		    	
		    	
		    }
		   
		    return 0;
			} catch (Exception e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return 0;
			}
		finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if(myres!=null)
	            	myres.close();
	        } catch (SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	    }
	
	}
	public void book(Scanner s,int busid)
	{
		bus_id=busid;
		System.out.println("Enter no of tickets");
		no_of_tickets=s.nextInt();
		System.out.println("Enter Date of journey(dd-mm-yyyy)");
		date=s.next();
		while(!date.matches("\\d{2}[-]\\d{2}[-]\\d{4}")) 
		{System.out.println("Enter Date of journey(dd-mm-yyyy)");
		date=s.next();
		}
	try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
		    System.out.println("here");
		    pstmt=con.prepareStatement("insert into bookings(customer_id,bus_id,no_of_tickets,journey_date) values(?,?,?,?)");
		    pstmt.setInt(1,customer_id);
		    pstmt.setInt(2,bus_id);
		    pstmt.setInt(3,no_of_tickets);
		    pstmt.setString(4,date);
		    temp=pstmt.executeUpdate();
		    System.out.println("\n"+temp+" successfully booked\n");
		  
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
	public void get_bookings(int id,int bookingid)
	{
		try {int temp_id=-1;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
	    stmt=con.createStatement();
	    myres=stmt.executeQuery("select * from bookings");
	    System.out.println("name\tbooking_id\tbus_id\tno_of_tickets\tTotale_Price\tFrom\tTo");
	    while(myres.next())
	    {	
	    	temp_id=myres.getInt("customer_id");
	    	booking_id=myres.getInt("booking_id");
	    	bus_id=myres.getInt("bus_id");
	    	no_of_tickets=myres.getInt("no_of_tickets");
	    	date=myres.getString("journey_date");
	    	
	    	if(id==-1||temp_id==id||bookingid==booking_id)
	    	{
	    		bus b=new bus();
	    		b.get_bus(bus_id);
	    		route r=new route();
	    		r.get_route(b.route_id);
	    		System.out.println(name+"\t"+booking_id+"\t"+bus_id+"\t"+no_of_tickets+"\t"+no_of_tickets*b.fare+"\t"+r.start+"\t"+r.end);
	    	}
	    	
	    }
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
	public void delete_booking(int bookingid)
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busbooking","root","root");
	    stmt=con.createStatement();
	    String s1="Delete from bookings where booking_id ="+String.valueOf(bookingid);
	    temp=stmt.executeUpdate(s1);
	   System.out.println("\n"+temp+" booking deleted\n");
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
	public void book_ticket(Scanner s,int customerid)
	{
		
	route r=new route();
	r.get_route(-1);
	int temp;
	System.out.println("enter route id");
	temp=s.nextInt();
	bus b=new bus();
	
	int temp2=b.get_busid_by_routeid(temp);
	if(temp2==-1)
		System.out.println("routeid not found or bus not found\n");
	else if(temp2!=-1)
	book(s,temp2);
	get_bookings(customerid,-1);
	}
}
    
  