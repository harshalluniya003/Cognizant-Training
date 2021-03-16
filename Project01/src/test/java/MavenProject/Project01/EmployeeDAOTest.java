package MavenProject.Project01;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import MavenProject.Project01.Employee;
import MavenProject.Project01.EmployeeMapper;
import junit.framework.TestCase;
import MavenProject.Project01.EmployeeDAO;



public class EmployeeDAOTest extends TestCase {
	@Test
	void testGetAllEmployees() {
		Resource resource=new ClassPathResource("applicationContext.xml");  
	    BeanFactory factory=new XmlBeanFactory(resource);  
	    EmployeeDAO empdao=(EmployeeDAO)factory.getBean("daoOfEmp");  
		
		// EmployeeDAO empdao = new EmployeeDAO(); 
		List<Employee> l = empdao.getAllEmployees();
		Assertions.assertEquals(107, l.size());
	}
	
	@Test
	void testGetConnection() {
		EmployeeDAO empdao = new EmployeeDAO();
		Connection c = empdao.getConnection();
		assertNotNull(c);
		
		
	}
	
	@Test
	void testGetEmpByLname() {
		EmployeeDAO empdao = new EmployeeDAO();
		
		List<Employee> eList = empdao.getEmpByLname("Cambrault");
		
		
		System.out.println(eList.get(0).getName());
	}
	
	// write a test case for getAllDepartments
	
	@Test
	void testJDBCTemplate() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
		JdbcTemplate jdb = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		
		
		String sql  ="select * from employees";
		List<Employee> l = jdb.query(
				   sql, new EmployeeMapper());
		System.out.println(l.get(0).getName());
		Assertions.assertEquals(107, l.size());
	}
	

}