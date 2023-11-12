package BusBooking.sqlqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import BusBooking.code.*;

public class driverQueries {
    static public Connection con = connectionmaker.getConnection();

    public static void insertion(driver d) {
        final String SQL = "insert into driver values(?,?,?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, d.getName());
            stmt.setInt(2, d.getAge());
            stmt.setString(3, d.getGender());
            stmt.setString(4, d.getMobileNo());
            stmt.setInt(5, d.getSalary());
            stmt.setInt(6, d.getExperience());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updating(){
        final String SQL = "update driver set salary = salary + 500 where experience > 5";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateDriver() {
        final String SQL = "truncate driver";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
