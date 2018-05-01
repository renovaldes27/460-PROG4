package models;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;


public class Invoice 
{
    private static int nextInvoiceId;
    private static boolean isInitialized;
    public int id;
    public String leaseID;
    public String semester;
    public String paymentDueDate;
    public String DatePaid = "";

    public static Invoice[] getAll(Statement statement)
    {
        Invoice[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.Invoice.ID as ID, semester, PaymentDue, DatePaid, isaacp.Student.name AS sName, isaacp.room.RoomNumber AS rNumber, isaacp.building.name AS bName " + 
            "from isaacp.Invoice join isaacp.StudentLease on (isaacp.Invoice.LeaseID = isaacp.StudentLease.id) " + 
            "join isaacp.Student on (isaacp.StudentLease.StudentID = isaacp.Student.id) " + 
            "join isaacp.room on (isaacp.StudentLease.RoomID = isaacp.room.id) " + 
            "join isaacp.building on (isaacp.room.buildingID = isaacp.building.id)");

            List<Invoice> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Invoice tempLease = new Invoice();

                tempLease.id = answer.getInt("ID");
                tempLease.semester = answer.getString("semester");
                tempLease.leaseID = answer.getString("SNAME") + " in " + answer.getString("bName") + ": " + answer.getString("rNumber");
                tempLease.paymentDueDate = answer.getDate("PaymentDue").toString();

                Date DatePaidAsDate = answer.getDate("DatePaid");
                if(DatePaidAsDate == null)
                {
                    tempLease.DatePaid = "";
                }
                else
                {
                    tempLease.DatePaid = DatePaidAsDate.toString();
                }

                expandableList.add(tempLease);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Invoice[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Invoice. " + e.getMessage());
        }

        return output;
    }

    // This answers query 3    
    public static int getDebt(Statement statement)
    {
        int output = 0;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.StudentLease.MonthlyCost as cost " + 
            "from isaacp.Invoice join isaacp.StudentLease on (isaacp.Invoice.LeaseID = isaacp.StudentLease.id) " + 
            "where DatePaid IS NULL");

            while(answer.next())
            {
                output += answer.getInt("cost");
            }
            
            output *= 4; // lease is per semester, rent is per month.  assuming 4 whole months in a semester.
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Invoice. " + e.getMessage());
        }

        return output;
    }

    // This answers query 3
    public static Invoice[] getUnpaid(Statement statement)
    {
        Invoice[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.Invoice.ID as ID, semester, PaymentDue, isaacp.Student.name AS sName, isaacp.room.RoomNumber AS rNumber, isaacp.building.name AS bName " + 
            "from isaacp.Invoice join isaacp.Staff on (isaacp.Invoice.InspectorID = isaacp.Staff.id) " +
            "join isaacp.StudentLease on (isaacp.Invoice.LeaseID = isaacp.StudentLease.id) " + 
            "join isaacp.Student on (isaacp.StudentLease.StudentID = isaacp.Student.id) " + 
            "join isaacp.room on (isaacp.StudentLease.RoomID = isaacp.room.id) " + 
            "join isaacp.building on (isaacp.room.buildingID = isaacp.building.id) " + 
            "where DatePaid IS NULL");

            List<Invoice> expandableList = new ArrayList<>();

            while(answer.next())
            {
                Invoice tempLease = new Invoice();

                tempLease.id = answer.getInt("ID");
                tempLease.semester = answer.getString("semester");
                tempLease.leaseID = answer.getString("SNAME") + " in " + answer.getString("bName") + ": " + answer.getString("rNumber");
                tempLease.paymentDueDate = answer.getDate("PaymentDue").toString();
                
                tempLease.DatePaid = "";

                expandableList.add(tempLease);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Invoice[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the Invoice. " + e.getMessage());
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
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.Invoice");
                while(answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextInvoiceId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.invoice where (id = " + id + " )");
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
                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
                SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
                String payDueString = formater.format(input.parse(paymentDueDate));
    
                String datePaidString;
                if(DatePaid.toLowerCase().compareTo("null") == 0)
                {
                    datePaidString = "NULL";
                }
                else
                {
                    datePaidString = "'" + formater.format(input.parse(DatePaid)) + "'";
                }
    
                statement.execute("insert into isaacp.Invoice values('" + nextInvoiceId +
                "', '" + leaseID +
                "', '" + semester +
                "', '" + payDueString +
                "', " + datePaidString +
                " )");

                nextInvoiceId++;
            }

        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new Invoice. " + e.getMessage());
        }
    }

    private void update(Statement statement)
    {
        try
        {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
            SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
            String payDueString = formater.format(input.parse(paymentDueDate));
            
            String datePaidString;
            if(DatePaid.toLowerCase().compareTo("null") == 0)
            {
                datePaidString = "NULL";
            }
            else
            {
                datePaidString = "'" + formater.format(input.parse(DatePaid)) + "'";
            }

            statement.execute("update isaacp.invoice set " +
            "LeaseID = '" + leaseID + 
            "', Semester = '" + semester + 
            "', PaymentDue = '" + payDueString + 
            "', DatePaid = " + datePaidString + 
            " where ( ID = " + id + " )");
        }
        catch (SQLException | ParseException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't update invoice. " + e.getMessage());
            // System.err.println("ID = " + id);
            System.err.println("leaseID = " + leaseID);
            System.err.println("semester = " + semester);
            System.err.println("payDueString = " + paymentDueDate);
            System.err.println("DatePaid = " + DatePaid);
        }  
    }
}