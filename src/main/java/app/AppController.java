package app; 

import models.*;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.sql.*; 
import java.sql.*; 
import java.util.*;

/*
 * - AppController
 *
 *
 * - This class provides all of the methods that are called by the front end.
 *
 * */
@Controller
public class AppController 
{
    @Autowired
    private DataSource dataSource;

     /*
     * - getStudents()
     *
     * - This method makes a call to the DB to get all the students from the student
     * table.
     * 
     * - Param: none - Return:A list of all students.
     */
    @RequestMapping(value ="/students", method = RequestMethod.GET)
    @ResponseBody
    public Student[]  getStudents() 
    {
        Student results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Student.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getInvoice()
     *
     * - This method makes a call to the DB to get all the invoices from the invoice
     * table.
     * 
     * - Param: none - Return:A list of all invoice.
     */
    @RequestMapping(value ="/invoice", method = RequestMethod.GET)
    @ResponseBody
    public Invoice[]  getInvoices() 
    {
        Invoice results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Invoice.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getInspection()
     *
     * - This method makes a call to the DB to get all the inspections from the inspection
     * table.
     * 
     * - Param: none - Return:A list of all inspection.
     */
    @RequestMapping(value ="/inspect", method = RequestMethod.GET)
    @ResponseBody
    public Inspection[]  getInspections() 
    {
        Inspection results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Inspection.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - addNewStudent()
     *
     * - This method makes a call to the DB to add a new student to the student
     * table.
     * 
     * - Param: a new student - Return: None.
     */
    @RequestMapping(value ="/students", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewStudent(@RequestBody Student jsonString) 
    {
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            jsonString.add(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
    }

    /*
     * - addNewStaff()
     *
     * - This method makes a call to the DB to add a new staff to the staff
     * table.
     * 
     * - Param: a new staff - Return: None.
     */
    @RequestMapping(value ="/staff", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewStaff(@RequestBody Staff jsonString)
    {
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            jsonString.add(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        } 
    }

    /*
     * - removeStudent()
     *
     * - This method makes a call to the DB to remove an existing student from the student
     * table.
     * 
     * - Param: an existing student - Return: None.
     */
    @RequestMapping(value ="/studremove", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeStudent(@RequestBody Student jsonString)
    {
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            jsonString.delete(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
    }

    /*
     * - addNewLease()
     *
     * - This method makes a call to the DB to add a new lease to the lease
     * table.
     * 
     * - Param: a new lease - Return: None.
     */
    @RequestMapping(value ="/lease", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewLease(@RequestBody Lease jsonString) 
    {
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            jsonString.add(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
    }

    /*
     * - addNewAdvisor()
     *
     * - This method makes a call to the DB to add a new advisor to the advisor
     * table.
     * 
     * - Param: a new advisor - Return: None.
     */
    @RequestMapping(value ="/advisors", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewAdvisor(@RequestBody Advisor jsonString) 
    {
       try 
       {
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           jsonString.add(statement);
           statement.close();
           connection.close();
       } 
       catch (SQLException e) {
           e.printStackTrace();
           System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
       }
    }

    /*
     * - addNewRoom()
     *
     * - This method makes a call to the DB to add a new room to the room
     * table.
     * 
     * - Param: a new room - Return: None.
     */
    @RequestMapping(value ="/room", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewRoom(@RequestBody Room jsonString) 
    {
       try 
       {
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           jsonString.add(statement);
           statement.close();
           connection.close();
       } 
       catch (SQLException e) {
           e.printStackTrace();
           System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
       }
    }

    /*
     * - addNewBuilding()
     *
     * - This method makes a call to the DB to add a new building to the building
     * table.
     * 
     * - Param: a new building - Return: None.
     */
    @RequestMapping(value ="/buildings", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewBuildilng(@RequestBody Building jsonString) 
    {
       try 
       {
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           jsonString.add(statement);
           statement.close();
           connection.close();
       } 
       catch (SQLException e) {
           e.printStackTrace();
           System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
       }
    }

    /*
     * - addNewInvoice()
     *
     * - This method makes a call to the DB to add a new invoice to the invoice
     * table.
     * 
     * - Param: a new invoice - Return: None.
     */
    @RequestMapping(value ="/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewInvoice(@RequestBody Invoice jsonString) 
    {
       try 
       {
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           jsonString.add(statement);
           statement.close();
           connection.close();
       } 
       catch (SQLException e) {
           e.printStackTrace();
           System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
       }
    }

    /*
     * - addNewInspection()
     *
     * - This method makes a call to the DB to add a new Inspection to the inspection
     * table.
     * 
     * - Param: a new inspection - Return: None.
     */
    @RequestMapping(value ="/inspect", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewInspection(@RequestBody Inspection jsonString) 
    {
       try 
       {
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           jsonString.add(statement);
           statement.close();
           connection.close();
       } 
       catch (SQLException e) {
           e.printStackTrace();
           System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
       }
    }

    /*
     * - getStaff()
     *
     * - This method makes a call to the DB to get all the staff from the staff
     * table.
     * 
     * - Param: none - Return:A list of all staff.
     */
    @RequestMapping(value ="/staff", method = RequestMethod.GET)
    @ResponseBody
    public Staff[]  getStaff()
    {
        Staff results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Staff.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getLease()
     *
     * - This method makes a call to the DB to get all the Leases from the lease
     * table.
     * 
     * - Param: none - Return:A list of all leases.
     */
    @RequestMapping(value ="/lease", method = RequestMethod.GET)
    @ResponseBody
    public Lease[]  getLease()
    {
        Lease results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Lease.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getAdvisors()
     *
     * - This method makes a call to the DB to get all the advisors from the advisor
     * table.
     * 
     * - Param: none - Return:A list of all advisor.
     */
    @RequestMapping(value ="/advisors", method = RequestMethod.GET)
    @ResponseBody
    public Advisor[] getAdvisors()
    {
        Advisor results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Advisor.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getRooms()
     *
     * - This method makes a call to the DB to get all the rooms from the room
     * table.
     * 
     * - Param: none - Return:A list of all room.
     */
    @RequestMapping(value ="/room", method = RequestMethod.GET)
    @ResponseBody
    public Room[] getRooms()
    {
        Room results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Room.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getbuildings()
     *
     * - This method makes a call to the DB to get all the buildings from the building
     * table.
     * 
     * - Param: none - Return:A list of all building.
     */
    @RequestMapping(value ="/buildings", method = RequestMethod.GET)
    @ResponseBody
    public Building[] getBuildings()
    {
        Building results[] = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Building.getAll(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getLeaseSummary()
     *
     * - This method makes a call to the DB to determine how many students
     * in each category are in each type of housing.
     * 
     * - Param: None - Return: a summary of all leases.
     */
    @RequestMapping(value ="/summary", method = RequestMethod.GET)
    @ResponseBody
    public LeaseSummary getLeaseSummary()
    {
        LeaseSummary result = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            result =  LeaseSummary.getSummary(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return result;
    }

    /*
     * - getUnapid()
     *
     * - This method makes a call to the DB to get a list of all unpaid invoices.
     * 
     * - Param: None - Return: a list of unpaid invoices.
     */
    @RequestMapping(value ="/unpaid", method = RequestMethod.GET)
    @ResponseBody
    public Invoice[] getUnapid()
    {
        Invoice[] results = null;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            results =  Invoice.getUnpaid(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return results;
    }

    /*
     * - getDebt()
     *
     * - This method makes a call to the DB to get the total amount of money
     * owed by unpaid invoces.
     * 
     * - Param: None - Return: the amount of money owed by unpaid invoces.
     */
    @RequestMapping(value ="/debt", method = RequestMethod.GET)
    @ResponseBody
    public String getDebt()
    {
        int result = 0;
        try 
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            result =  Invoice.getDebt(statement);
            statement.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
        return String.valueOf(result);
    }

    /*
     * - getHallManagerInfo()
     *
     * - This method makes a call to the DB to get information about the staff members
     * that manage the dormatories.
     * 
     * - Param: None - Return: the list of information about dormatory managers.
     */
    @RequestMapping(value ="/getHallManagerInfo", method = RequestMethod.GET)
    @ResponseBody
    public HallManager[] getHallManagerInfo()
    {
        HallManager[] output = null;

        try
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet answer = statement.executeQuery("select isaacp.staff.Name as ManagerName, isaacp.building.TelephoneNumber, isaacp.building.name as BuildingName from isaacp.staff join isaacp.building on (isaacp.building.managerID = isaacp.staff.id)");
            List<HallManager> expandableList = new ArrayList<>();

            while(answer.next())
            {
                HallManager manager = new HallManager();

                manager.ManagerName = (answer.getString("ManagerName"));
                manager.TelephoneNumber = (answer.getInt("TelephoneNumber"));
                manager.BuildingName = (answer.getString("BuildingName"));

                expandableList.add(manager);
            } 

            int size = expandableList.size();
            output = new HallManager[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR:can't retrieve the managers. " + e.getMessage());
        }

        return output;
    }
    
    /*
     * - getLowerRents()
     *
     * - This method makes a call to the DB to get information about rooms
     * that have a lower rent than the room that the student is currently residing in.
     * 
     * - Param: the id of a student - Return: the list of rooms cheaper than the studen't current room.
     */
    @RequestMapping(value ="/getLowerRents", method = RequestMethod.GET)
    @ResponseBody
    public LowerRent[] getLowerRents(@RequestParam(value="studentID", defaultValue="1") int studentID)
    {
        LowerRent[] output = null;
        try
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            int studentRent = 0;

            ResultSet answer = statement.executeQuery("SELECT MonthlyRentRate from isaacp.Room WHERE StudentID = "+studentID);
            while(answer.next()) // there should be only one result, but java requires us to use .next()
            {
                studentRent = answer.getInt(1); // result set is one indexed, unlike normal compSci
            }
            
            String finalQueryString = "SELECT isaacp.Building.Name as BuildingName, isaacp.Room.RoomNumber as RoomNumber, isaacp.Building.Address as BuildingAddress, isaacp.Room.MonthlyRentRate as MonthlyRentRate FROM isaacp.Room JOIN isaacp.Building ON (isaacp.Room.BuildingID = isaacp.Building.ID) WHERE isaacp.Room.MonthlyRentRate < "+studentRent;

            answer = statement.executeQuery(finalQueryString);

            List<LowerRent> expandableList = new ArrayList<>();

            while(answer.next())
            {
                LowerRent lowerRent = new LowerRent();

                lowerRent.BuildingName = (answer.getString("BuildingName"));
                lowerRent.RoomNumber = (answer.getInt("RoomNumber"));
                lowerRent.BuildingAddress = (answer.getString("BuildingAddress"));
                lowerRent.MonthlyRentRate = (answer.getString("MonthlyRentRate"));

                expandableList.add(lowerRent);
            }

            int size = expandableList.size();
            output = new LowerRent[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR:can't retrieve the oldest inspection data. " + e.getMessage());
        }

        return output;
    }

    /*
     * - getCategoryAvgRent()
     *
     * - This method makes a call to the DB to get the average rent of each category of student
     * for every type of housing.
     * 
     * - Param: None - Return: the list of average rents.
     */
    @RequestMapping(value ="/getCategoryAvgRent", method = RequestMethod.GET)
    @ResponseBody
    public CategoryAvgrRent[] getCategoryAvgRent()
    {
        CategoryAvgrRent[] output = null;

        try
        {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet answer = statement.executeQuery("SELECT isaacp.Student.Category as StudentCategory, AVG(isaacp.StudentLease.MonthlyCost) as AverageMonthlyCost FROM isaacp.Student JOIN isaacp.StudentLease ON (isaacp.Student.ID = isaacp.StudentLease.StudentID) GROUP BY isaacp.Student.Category");
            List<CategoryAvgrRent> expandableList = new ArrayList<>();

            while(answer.next())
            {
                CategoryAvgrRent categoryAvgRent = new CategoryAvgrRent();

                categoryAvgRent.StudentCategory = (answer.getString("StudentCategory"));
                categoryAvgRent.AverageMonthlyCost = (answer.getFloat("AverageMonthlyCost"));

                expandableList.add(categoryAvgRent);
            } 

            int size = expandableList.size();
            output = new CategoryAvgrRent[size];
            for(int i = 0; i < output.length; i++)
            {
                output[i] = expandableList.get(i);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR:can't retrieve average of rents for each student category. " + e.getMessage());
        }

        return output;
    }
}