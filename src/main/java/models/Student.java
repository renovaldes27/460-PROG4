package models;

import java.util.*;
import java.sql.*;
import java.text.*;

/*
 * - Student
 *
 *
 * - This class is used the model a student on the front end.
 *   It is also used for any backend methods that add/get/update students
 *   to the table.
 *
 * */
public class Student {
    private static int nextStudentId; // next id
    private static boolean isInitialized;// is intialized
    public int id; // student ID
    public String name; // student name
    public String address; // student address
    public String phone; // student phone
    public String email; // student email
    public String gender; // student gender
    public String dob; // student dob
    public String category; // student category
    public String major; // student major
    public String minor; // student minor
    public String advisorID; // Advisor ID

    /*
     * - add()
     *
     * - This method makes a call to the DB to add a new student to the student
     * table.
     * 
     * - Param: db statement - Return:None
     */
    public void add(Statement statement) {
        try {
            if (isInitialized == false) {
                isInitialized = true;
                // get the max id that was used in the database
                ResultSet answer = statement.executeQuery("select max(id) from isaacp.student");
                while (answer.next()) // there should be only one result, but java requires us to use .next()
                {
                    nextStudentId = answer.getInt("MAX(ID)") + 1;
                }
            }

            boolean exists = false;
            ResultSet answer = statement.executeQuery("select * from isaacp.student where (id = " + id + " )");
            while (answer.next()) {
                exists = true;
            }

            if (exists) {
                update(statement);
            } else {
                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
                SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
                String dobString = formater.format(input.parse(dob));

                statement.execute("insert into isaacp.student values('" + nextStudentId + "', '" + name + "', '"
                        + address + "', '" + phone + "', '" + email + "', '" + gender + "', '" + dobString + "', '"
                        + category + "', '" + major + "', '" + minor + "', '" + advisorID + "' )");
                nextStudentId++;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't add a new student. " + e.getMessage());
        }
    }

    /*
     * - update()
     *
     * - This method makes a call to the DB to update a an existing student in the
     * student table.
     * 
     * - Param: db statement - Return:None
     */
    private void update(Statement statement) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-DD");
            SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yy");
            String dobString = formater.format(input.parse(dob));

            statement.execute("update isaacp.student set " + "Name = '" + name + "', Address = '" + address
                    + "', PhoneNumber = '" + phone + "', Email = '" + email + "', Gender = '" + gender + "', DOB = '"
                    + dobString + "', Category = '" + category + "', MajorID = '" + major + "', MinorID = '" + minor
                    + "', AdvisorID = '" + advisorID + "' where ( ID = " + id + " )");
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't update student. " + e.getMessage());
            System.err.println("ID = " + id);
            System.err.println("Name = " + name);
            System.err.println("Address = " + address);
            System.err.println("Email = " + email);
            System.err.println("Gender = " + gender);
            System.err.println("DOB = " + dob);
            System.err.println("Category = " + category);
            System.err.println("MajorID = " + major);
            System.err.println("MinorID = " + minor);
            System.err.println("AdviosorID = " + advisorID);
        }
    }

    public void delete(Statement statement) {
        try {
            ResultSet answer = statement.executeQuery("select id from isaacp.studentLease where StudentID = " + id);
            while (answer.next()) {
                int leaseID = answer.getInt(1);
                System.out.println("Lease: " + leaseID);
                statement.execute("delete from isaacp.invoice where LeaseID = " + leaseID);
                statement.execute("delete from isaacp.StudentLease where id = " + leaseID);
            }

            statement.execute("delete from isaacp.student where id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't remove student, id = " + id + "." + e.getMessage());
        }
    }

    /*
     * - getAll()
     *
     * - This method makes a call to the DB to get all the students from the student
     * table.
     * 
     * - Param: db statement - Return:A list of all advisors.
     */
    public static Student[] getAll(Statement statement) {
        Student[] output = null;

        try {
            ResultSet answer = statement.executeQuery(
                    "select isaacp.student.id as id, isaacp.student.name as name, address, phoneNumber, isaacp.student.email, gender, dob, category, major.name as majorid, minor.name as minorid, isaacp.advisor.name as advisorid"
                            + " from isaacp.student"
                            + " join isaacp.department major on (isaacp.student.majorID = major.id)"
                            + " join isaacp.department minor on (isaacp.student.minorID = minor.id)"
                            + " join isaacp.advisor on (isaacp.student.advisorid = isaacp.advisor.id)");

            List<Student> expandableList = new ArrayList<>();

            while (answer.next()) {
                Student tempStudent = new Student();

                tempStudent.id = (answer.getInt("ID"));
                tempStudent.name = (answer.getString("Name"));
                tempStudent.address = (answer.getString("Address"));
                tempStudent.phone = (answer.getInt("PhoneNumber")) + "";
                tempStudent.email = (answer.getString("Email"));
                tempStudent.gender = (answer.getString("Gender"));
                tempStudent.dob = (answer.getDate("DOB")).toString();
                tempStudent.category = (answer.getString("Category"));
                tempStudent.major = (answer.getString("MajorID"));
                tempStudent.minor = (answer.getString("MinorID"));
                tempStudent.advisorID = (answer.getString("AdvisorID"));

                expandableList.add(tempStudent);
            }

            // result set won't allow us to easily get size
            // so we have to copy values from a resizable array, sigh
            int size = expandableList.size();
            output = new Student[size];
            for (int i = 0; i < output.length; i++) {
                output[i] = expandableList.get(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: can't retrieve the students. " + e.getMessage());
        }

        return output;
    }
}
