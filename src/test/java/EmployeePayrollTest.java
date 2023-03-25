import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollTest{
    EmployeePayrollObj employeePayrollObj = new EmployeePayrollObj();
    @Test
    public void  afterUpdatingSalaryShouldReturnTrue() throws Exception{
        DBConnection con = new DBConnection();
        Assert.assertEquals(300000.0, con.getSalaryFromDB("kundan"), 0.0);
    }

}
