package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* Student: Isaac Plunkett, Reno Valdes, Hazza Alkaabi
 * Course: Data Structures CS 460
 * Assignment: 4
 * Instructor: McCann
 * TA: Aakash Rathore
 * Due Date: May 1, 2018
 * 
 * Application
 * Takes no command line arguments.
 * Connects to the oracle database using JDBC.
 * Allows the user to manage a student housing databse from a website.
 * 
 * Language: Java 8
 * Compilation: mvn compile
 * Run: mvn spring-boot:run, then connect to localhost at appropriate port number
 * Additional run time requirements:  There must be an oracle server at "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle"
 *      Must have previously installed ojdbc using mvn.  see comment below for command.
 */

// mvn install:install-file -Dfile=./ojdbc14.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=11.2.0 -Dpackaging=jar

@SpringBootApplication
public class Application implements CommandLineRunner
{

    // can this just be a Statement? see comment in appController
    // do we have to close this?
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
        ds.setUrl("jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle");
        ds.setUsername("isaacp");
        ds.setPassword("a7031");
        return ds;
    }

    /*
     * - main()
     *
     * - This method is the start of the server program
     * 
     * - Param: array of command line arguments - Return:None
     */
    public static void main(String[] args) 
    {
        SpringApplication.run(Application.class, args);
    }

    /*
     * - run()
     *
     * - This method is called by maven, and prints a message when the server is ready
     * 
     * - Param: strings that are never used - Return:None
     */
    @Override
    public void run(String... strings) throws Exception 
    {
		System.out.println("Application Started!");
    
	}

}
