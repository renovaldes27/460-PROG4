package models;
import java.util.Date;
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
    public char gender;
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

            // TODO: add all of the other fields
            // TODO: implement student.toString()?  would need to ignore the id field
            statement.execute("insert into isaacp.student values(" + nextStudentId + name + ")");
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
            answer.last();
            int size = answer.getRow() - 1;
            answer.beforeFirst();
            output = new Student[size];
            for(int i = 0; answer.next(); i++)
            {
                Student tempStudent = new Student();

                tempStudent.id = (answer.getInt("id"));
                // TODO: add all of the other fields

                output[i] = tempStudent;
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
