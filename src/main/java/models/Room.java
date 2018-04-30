package models;
import java.sql.*;
import java.util.*;

public class Room 
{
    private static int nextRoomId;
    private static boolean isInitialized;
    public int id;
    public String roomNumber;
    public String buildingId;
    public String studentID;
    public String monthlyRent;

    public static Room[] getAll(Statement statement)
    {
        Room[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.Room.ID as ID, RoomNumber, isaacp.student.name AS sName, MonthlyRentRate, isaacp.building.Name as bName " + 
            "from isaacp.Room join isaacp.student on (isaacp.Room.studentID = isaacp.student.id) " +
            "join isaacp.building on (isaacp.Room.buildingID = isaacp.building.id)");

            List<Room> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Room tempStaff = new Room();

                tempStaff.id = answer.getInt("ID");
                tempStaff.roomNumber = answer.getString("RoomNumber");
                tempStaff.buildingId = answer.getString("bName");
                tempStaff.studentID = answer.getString("sName");
                tempStaff.monthlyRent = answer.getString("monthlyRentRate");

                expandableList.add(tempStaff);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Room[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the room. " + e.getMessage());
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
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.Room");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextRoomId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.room where (id = " + id + " )");
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
                statement.execute("insert into isaacp.Room values('" + nextRoomId +
                "', '" + roomNumber +
                "', '" + buildingId +
                "', '" + studentID +
                "', '" + monthlyRent +
                "' )");
                nextRoomId++;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new room. " + e.getMessage());
        }
    }
    
    private void update(Statement statement)
    {
        try
        {
            statement.execute("update isaacp.room set " +
            "RoomNumber = '" + roomNumber + 
            "', BuildingID = '" + buildingId + 
            "', StudentID = '" + studentID + 
            "', MonthlyRentRate = '" + monthlyRent + 
            "' where ( ID = " + id + " )");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't update room. " + e.getMessage());
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