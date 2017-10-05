package userdetails;
import java.util.Scanner;

public class user {
protected int age;
protected String name,gender,username,pwd;
public void getdetails(Scanner s)
{
		
	name=s.next();
	gender=s.next();
	age=s.nextInt();
	username=s.next();
	pwd=s.next();
	
	
}
}