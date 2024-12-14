import java.sql.SQLException;
import java.util.Scanner;

public interface userManagementInterface {
    void register(Scanner scanner) throws SQLException;
    boolean loginAsAuthor(String username, String password);
    boolean loginAsLearner(String username, String password);
    void adminLogin(Scanner scanner) throws SQLException;
    void closeConnection(); // Add this line
}