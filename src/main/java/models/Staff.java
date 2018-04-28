package models;
import java.sql.*;
import java.util.*;


public class Staff {
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
            ResultSet answer = statement.executeQuery("select * from isaacp.Staff join isaacp.jobtitle on (isaacp.staff.jobtitleid = isaacp.jobtitle.id)");

            List<Staff> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Staff tempStaff = new Staff();

                tempStaff.id = (answer.getInt("isaacp.staff.ID"));
                tempStaff.name = (answer.getString("isaacp.staff.Name"));
                tempStaff.email = (answer.getString("isaacp.staff.Email"));
                tempStaff.address = (answer.getString("isaacp.staff.HomeAddress"));
                tempStaff.dob = (answer.getDate("isaacp.staff.DOB")).toString();
                tempStaff.gender = (answer.getString("isaacp.staff.Gender"));
                tempStaff.jobTitle = (answer.getString("isaacp.JobTitle.Name"));
                tempStaff.location = (answer.getString("isaacp.staff.Location"));

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

}
