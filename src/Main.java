import java.sql.Date;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		try {
			String sen = "hi", rec = "k", con = "test";
			Scanner scanner = new Scanner(System.in);
			System.out.print("enter date in format YYYY-MM-DD:\n");
			
			String date = scanner.nextLine();
			
			
			Message mes1 = new Message(sen, rec, date, con);
			System.out.print(mes1.toString());
			
		
		}catch (Exception e) {
			System.out.println("error"+ e);
		}
		

	}

}
