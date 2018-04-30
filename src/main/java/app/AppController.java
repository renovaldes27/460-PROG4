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
        Invoice dummy = new Invoice();
        dummy.id = 1;
        dummy.DatePaid= "1999-12-12";
        dummy.leaseID = "3";
        dummy.semester = "Spring";
        dummy.paymentDueDate = "2001-12-12";
        return new Invoice[]{dummy};
    }

    @RequestMapping(value ="/inspect", method = RequestMethod.GET)
    @ResponseBody
    public Inspection[]  getInspections() 
    {
        Inspection result = new Inspection();
        result.id=1;
        result.roomString="some2";
        result.staffName = "Bob";
        result.inspectionDate="2000-10-10";
        result.condition = "terrible";
        result.action = "eviction";
        
        return new Inspection[]{result};
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
    public void addNewInspection(@RequestBody Building jsonString) 
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
}