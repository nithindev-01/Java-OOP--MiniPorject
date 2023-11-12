package BusBooking.sqlqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusBooking.code.*;

public class busQueries {

    static public Connection con = connectionmaker.getConnection();

    public static void addBus(bus b) {
        final String SQL = "insert into bus values(?,?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setInt(1, b.getavailSeats());
            stmt.setString(2, b.getFrom());
            stmt.setString(3, b.getTo());
            stmt.setString(4, b.getTravelsName());
            stmt.setString(5, b.getBusId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int searchBus(String f, String t) {
        final String SQL = "select * from bus where Source = ? and destination = ? ";
        int count = 0;
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, f);
            stmt.setString(2, t);
            ResultSet rs = stmt.executeQuery();
            System.out.println("available Seats  |  Source  |  Destination  |  TravelsName  |  Busid");
            while (rs.next()) {
                System.out.println(rs.getInt("NoOfSeats") + "    |    " + rs.getString("Source") + "    |    "
                        + rs.getString("destination") + "    |    " + rs.getString("TravelsName") + "    |    " + rs.getString("id"));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void updateBus(String id){
        final String SQL = "update bus set NoOfSeats = NoOfSeats+1 where id = ? ";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBusSeat(String id){
        final String SQL = "update bus set NoOfSeats = NoOfSeats-1 where id = ? ";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateBus() {
        final String SQL = "truncate bus";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
