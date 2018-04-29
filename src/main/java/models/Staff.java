package models;
import java.sql.*;
import java.util.*;
import java.text.*;


public class Staff 
{
    private static int nextStaffId;
    private static boolean isInitialized;
    public int id;
    public String name;
    public String email;
    public String address;
    public String gender;
    public String dob;
    public String jobTitle;
    public String location;

    public static Staff[] getAll(Statement statement)
    {
        Staff[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.staff.ID as ID, isaacp.staff.Name AS Name, Email, HomeAddress, DOB, Gender, isaacp.JobTitle.Name as Title, Location " + 
            "from isaacp.Staff join isaacp.jobtitle on (isaacp.staff.jobtitleid = isaacp.jobtitle.id)");

            List<Staff> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Staff tempStaff = new Staff();

                tempStaff.id = (answer.getInt("ID"));
                tempStaff.name = (answer.getString("Name"));
                tempStaff.email = (answer.getString("Email"));
                tempStaff.address = (answer.getString("HomeAddress"));
                tempStaff.dob = (answer.getDate("DOB")).toString();
                tempStaff.gender = (answer.getString("Gender"));
                tempStaff.jobTitle = (answer.getString("Title"));
                tempStaff.location = (answer.getString("Location"));

                expandableList.add(tempStaff);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Staff[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the staff. " + e.getMessage());
        }

        return output;
    }

    public void add(Statement statement)
    {
        try
        {
            if(isInitialized == false)
            {
                isInitialized = true;
                // get the max id that was used in the database
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.staff");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextStaffId = answer.getInt("MAX(ID)") + 1;
                }
            }

            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
            // System.out.println(input.toPattern());
            SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
            String dobString = formater.format(input.parse(dob));

            statement.execute("insert into isaacp.staff values('" + nextStaffId +
            "', '" + name +
            "', '" + email +
            "', '" + address +
            "', '" + dobString +
            "', '" + gender +
            "', '" + jobTitle +
            "', '" + location +
            "' )");
        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new staff. " + e.getMessage());
        }
        nextStaffId++;
    }

}
