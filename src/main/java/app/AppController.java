package app;

import models.Staff;
import models.Student;
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
	    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
        Student student = new Student();
        student.setID(27);
        student.setName("Reno");
        student.setAddress("Address");
        student.setPhone("520-909-0123");
        student.setEmail("email@email.com");
        student.setGender('M');
        student.setDob(df.format(new Date()));
        student.setCategory("Cat");
        student.setMajor("Computer Science");
        student.setMinor("ISTA");
        student.setAdvisorID(10);
        return new Student[]{student};

        // return Student.getAll(statement);
    }

    @RequestMapping(value ="/staff", method = RequestMethod.GET)
    @ResponseBody
    public Staff[]  getStaff()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
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


