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
    public String isAppartment;
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
                int isApt = answer.getInt("IsApartment");
                if(isApt == 0)
                {
                    tempStaff.isAppartment = "No";
                }
                else
                {
                    tempStaff.isAppartment = "Yes";
                }
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

            statement.execute("insert into isaacp.Building values('" + nextBuildingId +
            "', '" + name +
            "', '" + address +
            "', '" + isAppartment +
            "', '" + telephoneNumber +
            "', '" + managerID +
            "', '" + numStudents +
            "' )");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new Building. " + e.getMessage());
        }
        nextBuildingId++;
    }
}