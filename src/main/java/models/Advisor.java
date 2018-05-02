package models;

import java.sql.*;
import java.util.*;

/*
 * - Advisor
 *
 *
 * - This class is used the model an Advisor on the front end.
 *   It is also used for any backend methods that add/get/update advisors
 *   to the table.
 *
 * */
public class Advisor {
    private static int nextAdvisorId; // Next id in db table
    private static boolean isInitialized; // initialized
    public int id; // Advisor ID
    public String name; // Advisor Name
    public String position; // Advisor position
    public String departmentID; // Department ID
    public String telephoneNumber; // Phone #
    public String email; // email

    /*
     * - getAll()
     *
     * - This method makes a call to the DB to get all the advisors from the advisor
     * table.
     * 
     * - Param: db statement - Return:A list of all advisors.
     */
    public static Advisor[] getAll(Statement statement) {
        Advisor[] output = null;

        try {
            ResultSet answer = statement.executeQuery(
                    "select isaacp.Advisor.ID as ID, isaacp.Advisor.Name AS Name, Email, Position, TelephoneNumber, isaacp.department.Name as dName "
                            + "from isaacp.Advisor join isaacp.department on (isaacp.Advisor.DepartmentID = isaacp.department.id)");

            List<Advisor> expandableList = new ArrayList<>();

            while (answer.next()) {
                Advisor tempStaff = new Advisor();

                tempStaff.id = answer.getInt("ID");
                tempStaff.name = answer.getString("Name");
                tempStaff.position = answer.getString("Position");
                tempStaff.departmentID = answer.getString("dName");
                tempStaff.telephoneNumber = answer.getString("TelephoneNumber");
                tempStaff.email = answer.getString("Email");

                expandableList.add(tempStaff);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Advisor[size];
            for (int i = 0; i < output.length; i++) {
                output[i] = expandableList.get(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the advisor. " + e.getMessage());
        }

        return output;
    }

    /*
     * - add()
     *
     * - This method makes a call to the DB to add a new advisor to the advisor
     * table.
     * 
     * - Param: db statement - Return:None
     */
    public void add(Statement statement) {
        try {
            if (isInitialized == false) {
                isInitialized = true;
                // get the max id that was used in the database
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.Advisor");
                while (answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextAdvisorId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.advisor where (id = " + id + " )");
            while (answer.next()) {
                exists = true;
            }

            if (exists) {
                update(statement);
            } else {
                statement.execute("insert into isaacp.Advisor values('" + nextAdvisorId + "', '" + name + "', '"
                        + position + "', '" + departmentID + "', '" + telephoneNumber + "', '" + email + "' )");

                nextAdvisorId++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new advisor. " + e.getMessage());
        }
    }

    /*
     * - update()
     *
     * - This method makes a call to the DB to update a an existing advisor in the
     * advisor table.
     * 
     * - Param: db statement - Return:None
     */
    private void update(Statement statement) {
        try {
            statement.execute("update isaacp.advisor set " + "Name = '" + name + "', Position = '" + position
                    + "', DepartmentID = '" + departmentID + "', TelephoneNumber = '" + telephoneNumber + "', Email = '"
                    + email + "' where ( ID = " + id + " )");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't update advisor. " + e.getMessage());
        }
    }
}