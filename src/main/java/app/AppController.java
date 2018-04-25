package app;

import models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value ="/students", method = RequestMethod.GET)
    @ResponseBody
    public Student[]  getStudents() {
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
    }

}


