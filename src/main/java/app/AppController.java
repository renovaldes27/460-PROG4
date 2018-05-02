package app; 

import models.*;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.*; 

import javax.annotation.PostConstruct; 
import javax.sql.*; 

import java.sql.*; 
import java.text.*; 
import java.util.Date; 
import java.util.*;

@Controller
public class AppController 
{
    @Autowired
    private DataSource dataSource;

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