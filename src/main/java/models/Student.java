package models;
import java.util.*;
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
            
            // // figure out how many students there are
            // This dosen't work, becuase the result set is "forward only"
            // answer.last();
            // int size = answer.getRow() - 1;
            // answer.beforeFirst();

            // // create an array of appropriate size
            // output = new Student[size];

            List<Student> expandableList = new ArrayList<>();
            // fill out the tablez
            // for(int i = 0; answer.next(); i++)
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

                // output[i] = tempStudent;
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

    // public int getID() {
    //     return ID;
    // }

    // public void setID(int ID) {
    //     this.ID = ID;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getAddress() {
    //     return address;
    // }

    // public void setAddress(String address) {
    //     this.address = address;
    // }

    // public String getPhone() {
    //     return phone;
    // }

    // public void setPhone(String phone) {
    //     this.phone = phone;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getGender() {
    //     return gender;
    // }

    // public void setGender(String gender) {
    //     this.gender = gender;
    // }

    // public String getDob() {
    //     return dob;
    // }

    // public void setDob(String dob) {
    //     this.dob = dob;
    // }

    // public String getCategory() {
    //     return category;
    // }

    // public void setCategory(String category) {
    //     this.category = category;
    // }

    // public String getMajor() {
    //     return major;
    // }

    // public void setMajor(String major) {
    //     this.major = major;
    // }

    // public String getMinor() {
    //     return minor;
    // }

    // public void setMinor(String minor) {
    //     this.minor = minor;
    // }

    // public int getAdvisorID() {
    //     return advisorID;
    // }

    // public void setAdvisorID(int advisorID) {
    //     this.advisorID = advisorID;
    // }
}
