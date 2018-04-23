package app;

import models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
public class AppController {


    @RequestMapping(value ="/students", method = RequestMethod.GET)
    @ResponseBody
    public Student greeting() {
        Student student = new Student();
        student.setID(27);
        student.setName("Reno");
        student.setAddress("Address");
        student.setPhone("520-909-0123");
        student.setEmail("email@email.com");
        student.setGender('M');
        student.setDob(new Date());
        student.setCategory("Cat");
        student.setMajor("Computer Science");
        student.setMinor("ISTA");
        student.setAdvisorID(10);
        return student;
    }

}


