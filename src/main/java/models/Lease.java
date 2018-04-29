package models;
import java.sql.*;
import java.util.*;

public class Lease {
    public int id;
    public String rID;
    public String sID;
    public String duration;
    public int cost;
    public String startDate;

    public static Lease[] getAll(Statement statement)
    {
        Lease[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.StudentLease.ID as ID, MonthlyCost, Duration, LeaseStartDate, isaacp.student.name AS sName, isaacp.room.RoomNumber AS rNumber, isaacp.building.name AS bName " + 
            "from isaacp.StudentLease join isaacp.student on (isaacp.StudentLease.StudentID = isaacp.student.id) " +
            "join isaacp.room on (isaacp.studentLease.roomID = isaacp.room.id) " + 
            "join isaacp.building on (isaacp.room.buildingID = isaacp.building.id)");

            List<Lease> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Lease tempLease = new Lease();

                tempLease.id = (answer.getInt("ID"));
                tempLease.cost = (answer.getInt("MonthlyCost"));
                tempLease.duration = (answer.getInt("Duration") + " semesters");
                tempLease.rID = (answer.getString("bName") + ": " + answer.getString("rNumber"));
                tempLease.sID = (answer.getString("sName"));
                tempLease.startDate = (answer.getDate("LeaseStartDate")).toString();

                expandableList.add(tempLease);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Lease[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Lease. " + e.getMessage());
        }

        return output;
    }

}