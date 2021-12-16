package org.example;


import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Запустить spring_rest
    private final String url = "http://localhost:8080/spring_rest_war_exploded/api/employees";

    public List<Employee> getAllEmployees(){

        ResponseEntity<List<Employee>> responseEntity =
               restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
               });

        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }


    public Employee getEmployee(int id){

        Employee employee = restTemplate.getForObject(url + "/" + id,Employee.class);
        return employee;
    }


    public void saveEmployee(Employee employee){
        int id = employee.getId();
        if (id == 0){
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url, employee, String.class);
            System.out.println("New emp add to dataBase");
            System.out.println(responseEntity.getBody());
        }
        else {
            restTemplate.put(url, employee);
            System.out.println("Emp with id " + id + " was updated");
        }
    }


    public void deleteEmployee(int id){
        restTemplate.delete(url + "/" + id);
        System.out.println("Employee with id = " + id + " was delete from database");

    }
}
