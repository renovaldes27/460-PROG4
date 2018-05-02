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
    private DataSource dataSource; // IF we don't need jdbc template (see comment below), can this be changed to just a Statement?

    private Statement statement;

    @PostConstruct
    private void postConstruct() 
    {
        try
        {
            // create the statement that every function will use
            statement = dataSource.getConnection().createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't get statement from the database. " + e.getMessage());
        }
    }

    @RequestMapping(value ="/students", method = RequestMethod.GET)
    @ResponseBody
    public Student[]  getStudents() 
    {
        return Student.getAll(statement);
    }

    @RequestMapping(value ="/students", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewStudent(@RequestBody Student jsonString) 
    {
        jsonString.add(statement);
    }


    @RequestMapping(value ="/staff", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewSaff(@RequestBody Staff jsonString)
    {
        jsonString.add(statement); 
    }


    @RequestMapping(value ="/lease", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addNewLease(@RequestBody Lease jsonString) 
    {
        jsonString.add(statement);
    }

    @RequestMapping(value ="/staff", method = RequestMethod.GET)
    @ResponseBody
    public Staff[]  getStaff()
    {
        return Staff.getAll(statement);
    }

    @RequestMapping(value ="/lease", method = RequestMethod.GET)
    @ResponseBody
    public Lease[]  getLease()
    {
        return Lease.getAll(statement);
    }

    @RequestMapping(value ="/getHallManagerInfo", method = RequestMethod.GET)
    @ResponseBody
    public HallManager[] getHallManagerInfo()
    {
        HallManager[] output = null;

        try
        {
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR:can't retrieve the managers. " + e.getMessage());
        }

        return output;
    }

    // for the given student.id, updates all of the other fields.
    // TODO: how to designate when no matching id is found.
    // TODO: optimization, change only some fields
    public void updateStudent(Student student)
    {
        student.update(statement);     
    }

    // for the given student.id, delete the student with matching id
    // TODO: how to designate when no matching id is found.
    public void deleteStudent(Student student)
    {
        student.delete(statement);
    }

    
    @RequestMapping(value ="/getLowerRents", method = RequestMethod.GET)
    @ResponseBody
    public LowerRent[] getLowerRents(@RequestParam(value="studentID", defaultValue="0") int studentID)
    {
        LowerRent[] output = null;
        try
        {
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
}


