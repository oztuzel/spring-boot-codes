package org.monozel.springbootgeneral.rest.relatedStudent;

import jakarta.annotation.PostConstruct;
import org.monozel.springbootgeneral.entity.relatedStudent.StudentPOJO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<StudentPOJO> theStudents;

    // define @PostConstruct to load the student data ... only once!
    // @PostConstruct annotation is meaning this function executing after beans created.
    @PostConstruct
    public void loadData () {
        theStudents = new ArrayList<>();

        theStudents.add(new StudentPOJO("Poornima","Patel"));
        theStudents.add(new StudentPOJO("Ricihi","Lay"));
        theStudents.add(new StudentPOJO("Nima","Kartel"));
        theStudents.add(new StudentPOJO("Orni","Acel"));
    }

    @GetMapping("/students")
    public List<StudentPOJO> getStudents () {

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public StudentPOJO getStudent (@PathVariable int studentId) {
        // just index into the list ...

        // check the studentId in list size
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found " + studentId);
            // if exception throws we can catch @ExceptionHandler annotation and handle
        }

        return theStudents.get(studentId);
    }



}
