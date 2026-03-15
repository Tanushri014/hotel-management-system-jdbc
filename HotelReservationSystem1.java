
//in sql command promt we have already created a hotel_db

//hotel_db have table name reservations

//table reservations have columns
// reservation_id 
//guest_name
//room_number 
//contact_number
//reservation_date


//imported all the necessary interfaces
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

//imported all the necessary classes
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


//use right click run java application as we have used a refernced file  jar file of sql 

//the little small button wont work this time it is used when we have a single file only like a plain java application 




public class HotelReservationSystem1 {
    //we donts want the following credentials to be aceesible outside this class so we will be using private 

  private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
private static final String user = "your_mysql_username"; // add your MySQL username
private static final String password = "your_mysql_password"; // add your MySQL password

    public static void main(String[] args) {

        //firstly we need to load the driver in which we might get error

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver error: " + e.getMessage());
        }
        //we need to build a connection in which we might get error
        try (Connection con = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n------ HOTEL RESERVATION MENU ------");
                System.out.println("1. Reserve a Room");
                System.out.println("2. View All Reservations");
                System.out.println("3. Get Room by Reservation ID");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Guest Name: ");
                        String guestName = sc.nextLine();

                        System.out.print("Enter Room Number: ");
                        int roomNumber = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Contact Number: ");
                        String contactNumber = sc.nextLine();

                        String insertQuery = "INSERT INTO reservations(guest_name, room_number, contact_number) VALUES (?, ?, ?)";
                        try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
                            pstmt.setString(1, guestName);
                            pstmt.setInt(2, roomNumber);
                            pstmt.setString(3, contactNumber);
                            pstmt.executeUpdate();
                            System.out.println("Reservation added successfully!");
                        }
                        break;

                    case 2:
                        String viewQuery = "SELECT * FROM reservations";
                        try (Statement stmt = con.createStatement();
                             ResultSet rs = stmt.executeQuery(viewQuery)) {
                            while (rs.next()) {
                                System.out.println("ID: " + rs.getInt("reservation_id") +
                                        ", Guest: " + rs.getString("guest_name") +
                                        ", Room: " + rs.getInt("room_number") +
                                        ", Contact: " + rs.getString("contact_number") +
                                        ", Date: " + rs.getTimestamp("reservation_date"));
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Reservation ID: ");
                        int searchId = sc.nextInt();

                        String searchQuery = "SELECT room_number, guest_name FROM reservations WHERE reservation_id = ?";
                        try (PreparedStatement pstmt = con.prepareStatement(searchQuery)) {
                            pstmt.setInt(1, searchId);
                            try (ResultSet rs = pstmt.executeQuery()) {
                                if (rs.next()) {
                                    System.out.println("Room Number: " + rs.getInt("room_number"));
                                    System.out.println("Guest Name: " + rs.getString("guest_name"));
                                } else {
                                    System.out.println("No reservation found with ID " + searchId);
                                }
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Reservation ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new Contact Number: ");
                        String newContact = sc.nextLine();

                        System.out.print("Enter Name to update: ");
                        String newName = sc.nextLine();

                        String updateQuery = "UPDATE reservations SET contact_number = ?,guest_name = ? WHERE reservation_id = ?";
                        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
                            
                             pstmt.setString(1, newName);
                             pstmt.setString(2, newContact);
                            pstmt.setInt(3, updateId);
                            int rows = pstmt.executeUpdate();  
                            if (rows > 0) {
                                System.out.println(" Reservation updated!");
                            } else {
                                System.out.println(" Reservation not found!");
                            }
                        }
                        break;

                    case 5:
                        System.out.print("Enter Reservation ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteQuery = "DELETE FROM reservations WHERE reservation_id = ?";
                        try (PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {
                            pstmt.setInt(1, deleteId);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0) {
                                System.out.println("Reservation deleted!");
                            } else {
                                System.out.println(" Reservation not found!");
                            }
                        }
                        break;


                        //break inside a switch only breaks out thw switch block ,not the while loop
                        //the control will go back to while loop

                        //return will exits the entire method not just switch or while and go back to caller 

                    case 6:
                        System.out.println("Exiting... Thank you for using Hotel Management System!");
                        return;

                    default:
                        System.out.println(" Invalid choice. Try again.");
                        //if we would have used break in default then after entering a wrong choice the program will stop .....
                        //but we want to give user choice till he or she enters exist choice .....
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
