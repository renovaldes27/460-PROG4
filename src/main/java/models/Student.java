package models;

import java.util.*;
import java.sql.*;

public class Student 
{
    private static int nextStudentId;
    private static boolean isInitialized;
    public int id;
    public String name;
    public String address;
    public String phone;
    public String email;
    public String gender;
    public String dob;
    public String category;
    public String major;
    public String minor;
    public int advisorID;

    public void add(Statement statement)
    {
        try
        {
            if(isInitialized == false)
            {
                isInitialized = true;
                // get the max id that was used in the database
                ResultSet answer = statement.executeQuery("select max(id) from student");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextStudentId = answer.getInt("id");
                }
            }

            statement.execute("insert into isaacp.student values('" + nextStudentId +     
            "', '" + name +
            "', '" + address +
            "', '" + phone +
            "', '" + email +
            "', '" + gender +
            "', '" + dob + // TODO This will not be formatted correctly
            "', '" + category +
            "', '" + major +
            "', '" + minor +
            "', '" + advisorID + 
            "' )");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new student. " + e.getMessage());
        }
        nextStudentId++;
    }

    public void update(Statement statement)
    {
        try
        {
            statement.execute("update student set name = " + name + " where id = " + id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't update student, id = " + id + "." + e.getMessage());
        }  
    }

    public void delete(Statement statement)
    {
        try
        {
            statement.execute("delete from student where id = " + id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't remove student, id = " + id + "." + e.getMessage());
        }
    }

    public static Student[] getAll(Statement statement)
    {
        Student[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select * from student");

            List<Student> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Student tempStudent = new Student();

                tempStudent.id = (answer.getInt("ID"));
                tempStudent.name = (answer.getString("Name"));
                tempStudent.address = (answer.getString("Address"));
                tempStudent.phone = (answer.getInt("PhoneNumber")) + "";
                tempStudent.email = (answer.getString("Email"));
                tempStudent.gender = (answer.getString("Gender"));
                tempStudent.dob = (answer.getDate("DOB")).toString();
                tempStudent.category = (answer.getString("Category"));
                tempStudent.major = (answer.getInt("MajorID")) + "";
                tempStudent.minor = (answer.getInt("MinorID")) + "";
                tempStudent.advisorID = (answer.getInt("AdvisorID"));

                expandableList.add(tempStudent);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Student[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the students. " + e.getMessage());
        }

        return output;
    }
}
