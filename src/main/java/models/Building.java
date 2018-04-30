package models;
import java.sql.*;
import java.util.*;

public class Building 
{
    private static int nextBuildingId;
    private static boolean isInitialized;
    public int id;
    public String name;
    public String address;
    public int isApartment;
    public String telephoneNumber;
    public String managerID;
    public String numStudents;

    public static Building[] getAll(Statement statement)
    {
        Building[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.Building.ID as ID, isaacp.Building.Name AS Name, Address, IsApartment, TelephoneNumber, isaacp.staff.Name as staffName, NumberOfStudents " + 
            "from isaacp.Building join isaacp.Staff on (isaacp.Building.ManagerID = isaacp.Staff.id)");

            List<Building> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Building tempStaff = new Building();

                tempStaff.id = answer.getInt("ID");
                tempStaff.name = answer.getString("Name");
                tempStaff.address = answer.getString("Address");
                tempStaff.isApartment = answer.getInt("IsApartment");
                tempStaff.managerID = answer.getString("staffName");
                tempStaff.telephoneNumber = answer.getString("TelephoneNumber");
                tempStaff.numStudents = answer.getString("NumberOfStudents");

                expandableList.add(tempStaff);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Building[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Building. " + e.getMessage());
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
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.Building");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextBuildingId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.building where (id = " + id + " )");
            while(answer.next())
            {
                exists = true;
            }

            if(exists)
            {
                update(statement);               
            }
            else
            {
                statement.execute("insert into isaacp.Building values('" + nextBuildingId +
                "', '" + name +
                "', '" + address +
                "', '" + isApartment +
                "', '" + telephoneNumber +
                "', '" + managerID +
                "', '" + numStudents +
                "' )");

                nextBuildingId++;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new Building. " + e.getMessage());
        }
    }

    private void update(Statement statement)
    {
        try
        {
            statement.execute("update isaacp.building set " +
            "Name = '" + name + 
            "', Address = '" + address + 
            "', IsApartment = '" + isApartment + 
            "', TelephoneNumber = '" + telephoneNumber + 
            "', ManagerID = '" + managerID + 
            "', NumberOfStudents = '" + numStudents + 
            "' where ( ID = " + id + " )");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't update building. " + e.getMessage());
            // System.err.println("ID = " + id);
            // System.err.println("Name = " + name);
            // System.err.println("Address = " + address);
            // System.err.println("Email = " + email);
            // System.err.println("Gender = " + gender);
            // System.err.println("DOB = " + dob);
            // System.err.println("Category = " + category);
            // System.err.println("MajorID = " + major);
            // System.err.println("MinorID = " + minor);
            // System.err.println("AdviosorID = " + advisorID);
        }  
    }
}