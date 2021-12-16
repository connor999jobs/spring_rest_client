package org.example;

import org.example.config.MyConfig;
import org.example.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

// Список всех работников
        List<Employee> allEmployees = communication.getAllEmployees();
        System.out.println(allEmployees);

        System.out.println("------------------");

        // Список  работников по ID
        Employee empById = communication.getEmployee(1);
        System.out.println(empById);

        System.out.println("------------------");

        // Создание или обновление работника.
//        Employee emp = new Employee("Anton", "Yagodka", "AirLine", 850);
//        emp.setId(11);//для изменения уже готового работника
//        communication.saveEmployee(emp);

        System.out.println("------------------");

        communication.deleteEmployee(11);// удаление
    }
}
