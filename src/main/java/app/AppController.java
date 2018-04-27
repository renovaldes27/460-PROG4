package app;

import models.Staff;
import models.Student;
import models.HallManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
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
    private JdbcTemplate jdbcTemplate;  // do we need this?  the query function looks over engineered

    private Statement statement;

    @PostConstruct
    private void postConstruct() 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
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
	    DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        Student student = new Student();
        student.id = 27;
        student.name ="Reno";
        student.address = "Address";
        student.phone = "520-909-0123";
        student.email = "email@email.com";
        student.gender = 'M';
        student.dob = df.format(new Date());
        student.category = "Cat";
        student.major = "Computer Science";
        student.minor = "ISTA";
        student.advisorID = 10;
        return new Student[]{student};

        // return Student.getAll(statement);
    }

    @RequestMapping(value ="/staff", method = RequestMethod.GET)
    @ResponseBody
    public Staff[]  getStaff()
    {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        Staff staff = new Staff();
        staff.id = 27;
        staff.name = "Reno";
        staff.address = "Address";
        staff.email = "staff@email.com";
        staff.gender = 'M';
        staff.dob = df.format(new Date());
        staff.jobTitle = "Head CS Professor";
        staff.location = "University of Arizona";
        return new Staff[]{staff};
    }
    @RequestMapping(value ="/getHallManagerInfo", method = RequestMethod.GET)
    @ResponseBody
    public HallManager[] getHallManagerInfo()
    {
        HallManager[] output = null;

        try
        {
            ResultSet answer = statement.executeQuery("select isaacp.staff.Name as ManagerName, isaacp.building.TelephoneNumber, isaacp.building.name as BuildingName from isaacp.staff join isaacp.building on (isaacp.building.managerID == isaacp.staff.id)");
            
            answer.last();
            int size = answer.getRow() - 1;
            answer.beforeFirst();
            output = new HallManager[size];

            for(int i = 0; answer.next(); i++)
            {
                HallManager manager = new HallManager();

                //manager.ID = (answer.getInt("ManagerName"));
                //manager.TelephoneNumber = (answer.getInt("TelephoneNumber"));
                //manager.BuildingName = (answer.getInt("BuildingName"));

                output[i] = manager;
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the managers. " + e.getMessage());
        }

        return output;
    }


    // the id field of input student will be ignored.
    // id will be uniqly assigned
    public void addStudent(Student student)
    {
        student.add(statement);
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
}


