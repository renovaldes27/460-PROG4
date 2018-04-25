package models;
import java.util.Date;
import java.sql.*;

public class Student 
{
    private static int nextStudentId;
    private static boolean isInitialized;
    int ID;
    String name;
    String address;
    String phone;
    String email;
    char gender;
    String dob;
    String category;
    String major;
    String minor;
    int advisorID;

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
            statement.execute("update student set name = " + name + " where id = " + ID);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't update student, id = " + ID + "." + e.getMessage());
        }  
    }

    public void delete(Statement statement)
    {
        try
        {
            statement.execute("delete from student where id = " + ID);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't remove student, id = " + ID + "." + e.getMessage());
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

                tempStudent.ID = (answer.getInt("id"));
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public int getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }
}
