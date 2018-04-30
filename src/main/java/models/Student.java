package models;

import java.util.*;
import java.sql.*;
import java.text.*;

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
    public String advisorID;

    public void add(Statement statement)
    {
        try
        {
            if(isInitialized == false)
            {
                isInitialized = true;
                // get the max id that was used in the database
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.student");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextStudentId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.student where (id = " + id + " )");
            while(answer.next())
            {
                exists = true;
            }

            int inputID;
            if(exists)
            {
                inputID = id;
                statement.execute("delete from isaacp.student where id = " + id);                
            }
            else
            {
                inputID = nextStudentId;
                nextStudentId++;
            }
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
            // System.out.println(input.toPattern());
            SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
            String dobString = formater.format(input.parse(dob));

            statement.execute("insert into isaacp.student values('" + inputID +
            "', '" + name +
            "', '" + address +
            "', '" + phone +
            "', '" + email +
            "', '" + gender +
            "', '" + dobString +
            "', '" + category +
            "', '" + major +
            "', '" + minor +
            "', '" + advisorID +
            "' )");
        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new student. " + e.getMessage());
        }
    }

    public void update(Statement statement)
    {
        try
        {
            statement.execute("update isaacp.student set name = '" + name + "' where id = " + id);
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
            statement.execute("delete from isaacp.student where id = " + id);
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
            ResultSet answer = statement.executeQuery("select isaacp.student.id as id, isaacp.student.name as name, address, phoneNumber, isaacp.student.email, gender, dob, category, major.name as majorid, minor.name as minorid, isaacp.advisor.name as advisorid" + 
            " from isaacp.student" + 
            " join isaacp.department major on (isaacp.student.majorID = major.id)" +
            " join isaacp.department minor on (isaacp.student.minorID = minor.id)" +
            " join isaacp.advisor on (isaacp.student.advisorid = isaacp.advisor.id)");

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
                tempStudent.major = (answer.getString("MajorID"));
                tempStudent.minor = (answer.getString("MinorID"));
                tempStudent.advisorID = (answer.getString("AdvisorID"));

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
