import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.Scanner;

class Log_In
{
   String main()
   {
       try
       {
           Scanner CIN = new Scanner(System.in); // for int
           Scanner s = new Scanner(System.in);

           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_aid" , "root" , "mysqlpass");

           int done = 0 ;


           String u_name = "NULL";

           while( done == 0  )
           {
                System.out.println("Please Enter correct username and password");


                   System.out.print("Enter username:");
                   String User_Name = s.nextLine();
                   System.out.print("Enter password:");
                   String Password = s.nextLine();

                   System.out.println("");


                   Statement st = con.createStatement();
                   String query = "SELECT * FROM user";
                   ResultSet rs = st.executeQuery(query);

                   while(rs.next() )
                   {
                       String u_name2 = rs.getString("User_Name");
                       String u_pass = rs.getString("Password");

                       if(u_name2.equals(User_Name) && Password.equals(u_pass) )
                       {
                           done = 1; break;
                       }
                   }


               if(done > 0)
               {
                   u_name = User_Name;
                   break;
               }
           }

           System.out.println("Login Succefully");
           System.out.println("");

           return u_name;


       }
       catch(ClassNotFoundException e)
       {
               System.out.println("NO");
       }
       catch(SQLException ex)
       {
               System.out.println(ex.getMessage() );
       }

       return "NULL";
   }


}


class Sign_Up
{
    String main()
    {
        try
        {
            Scanner CIN = new Scanner(System.in);
            Scanner s = new Scanner(System.in);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");


               System.out.println("");
               String queryM = "INSERT INTO user" + "( Name , Age , Sex , Location , Contact_Number , E_mail , User_Name , Password  ) " +  " VALUES (?,?,?,?,?,?,?,?) ";
               String queryM2 = "SELECT * FROM user";

               PreparedStatement Pst = con.prepareStatement(queryM);
               Statement st = con.createStatement();

               boolean done = false;

               String Password = null , User_Name = null;

               while( !done )          // TO CHECK IF USERNAME EXISTS
               {
                   int c = 0 ;

                   System.out.print("Enter username:");
                   User_Name = s.nextLine();

                   ResultSet rs = st.executeQuery(queryM2);

                   while(rs.next() )
                   {
                       String u_name = rs.getString("User_Name");

                       if(u_name.equals(User_Name) )
                       {
                           System.out.println("User-Name already exists. Please Enter a new User-Name"); c++;
                           break;
                       }
                   }

                   if(c == 0)done = true;
               }

                System.out.print("Enter password:");
                Password = s.nextLine();

               System.out.print("Enter First-Name: ");
               String First_Name = s.nextLine();



            System.out.print("Enter Your Age: ");
            int Age = CIN.nextInt();


            System.out.print("Enter Your Sex: ");
            String Sex = s.nextLine();


            System.out.print("Enter Your Address: ");
            String Location = s.nextLine();


            System.out.print("Enter Your Contact number: ");
            String Contact_Number = s.nextLine();

            System.out.print("Enter Your E-mail id: ");
            String E_mail = s.nextLine();

            Pst.setString(1,First_Name);
            Pst.setInt(2,Age);
            Pst.setString(3,Sex);
            Pst.setString(4,Location);
            Pst.setString(5,Contact_Number);
            Pst.setString(6,E_mail);
            Pst.setString(7,User_Name);
            Pst.setString(8,Password);


            Pst.executeUpdate();

            System.out.println("Registerd Successfully");

             System.out.println("----------------------------------------");
                 System.out.println("");

            return User_Name;

        }
        catch(ClassNotFoundException e)
        {
                System.out.println("NO");
        }
        catch(SQLException ex)
        {
                System.out.println(ex.getMessage() );
        }
        return "NULL";

    }


}

class Contribute
{

    void Hire_For_Job(String x)
    {


        try
        {
            Scanner CIN = new Scanner(System.in); // for int
            Scanner s = new Scanner(System.in);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");

            // userid
            String User_Name = x;




        String Job_Position,Company_Name,Qualifications,Location,Note_Captions , Salary;
        int  Working_Hours;

        Scanner scstr = new Scanner(System.in);
        Scanner scint = new Scanner(System.in);

        System.out.println("This pandemic had an adverse effect on the lives of people belonging to different sections of society,uprooting many of them from their jobs.If you wish to contribute by employing them, go ahead and fill the details of the job you can offer them :)");
        System.out.println("Here is the database listing the opportunity properties for you:-");

        System.out.println();
        System.out.println();

        System.out.print("Enter the job position you are willing to offer : ");
        Job_Position = scstr.nextLine();


        System.out.print("Please enter the company's name : ");
        Company_Name = scstr.nextLine();

        System.out.print("Enter the Salary you will to offer : ");
        Salary = scint.nextLine();

        System.out.print("Mention the qualifications the employee must possess : ");
        Qualifications = scstr.nextLine();

        System.out.print("The city in which you will be offering job : ");
        Location = scstr.nextLine();

        System.out.print("The points needed to be kept in mind regarding job : ");
        Note_Captions = scstr.nextLine();

        System.out.print("Working hours expected : ");
        Working_Hours = scint.nextInt();

        String query = "insert into jobs" + "(Job_Position , Company_Name , Qualifications , Location , Note_Captions , Salary , Working_Hours , User_Name ) "  + "VALUES(?,?,?,?,?,?,?,?)" ;
        PreparedStatement Pst = con.prepareStatement(query);

        Pst.setString(1,Job_Position);
        Pst.setString(2,Company_Name);
        Pst.setString(3,Qualifications);
        Pst.setString(4,Location);
        Pst.setString(5,Note_Captions);
        Pst.setString(6,Salary);
        Pst.setInt(7,Working_Hours);
        Pst.setString(8,User_Name);

        Pst.executeUpdate();

        System.out.println();

        System.out.println("Thank-You For Registering Job Positions");

        System.out.println("----------------------------------------");
        System.out.println("");


        }

        catch(ClassNotFoundException e)
        {
                System.out.println("NO");
        }
        catch(SQLException ex)
        {
                System.out.println(ex.getMessage() );
        }
    }

    void Medications(int choice , String User_Name)
    {
    try
    {
        Scanner CIN = new Scanner(System.in); // for int
        Scanner s = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");




            Scanner scstr = new Scanner(System.in);
            Scanner scint = new Scanner(System.in);




            if(choice==2)
            {
                String Blood_Group,Date_Recovered,Address;
                System.out.print("Enter your blood group: ");
                Blood_Group = scstr.nextLine();
                System.out.print("Enter the date you tested negative (mm/dd/yy) : ");
                Date_Recovered = scstr.nextLine();
                System.out.print("Enter your residential address: ");
                Address = scstr.nextLine();

                String query = "Insert into Plasma" + "(Blood_Group , Date_Recovered , Address , User_Name) " + "VALUES(?,?,?,?)";
                PreparedStatement Pst = con.prepareStatement(query);

                Pst.setString(1,Blood_Group);
                Pst.setString(2,Date_Recovered);
                Pst.setString(3,Address);
                Pst.setString(4,User_Name);
                Pst.executeUpdate();
                //DBMS vala part

                System.out.println("Thank-You For Helping. We'll Contact You once we find the patient :) ");

            }
            else if(choice == 3)
            {
                String Field,Available_Between;
                int Fees;
                System.out.print("Enter your speciality: ");
                Field = scstr.nextLine();
                System.out.print("Enter your fees: ");
                Fees = scint.nextInt();
                System.out.print("Enter the time you will be available: ");
                Available_Between = scstr.nextLine();

                System.out.println("Thank-You For Helping. We'll Contact You once we find the patient :) ");

                String query = "Insert into Doctors" + "(Field , Available_Between , Fees , User_Name) " + "VALUES(?,?,?,?)";
                PreparedStatement Pst = con.prepareStatement(query);

                Pst.setString(1,Field);
                Pst.setString(2,Available_Between);
                Pst.setInt(3,Fees);
                Pst.setString(4,User_Name);
                Pst.executeUpdate();

                //DBMS vala part
            }



            System.out.println("----------------------------------------");
            System.out.println("");


    }
    catch(ClassNotFoundException e)
    {
                System.out.println("NO");
    }
    catch(SQLException ex)
    {
                System.out.println(ex.getMessage() );
    }

    }

    void Donations(String User_Name)
{
    try
    {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");

        int Money = 0;
        Scanner scstr = new Scanner(System.in);
        Scanner scint = new Scanner(System.in);

          System.out.println("----------------------------------------");
            System.out.println("");

        String Donor_Name;
        System.out.print("Its so humble of you that you wish to help the one in need with donations. \nPlease enter your name : ");

        Donor_Name = scstr.nextLine();

        System.out.print("Enter the amount to be donated: ");

        Money = scint.nextInt();

        String query = "Insert into Donors" + "(Donor_Name , Amount , User_Name) " + "VALUES(?,?,?)";
                PreparedStatement Pst = con.prepareStatement(query);

                Pst.setString(1,Donor_Name);
                Pst.setInt(2,Money);
                Pst.setString(3,User_Name);

                Pst.executeUpdate();

                System.out.println("Thank-You For Donating");

                 System.out.println("----------------------------------------");
                 System.out.println("");



    }
    catch(ClassNotFoundException e)
    {
            System.out.println("NO");
    }
    catch(SQLException ex)
    {
            System.out.println(ex.getMessage() );
    }
}

}

 class Seek_Help
{
    void Jobs()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");

             System.out.println("----------------------------------------");
                 System.out.println("");


            System.out.println("Available jobs are \n");

            String query = "select * from jobs   inner join user on jobs.User_Name = user.User_Name group by user.User_Name";


		Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			String Company_Name = rs.getString("Company_Name");
			String Job_Position = rs.getString("Job_Position");
			String Salary = rs.getString("Salary");
			String Qualifications = rs.getString("Qualifications");
			String Location = rs.getString("Location");
			String Working_Hours = rs.getString("Working_Hours");
            String Note = rs.getString("Note_Captions");
            String Email = rs.getString("E_Mail");
            String Contact_Number = rs.getString("Contact_Number");

			System.out.println("Company_Name : " + Company_Name);
			System.out.println("Job_Position : " + Job_Position);
			System.out.println("Salary : " + Salary);
			System.out.println("Qualifications : " + Qualifications);
			System.out.println("Location : " + Location);
			System.out.println("Working_Hours : " + Working_Hours);
            System.out.println("Additional Notes : " + Note);
            System.out.println("E_Mail : " + Email);
            System.out.println("Contact_Number : " + Contact_Number);

            System.out.println();

            System.out.println("----------------------------------------");
            System.out.println("");
        }

        }
        catch(ClassNotFoundException e)
        {
                System.out.println("NO");
        }
        catch(SQLException ex)
        {
                System.out.println(ex.getMessage() );
        }

    }

    void seekmoney()
    {
     try
     {
        Scanner scint = new Scanner(System.in);
    	Scanner scstr = new Scanner(System.in);



        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");


            System.out.println("----------------------------------------");
            System.out.println("");

            System.out.println("Donors Available are : \n");
        String query = "select * from Donors inner join user on Donors.User_Name = user.User_Name group by user.User_Name";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);


        while(rs.next() )
        {
            String Name = rs.getString("Donor_Name");
            String Contact_Number = rs.getString("Contact_Number");
            String E_Mail = rs.getString("E_Mail");

            System.out.println("Name of the donor: " + Name );
            System.out.println("Contact-Number : " + Contact_Number);
            System.out.println("E_Mail: " + E_Mail);

            System.out.println("---------------------------------------------------------------------");
		System.out.println();
        }

		System.out.println();

     }
        catch(ClassNotFoundException e)
        {
                System.out.println("NO");
        }
        catch(SQLException ex)
        {
                System.out.println(ex.getMessage() );
        }


    }

	void doctors()
	{
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");

         System.out.println("----------------------------------------");
         System.out.println("");


        System.out.println("Doctors available in your area are : \n ");

        String query = "select * from Doctors inner join user on Doctors.User_Name = user.User_Name group by user.User_Name";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{

			String Doctor_Name = rs.getString("Name");
			String Fees = rs.getString("Fees");
                        String Meeting_Hours = rs.getString("Available_Between");
                        String Location = rs.getString("Location");
                        String E_Mail = rs.getString("E_Mail");
                        String Contact_Number = rs.getString("Contact_Number");

                        System.out.println("Doctor Name : " + Doctor_Name);
                        System.out.println("Fees : " + Fees);
                        System.out.println("Meeting_Hours : " + Meeting_Hours);
                        System.out.println("Address : " + Location);
                        System.out.println("E-Mail : " + E_Mail);
                        System.out.println("Contact_Number : " + Contact_Number);

                        	System.out.println();
		System.out.println("---------------------------------------------------------------------");


		}

		System.out.println();
    }
    catch(ClassNotFoundException e)
    {
            System.out.println("NO");
    }
    catch(SQLException ex)
    {
            System.out.println(ex.getMessage() );
    }

    }



	void Plasma()
	{
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");

        System.out.println("---------------------------------------------------------------------");
		System.out.println();

        System.out.println("Plasma Donors available  are : ");
        System.out.println();

        String query = "select * from Plasma inner join user on Plasma.User_Name = user.User_Name group by user.User_Name";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			String Donor_Name = rs.getString("Name");
            String Date_Recovered = rs.getString("Date_Recovered");
            String Location = rs.getString("Address");
            String Blood_Group = rs.getString("Blood_Group");
            String Contact_Number = rs.getString("Contact_Number");

            System.out.println("Donor Name: " + Donor_Name);
            System.out.println("Date Recovered: " + Date_Recovered);
            System.out.println("Address: " + Location);
            System.out.println("Blood Group: " + Blood_Group);
            System.out.println("Contact_Number: " + Contact_Number);

            System.out.println("---------------------------------------------------------------------");
		System.out.println();
        }

		System.out.println();

    }
    catch(ClassNotFoundException e)
    {
            System.out.println("NO");
    }
    catch(SQLException ex)
    {
            System.out.println(ex.getMessage() );
    }

	}



}

class analyzer
{
  void analyzerfun()
  {
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_Aid" , "root" , "mysqlpass");
         System.out.println("----------------------------------------");
         System.out.println("");


        Scanner s = new Scanner(System.in);

        String query = "select State from analyzer";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next())
        {

          String State = rs.getString("State");
          System.out.println(State);
        }

        System.out.println();
        System.out.println();
        System.out.println("Here is the list of all states. Type name of any one to know about its Covid stats.");
        System.out.println();
        String Statein = s.nextLine();

        query = "select * from analyzer where State = '" + Statein + "';";

        rs = st.executeQuery(query);
        while(rs.next())
        {
          int cases = rs.getInt("Cases");
          int recovered = rs.getInt("Recovered");
          int deaths = rs.getInt("Deaths");
          System.out.println("Total Cases = " + cases);
          System.out.println("Recovered = " + recovered);
          System.out.println("Deaths = " + deaths);
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println();
    }
    catch(ClassNotFoundException e)
    {
            System.out.println("NO");
    }
    catch(SQLException ex)
    {
            System.out.println(ex.getMessage() );
    }
  }
}

public class Cov_aid
{

    public static void main(String[] args)
    {

        try
        {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cov_aid" , "root" , "mysqlpass");

            System.out.println("Welcome To Cov_aid. ");
            System.out.println("Press 1 for Login ");
            System.out.println("Press 2 for Sign-up");

            System.out.println("----------------------------------------");
            System.out.println("");

            Scanner CIN = new Scanner(System.in);
            Scanner s = new Scanner(System.in);


            int flag = CIN.nextInt();

            String U_NAME  = "NULL";

            if(flag == 1)
            {
               Log_In login = new Log_In();
               U_NAME = login.main();
            }
            else if(flag == 2)
            {
                Sign_Up signup = new Sign_Up();
                U_NAME = signup.main();
            }

             System.out.println("");

                 int Task = 0;

                Contribute c1 = new Contribute();
                Seek_Help s1 = new Seek_Help();

                while(Task != 10)
                {
                    System.out.println("Press 1 To List Jobs Openings");
                    System.out.println("Press 2 To Donate Plasma");
                    System.out.println("Press 3 If You are a Doctor and want to treat Patients");
                    System.out.println("Press 4 To Provide Financial Help");
                    System.out.println("Press 5 To Seek Donations");
                    System.out.println("Press 6 To Meet a Doctor");
                    System.out.println("Press 7 To Look For Plasma Donors");
                    System.out.println("Press 8 To Look For Jobs ");
                    System.out.println("Press 9 For Covid19 Analyzer");
                    System.out.println("Press 10 To Log-Out");

                    Task = CIN.nextInt();

                    if(Task == 1)
                    {
                      c1.Hire_For_Job(U_NAME);
                    }
                    else if(Task == 2 || Task == 3)
                    {
                        c1.Medications(Task , U_NAME);
                    }
                    else if(Task == 4)
                    {
                        c1.Donations(U_NAME);
                    }
                    else if(Task == 5)
                    {
                        s1.seekmoney();
                    }
                    else if(Task == 6)
                    {
                        s1.doctors();
                    }
                    else if(Task == 7)
                    {
                        s1.Plasma();
                    }
                    else if(Task == 8)
                    {
                        s1.Jobs();
                    }
                    else if(Task == 9)
                    {
                        analyzer analyze = new analyzer();
                        analyze.analyzerfun();
                    }



                }



            System.out.println("Thank-You  " + U_NAME + " for using Cov-aid");

        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error-Connectiong to Cov_aid");
            System.out.println(e.getMessage() );

        }
        catch(SQLException ex)
        {
            System.out.println("Error-Connecting to Cov_aid");
            System.out.println(ex.getMessage() );
        }



    }



}


