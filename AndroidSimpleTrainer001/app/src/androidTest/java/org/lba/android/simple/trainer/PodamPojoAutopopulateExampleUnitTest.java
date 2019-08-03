package org.lba.android.simple.trainer;

import org.junit.Test;
import org.lba.android.simple.trainer.model.Employee;


import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PodamPojoAutopopulateExampleUnitTest {
    @Test
    public void employeeAutopopulateTest(){
        PodamFactory factory = new PodamFactoryImpl();
        Employee myEmployeeOne = factory.manufacturePojo(Employee.class);
        System.out.println("** Employee one - generation - START");
        System.out.println(myEmployeeOne.toString());
        System.out.println("** Employee one - generation - END");
        //
        System.out.println("** Employee two - generation - START");
        Employee myEmployeeTwo = factory.manufacturePojoWithFullData(Employee.class);
        System.out.println(myEmployeeTwo.toString());
        System.out.println("** Employee two - generation - END");
        //List
        System.out.println("** Employee list - generation - START");
        List<Employee> myEmployeeList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Employee employeeElement = factory.manufacturePojoWithFullData(Employee.class);
            myEmployeeList.add(employeeElement);
        }
        System.out.println("** Employee list - generation - END");
        //
        System.out.println("** Employee print - generation - START");
        for (Employee currentEmployee: myEmployeeList) {
            System.out.println(currentEmployee.toString());
        }
        System.out.println("** Employee print - generation - END");
        //
        assertEquals(4, 2 + 2);
    }
}