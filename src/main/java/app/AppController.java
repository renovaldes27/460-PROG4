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

    
    @RequestMapping(value ="/getRoomWithOldestInspection", method = RequestMethod.GET)
    @ResponseBody
    public OldestInspection[] getRoomWithNewestInspection()
    {
        OldestInspection[] output = null;
        try
        {
            String queString = "SELECT Date, isaacp.Staff.Name as InspectorName, isaacp.Building.Name as BuildingName, isaacp.Room.RoomNumber as RoomNumber, isaacp.Room.StudentID as StudentID, isaacp.Student.Name as StudentName FROM isaacp.Inspection "+
            "join isaacp.Staff on InspectorID = isaacp.Staff.ID "+
            "join isaacp.Room on InspectedRoomID = isaacp.Room.ID "+
            "join isaacp.Building on BuildingID = isaacp.Building.ID "+
            "join isaacp.Student on StudentID = isaacp.Student.ID "+
            "WHERE Date = (SELECT MAX(Date) FROM isaacp.Inspection)";

            ResultSet answer = statement.executeQuery(queString);
            List<OldestInspection> expandableList = new ArrayList<>();

            while(answer.next())
            {
                OldestInspection oldestInspection = new OldestInspection();

                oldestInspection.Date = (answer.getString("Date"));
                oldestInspection.InspectorName = (answer.getString("InspectorName"));
                oldestInspection.BuildingName = (answer.getString("BuildingName"));
                oldestInspection.RoomNumber = (answer.getInt("RoomNumber"));
                oldestInspection.StudentID = (answer.getInt("StudentID"));
                oldestInspection.StudentName = (answer.getString("StudentName"));

                expandableList.add(oldestInspection);
            }

            int size = expandableList.size();
            output = new OldestInspection[size];
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


