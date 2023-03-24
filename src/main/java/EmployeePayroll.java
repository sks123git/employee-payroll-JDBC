import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmployeePayroll {
    public static void main(String[] args) throws Exception{
        DBConnection con = new DBConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = -1;
        while (choice!=0){
            System.out.println("Enter your choice:\n1. DISPLAY EMPLOYEE PAYROLL\n2. UPDATE\n0. EXIT");
            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1: con.display();
                break;
                case 2:
                    System.out.println("Enter the name to update base pay");
                    String name = br.readLine();
                    System.out.println("Enter the basic pay to update");
                    Double pay = Double.parseDouble(br.readLine());
                    con.update(name,pay);
                    break;
                case 0:
                con.close();
                break;
            }
        }
    }
}
