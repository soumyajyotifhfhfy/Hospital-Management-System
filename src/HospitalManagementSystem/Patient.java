package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;
public Patient(Connection connection, Scanner scanner){
    this.connection=connection;
    this.scanner=scanner;
}
public void addPatient(){
    System.out.print("Enter patient name :");
    String name=scanner.next();
    System.out.print("Enter patient age :");
    int age=scanner.nextInt();
    System.out.print("Enter patient gender :");
    String gender=scanner.next();

    try{
        String query="INSERT INTO patient(name,age,gender) VALUES(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.setString(3, gender);
        int affectedRows=preparedStatement.executeUpdate();
        if(affectedRows>0)
            System.out.println("Patient added successfully!!");
        else
            System.out.println("Patient not added successfully!!");
    }
    catch(SQLException e){
        //System.out.println(e);
        e.printStackTrace();
    }

    }
public void viewPatients(){
    String query="SELECT * FROM patients";
    try{
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet= preparedStatement.executeQuery();
        System.out.println("Patients: ");
        System.out.println("+------------+--------------------+----------+------------+");
        System.out.println("| Patient Id | Name               | Age      | Gender     |");
        System.out.println("+------------+--------------------+----------+------------+");
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            int age=resultSet.getInt("age");
            String gender=resultSet.getString("gender");
            System.out.printf("|%-12s|%-20s|%-10s|%-12s");
            System.out.println("+------------+--------------------+----------+------------+");
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
}
public boolean getPatientById(int id){
    String query="SELECT * FROM PATIENTS WHERE ID =?";
    try{
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet= preparedStatement.executeQuery();
        return resultSet.next();
    }
    catch(SQLException e){
        e.printStackTrace();
    } return false;
}


}