package com.codewithabhishek.Main;



import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       String url = "jdbc:mysql://localhost:3306/codewithabhishek07";
       String dbusername = "root";
       String dbpassword="abcd";

       try{


           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection(url ,dbusername , dbpassword);
           System.out.println("Connection to database");
           Scanner scanner = new Scanner(System.in);

           System.out.println("Enter the number of opertion which you want to perfrom");
           System.out.println("press 1 for  Insert Your record  ");
           System.out.println("press 2 for your records ");
           System.out.println("press 3 for update for you records ");
           System.out.println("press 4 for Delete your records ");
           int operation = scanner.nextInt();

           switch (operation) {
               case 1:
               String insert = "INSERT INTO codewithjdbc(name ,lname,ph,address) VALUES (?,?,?,?)";
               PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
               System.out.println("Enter your name");
               String name = scanner.next();
               ps.setString(1, name);
               System.out.println("Enter your Lastname");
               String lname = scanner.next();
               ps.setString(2, lname);
               System.out.println("Enter your Phone Number");
               String ph = scanner.next();
               ps.setString(3, ph);
               System.out.println("Enter your Address");
               String address = scanner.next();
               ps.setString(4, address);
               ps.execute();
               ResultSet rs = ps.getGeneratedKeys();
               rs.next();
               int id = rs.getInt(1);
               System.out.println("===============================================================================================");

               System.out.println("Your Record successfully Submitted");
               System.out.println("Your User Id is  ===" + id);


               case 2:

               System.out.println("Enter your id to get your Records ");
               int ids = scanner.nextInt();
               String showdata = " Select * from codewithjdbc where id = " + ids;
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(showdata);
               while (resultSet.next()) {
                   String id1 = resultSet.getString(1);
                   name = resultSet.getString(2);
                   lname = resultSet.getString(3);
                   ph = resultSet.getString(4);
                   address = resultSet.getString(5);

                   System.out.println("UserName" + id1 + " " + name + " " + lname + " " + " " + ph + " " + address);
               }
               System.out.println("===============================================================================================");
                    break;
               case 3:

               System.out.println("Enter your Id for update your Record");
               int idupdate = scanner.nextInt();
               String update = " Update codewithjdbc SET name = ? , lname = ? , ph =? , address= ?  WHERE id = " + idupdate;
               PreparedStatement psupdate = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
               System.out.println("Enter your name");
               name = scanner.next();
               psupdate.setString(1, name);
               System.out.println("Enter your Lastname");
               lname = scanner.next();
               psupdate.setString(2, lname);
               System.out.println("Enter your Phone Number");
               ph = scanner.next();
               psupdate.setString(3, ph);
               System.out.println("Enter your Address");
               address = scanner.next();
               psupdate.setString(4, address);
               psupdate.execute();
               ResultSet rsupdate = psupdate.getGeneratedKeys();
              if(rsupdate.next()) {
                  int idupdate1 = rsupdate.getInt(1);
                  System.out.println(" Record has been updated for user id " + idupdate1);
              }
              System.out.println("===============================================================================================");
                   break;
               case 4:
                   System.out.println("Enter your Id for delete your Record");
                   int iddelete = scanner.nextInt();
                   String delete = "DELETE FROM codewithjdbc WHERE id= " +iddelete;
                   PreparedStatement psdelete = connection.prepareStatement(delete);
                   psdelete.executeUpdate();
                   System.out.println("record has been deleted");
                   break;
               default:
                        System.out.println("invaild options");
           }


       }catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }


    }
}
