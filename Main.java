import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:postgresql://localhost:5432/bank_management";
    static final String USER = "postgres";
    static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to Database!");

            while (true) {
                System.out.println("\n1. View Accounts");
                System.out.println("2. Transfer Money");
                System.out.println("3. Exit");

                int choice = sc.nextInt();

                if (choice == 1) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");

                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("account_id") +
                                " Balance: " + rs.getDouble("balance"));
                    }

                } else if (choice == 2) {
                    System.out.print("From Account: ");
                    int from = sc.nextInt();

                    System.out.print("To Account: ");
                    int to = sc.nextInt();

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();

                    conn.setAutoCommit(false);

                    PreparedStatement ps1 = conn.prepareStatement(
                            "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?");
                    ps1.setDouble(1, amount);
                    ps1.setInt(2, from);
                    ps1.setDouble(3, amount);
                    int rows = ps1.executeUpdate();

                    if (rows > 0) {
                        PreparedStatement ps2 = conn.prepareStatement(
                                "UPDATE accounts SET balance = balance + ? WHERE account_id = ?");
                        ps2.setDouble(1, amount);
                        ps2.setInt(2, to);
                        ps2.executeUpdate();

                        conn.commit();
                        System.out.println("Transfer Successful!");
                    } else {
                        conn.rollback();
                        System.out.println("Insufficient Balance!");
                    }

                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}