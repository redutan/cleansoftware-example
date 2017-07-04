package payday.employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author myeongju.jung
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
