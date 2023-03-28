import java.sql.*;

public class DBConnection {
Connection con;
PreparedStatement ps;
ResultSet rs;
EmployeePayrollObj employeePayrollObj;

DBConnection() throws Exception{
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "password");
        System.out.println("connection established........");
    }catch (Exception e){}
}

    public void display() throws Exception {
        ps = con.prepareStatement("SELECT * FROM EMPLOYEE_PAYROLL");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
                    +" "+rs.getDouble(4)+"  "+rs.getDouble(5)
                    +"  "+rs.getDouble(6)+"  "+rs.getDouble(7)
                    +"  "+rs.getDouble(8)+"  "+rs.getDate(9)
                    +"  "+rs.getString(10)+"  "+rs.getString(11));
        }
    }
    public void updateSalary(String name,Double pay) throws Exception {
        ps = con.prepareStatement("UPDATE EMPLOYEE_PAYROLL SET BASIC_PAY = ? WHERE NAME = ?");
        ps.setDouble(1,pay);
        ps.setString(2,name);
        ps.executeUpdate();
        System.out.println("Record updated........");
        employeePayrollObj = new EmployeePayrollObj(pay);
    }
    public double getSalaryFromDB(String name) throws Exception{
        ps = con.prepareStatement("SELECT BASIC_PAY FROM EMPLOYEE_PAYROLL WHERE NAME = ?");
        ps.setString(1,name);
        rs = ps.executeQuery();
        double sal=0;
        while (rs.next()){
            sal= rs.getDouble(1);
        }
        return sal;
    }
    public void close() throws Exception{
        con.close();
        System.out.println("Connection closed.....");
    }

    public void updateDate(String date) throws Exception {
        ps = con.prepareStatement("SELECT * FROM employee_payroll WHERE CAST(? AS DATE) AND DATE(NOW())");
        ps.setDate(1, java.sql.Date.valueOf(date));
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
                    +" "+rs.getDouble(4)+"  "+rs.getDouble(5)
                    +"  "+rs.getDouble(6)+"  "+rs.getDouble(7)
                    +"  "+rs.getDouble(8)+"  "+rs.getDate(9)
                    +"  "+rs.getString(10)+"  "+rs.getString(11));
        }
    }

    public void FindMultipleValues() throws Exception{
        System.out.println("------------------------------------------------------");
        ps = con.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("sum of salaries of male: "+ rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("sum of salaries of females: "+ rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Average of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Average of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Maximum of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Maximum of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Minimum of salaries of males: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Minimum of salaries of females: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Number of male employee: " + rs.getInt(1));
        }
        ps = con.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("Number of female employee: " + rs.getInt(1));
        }
        System.out.println("------------------------------------------------------");
    }

    public void addEmployee(String name, String gender, double basic, String date, String phone, String address) throws Exception {
    double deduction = (basic * 0.2);
    double taxablePay = basic - deduction;
    double tax = (taxablePay * 0.1);
    double netPay = (basic - tax);
        Date date1 = new Date(0000-00-00);
        ps = con.prepareStatement("INSERT INTO `payroll_service`.`employee_payroll` (`id`, `name`, `gender`, `basic_pay`, `deductions`, `taxable_pay`, `tax`, `net_pay`, `start`, `employee_phone`, `address`) VALUES ('5', 'tanuja', 'F', '400000', '30000', '20000', '1500', '450000', '2020-01-15', '93287299', 'India');");
        ps.setInt(1,5);
        ps.setString(2,name);
        ps.setString(3,gender);
        ps.setDouble(4,basic);
        ps.setDouble(5,deduction);
        ps.setDouble(6,taxablePay);
        ps.setDouble(7,tax);
        ps.setDouble(8,netPay);
        ps.setDate(9,date1.valueOf(date));
        ps.setString(10,phone);
        ps.setString(11,address);
        ps.executeUpdate();
        System.out.println("Record updated........");
    }
}
