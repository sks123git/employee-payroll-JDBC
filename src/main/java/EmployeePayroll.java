import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmployeePayroll {
    public static void main(String[] args) throws Exception{
        DBConnection con = new DBConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = -1;
        String name,date;
        Double pay;
        while (choice!=0){
            System.out.println("Enter your choice:\n1. DISPLAY EMPLOYEE PAYROLL\n2. UPDATE SALARY\n3. GET EMPLOYEE LIST WHO " +
                    "JOINED IN PARTICULAR DATE RANGE\n0. EXIT");

            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1: con.display();
                break;
                case 2:
                    System.out.println("Enter the name to update base pay");
                    name = br.readLine();
                    System.out.println("Enter the basic pay to update");
                    pay = Double.parseDouble(br.readLine());
                    con.updateSalary(name,pay);
                    break;
                case 3:
                    System.out.println("Enter the date in format eg. '2020-01-10' ");
                     date = br.readLine();
                    con.updateDate(date);
                    break;
                case 0:
                con.close();
                break;
            }
        }
    }
}
