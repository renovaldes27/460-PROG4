package models;
import java.sql.*;
import java.util.*;
import java.text.*;


public class Inspection 
{
    private static int nextInspectionId;
    private static boolean isInitialized;
    public int id;
    public String inspectionDate;
    public String staffName;
    public String roomString;
    public String condition;
    public String Action;

    public static Inspection[] getAll(Statement statement)
    {
        Inspection[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.Inspection.ID as ID, Condition, Action, InspectionDate, isaacp.Staff.name AS sName, isaacp.room.RoomNumber AS rNumber, isaacp.building.name AS bName " + 
            "from isaacp.Inspection join isaacp.Staff on (isaacp.Inspection.InspectorID = isaacp.Staff.id) " +
            "join isaacp.room on (isaacp.Inspection.InspectedRoomID = isaacp.room.id) " + 
            "join isaacp.building on (isaacp.room.buildingID = isaacp.building.id)");

            List<Inspection> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Inspection tempLease = new Inspection();

                tempLease.id = answer.getInt("ID");
                tempLease.Action = answer.getString("Action");
                tempLease.condition = answer.getString("Condition");
                tempLease.roomString = answer.getString("bName") + ": " + answer.getString("rNumber");
                tempLease.staffName = answer.getString("SNAME");
                tempLease.inspectionDate = answer.getDate("LeaseinspectionDate").toString();

                expandableList.add(tempLease);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Inspection[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Inspection. " + e.getMessage());
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
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.Inspection");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextInspectionId = answer.getInt("MAX(ID)") + 1;
                }
            }

            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
            // System.out.println(input.toPattern());
            SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
            String inspectionDateString = formater.format(input.parse(inspectionDate));

            statement.execute("insert into isaacp.Inspection values('" + nextInspectionId +
            "', '" + inspectionDateString +
            "', '" + staffName +
            "', '" + roomString +
            "', '" + condition +
            "', '" + Action +
            "' )");
        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new Inspection. " + e.getMessage());
        }
        nextInspectionId++;
    }
}