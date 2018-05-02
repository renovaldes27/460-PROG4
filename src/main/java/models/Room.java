package models;
import java.sql.*;
import java.util.*;

/*
 * - Room
 *
 *
 * - This class is used the model an Room on the front end.
 *   It is also used for any backend methods that add/get/update Room
 *   to the table.
 *
 * */
public class Room 
{
    private static int nextRoomId;// next id to assign to a new staff
    private static boolean isInitialized;// retains whether nextStaffId has been initialized
    public int id;// primary key
    public String roomNumber; // physical room number
    public String buildingId; // which building is the room in
    public String studentID;  // which student currently occupies the room
    public String monthlyRent; // the rent

    /*
     * - getAll()
     *
     * - This method makes a call to the DB to get all the room from the room
     * table.
     * 
     * - Param: db statement - Return:A list of all rooms.
     */
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

    /*
     * - add()
     *
     * - This method makes a call to the DB to add a new room to the room
     * table.
     * 
     * - Param: db statement - Return:None
     */
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
    
    /*
     * - update()
     *
     * - This method makes a call to the DB to update a an existing room in the
     * room table.
     * 
     * - Param: db statement - Return:None
     */
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
        }  
    }
}