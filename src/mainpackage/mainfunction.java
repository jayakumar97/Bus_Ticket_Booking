package mainpackage;
import java.util.Scanner;

import buses_and_routes.bus;
import buses_and_routes.route;
import userdetails.*;
public class mainfunction {
	
	private static String admin_name="admin",admin_pwd="admin";
    private static String username,pwd;
	private static int choice;
	public static int temp;
	public static void admin_funcs(Scanner s,customer c,driver d)
	{
		int choice=0;
		  int id;
		String ss;
		int temp2;
		route r=new route();
		bus b=new bus();
		while(choice!=9)
		{
		System.out.println("\n1.delete customer\n2.cancel booking\n3.add driver\n4.delete driver\n5.add route\n6.delete route\n7.add bus\n8.remove bus\n9.exit\n");
		choice=s.nextInt();
		if(choice==1)
			{ 
				c.get_customer_details(-1);
				System.out.println("Delete by \n1.id\n2.name");
				choice=s.nextInt();
				if(choice==1)
				{
					id=s.nextInt();
					c.delete_customer(id," ");
				}
				else if(choice==2)
				{
					ss=s.next();
					c.delete_customer(-1, ss);
				}
			}
		else if(choice==2)
		{
			c.get_bookings(-1, -1);
			System.out.println("enter the bookingid to cancel");
			temp2=s.nextInt();
			c.delete_booking(temp2);
		}
		else if(choice==3)
		{	
			b.get_bus(-1);
			d.add_driver(s);
		}
		else if(choice==4)
		{
			d.get_driver_details(-1);
			System.out.println("delete driver by \n1.id\n2.name");
			choice=s.nextInt();
			if(choice==1)
			{
				id=s.nextInt();
				d.delete_driver(id," ");
			}
			else if(choice==2)
			{
				ss=s.next();
				d.delete_driver(-1, ss);
			}
			
		}
		else if(choice==5)
		{
			r.add_route(s);
		}
		else if(choice==6)
		{
			r.get_route(-1);
			System.out.println("Enter route id to delete");
			id=s.nextInt();
			r.delete_route(id);
		}
		else if(choice==7)
		{
			System.out.println("routes");
			r.get_route(-1);
			b.add_bus(s);
		}
		else if(choice==8)
		{
			b.get_bus(-1);
			System.out.println("enter bus_id to delete");
			temp2=s.nextInt();
			b.delete_bus(temp2);
			
		}
	}
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
	try{
		Scanner s=new Scanner(System.in);
	while(choice!=3) {
		int trigger=0;
	System.out.println("\n1.Login\n2.Registration\n3.Exit\nEnter choice");	
	choice=s.nextInt();
	driver d=new driver();
	customer c=new customer();
		if(choice==1)
		{	temp=0;
			System.out.println("Enter Username and password");
			username=s.next();
			pwd=s.next();
			
			if((username.equals(admin_name))&&(pwd.equals(admin_pwd)))
				{
				trigger=1;
				admin_funcs(s,c,d);
				}
				
			temp=d.is_driver_there(username, pwd);
			if(temp!=0)
			{	
				trigger=1;
				d.get_driver_details(temp);
				
			}
			temp=c.is_customer_there(username, pwd);
			if(temp!=0)
			{int temp2;
				trigger=1;
				c.get_customer_details(temp);
				System.out.println("\n1.Book ticket\n2.cancel booking\n enter your choice");
				temp2=s.nextInt();
				if(temp2==1)
				c.book_ticket(s, temp);
				else if(temp2==2)
				{
					c.get_bookings(temp, -1);
					System.out.println("enter the bookingid to cancel");
					temp2=s.nextInt();
					c.delete_booking(temp2);
				}
			}
			if(trigger==0)
			{
				System.out.println("NO user found");
			}
		}
		else if(choice==2)
		{
			c.customer_registration(s);
		}
	}
	s.close();
	System.out.println("Thank You");
		}
			catch(Exception e)
			{
				System.out.println(e);
			}
    
	}
  }

