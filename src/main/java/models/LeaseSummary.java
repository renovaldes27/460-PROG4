// query 2 of the specification

package models;
import java.sql.*;

/*
 * - Advisor
 *
 *
 * - This class is used the model query 2 of the projet spec on the front end.
 *   It is also used for the backend method getOneSummary()
 *
 * */
public class LeaseSummary
{
    public int freshDorm;   // how many freshman are in dormatories
    public int freshApt;    // how many freshman are in apartments
    public int sophDorm;    // how many sophmores are in dormatories
    public int sophApt;     // how many sophmores are in apartments
    public int junDorm;     // how many juniors are in dormatories
    public int junApt;      // how many juniors are in apartments
    public int senDorm;     // how many seniors are in dormatories
    public int senApt;      // how many seniors are in apartments
    public int gradDorm;    // how many graduates are in dormatories
    public int gradApt;     // how many graduates are in apartments

    /*
     * - getSummary()
     *
     * - This method makes calls to the DB to determin how many students of each category are
     * in either apartments or dormatories
     * 
     * - Param: db statement - Return:A list of all buildings.
     */
    public static LeaseSummary getSummary(Statement statement)
    {
        LeaseSummary summary = new LeaseSummary();

        summary.freshDorm = getOneSummary("freshman", 0, statement);
        summary.freshApt = getOneSummary("freshman", 1, statement);
        
        summary.sophDorm = getOneSummary("sophmore", 0, statement);
        summary.sophApt = getOneSummary("sophmore", 1, statement);

        summary.junDorm = getOneSummary("junior", 0, statement);
        summary.junApt = getOneSummary("junior", 1, statement);

        summary.senDorm = getOneSummary("senior", 0, statement);
        summary.senApt = getOneSummary("senior", 1, statement);

        summary.gradDorm = getOneSummary("graduate", 0, statement);
        summary.gradApt = getOneSummary("graduate", 1, statement);
        return summary;
    }

    /*
     * - getOneSummary(String category, int isApt, Statement statement)
     *
     * - This method makes a call to the DB to get a summary of Lease.
     * 
     * - Param: db statement - Return: a summary of one lease
     */
    private static int getOneSummary(String category, int isApt, Statement statement)
    {
        try {
            ResultSet answer = statement.executeQuery("select count(isaacp.student.id) " +
            "from isaacp.student join isaacp.studentLease ON (isaacp.student.id = isaacp.studentLease.studentID) " +
            "join isaacp.room ON (isaacp.room.id = isaacp.studentLease.roomID) " +
            "join isaacp.building ON (isaacp.building.id = isaacp.room.BuildingID) " +
            "where (isaacp.student.category = '" + category + "' and isaacp.Building.IsApartment = "+ isApt +" )");
            while(answer.next()) // there should be only one result, but java requires us to use .next()
            {
                return answer.getInt(1); // result set is one indexed, unlike normal compSci
            }
		} catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the student lease summary. Category = " + category + ". isApartment = " + isApt);
            System.out.println(e.getMessage());
		}
        return Integer.MIN_VALUE;
    }
}