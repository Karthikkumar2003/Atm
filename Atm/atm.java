package atm;

import java.util.*;
import java.sql.*;
public class Atm {
      public static void main(String[] args) {
         try {
        	 Class.forName("com.mysql.jdbc.Driver");
        	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Atm","root","karthik12@");
        	 Statement stmt = con.createStatement();
        	 
        	 Scanner sc = new Scanner(System.in);
        	 System.out.println("Enter your pin Number");
        	 int pin = sc.nextInt();
        	 
        	 ResultSet rs=stmt.executeQuery("Select * from atm Where ac_no="+pin);
        	 String name=null;
        	 int balance=0;
        	 int count=0;
             while(rs.next()) {
            	 name=rs.getString(3);
            	 balance = rs.getInt(4);
            	 count++;
             }
        	 
           	 int choice; 
           	 int amount=0;
           	 int take_amount=0;
        	 
        	 if(count>0) {
        		System.out.println("Hello "+name);
        		while(true){
        		   System.out.println("Press 1 to check your balance");
      			   System.out.println("Press 2 to Add amount");
      			   System.out.println("Press 3 to Take Amount");
      			   System.out.println("Press 4 to Print the Recipt");
      			   System.out.println("Press 5 to Exit / Quit");
      			   
      			   System.out.println();
      			   System.out.println("Enter your Choice:");
      			   choice =sc.nextInt();
      			   switch(choice) {
      			     case 1:
      				   System.out.println("Your balance is : "+balance );
      				   break;
      			     case 2:
      			    	 System.out.println("How much amount did you want to add");
      			    	 amount = sc.nextInt();
      			    	 balance = balance+amount;
      			    	 int bal=stmt.executeUpdate("UPDATE atm SET balance = "+balance+" WHERE ac_no= " +pin);
      			    	 System.out.println("Successfully added now your curtent balance is : "+balance);
      			         break;
      			     case 3:
      			    	 System.out.println("How much amount did you want to take");
      			    	 take_amount = sc.nextInt();
      			    	 if(take_amount>balance) {
      			    		 System.out.println("Your blance is insufficient");
      			    	 }
      			    	 else {
      			    	     balance = balance - take_amount;
      			    	     int sub=stmt.executeUpdate("UPDATE atm SET balance = "+balance+" WHERE ac_no= " +pin);
      			    	     System.out.println("Successfully added now your curtent balance is : "+balance);
      			    	 }
      			    	 break;
      			     case 4:
      			    	 System.out.println("-------------------------------------");
      			    	 System.out.println("|      Indian Overseas Bank         |");
      			    	 System.out.println("-------------------------------------");
      			    	 System.out.println("Your current balance is : "+balance);
      			    	 System.out.println("Amount added            : "+amount);
      			    	 System.out.println("Amount taken            : "+take_amount);
      			    	 System.out.println("     Thanks for Coming");
      			    	 System.out.println("-------------------------------------");
      			    	 break;

      			   }
      			   if(choice==5) {
      				   break;
      			   }
      			   
        		}
        	 }
        	 else {
        		 System.err.println("Wrong pin number");
        	 }
         }   
         catch(Exception e) {
        	 System.out.println(e);
         }
        	 
       }    
}
