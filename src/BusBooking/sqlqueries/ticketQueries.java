package BusBooking.sqlqueries;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusBooking.code.*;

public class ticketQueries {

    static public Connection con = connectionmaker.getConnection();

    public static void addTicket(ticket t) {
        final String SQL = "insert into ticket values(?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, t.getP().getName());
            stmt.setInt(2, t.getP().getAge());
            stmt.setString(3, t.getP().getGender());
            stmt.setString(4, t.getP().getMobileNo());
            stmt.setString(5, t.getTicketID().substring(5));
            stmt.setString(6, t.getB().getFrom());
            stmt.setString(7, t.getB().getTo());
            stmt.setString(8, t.getB().getTravelsName());
            stmt.setInt(9, t.getPrice());
            stmt.setString(10, t.getTicketID());
            stmt.setString(11, t.getB().getBusId());
            stmt.executeUpdate();
            busQueries.updateBusSeat(t.getB().getBusId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTicket(String id, String name, String mobileno) {
        final String SQL = "update ticket set mobileNo = ? where TicketId = ? and name = ?";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, mobileno);
            stmt.setString(2, id);
            stmt.setString(3, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTicket(String name, int age, String gender, String mobileno) {
        final String SQL = "update ticket set name = ?, age = ?, gender = ? where mobileno = ?";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, mobileno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTicket(String tid, String bid) {
        final String SQL = "delete from ticket where TicketId = ?";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, tid);
            busQueries.updateBus(bid);
            ticket.updateSeats(tid);
            ticket.ticketbook.remove(tid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String searchTicket(String mn) {
        final String SQL = "Select ticketid from ticket where mobileno = ?";
        String tid = null;
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, mn);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tid = rs.getString("ticketid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tid;
    }

    public static void passengerList(String id) throws IOException {
        String DirectoryPath = System.getProperty("user.dir");
        final String SQL = "select name,age,gender,mobileno,seatno,source,destination,travelsname from ticket where busid = ?";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(
                    DirectoryPath+"//src//csvfiles//passenger.csv")));
            while (rs.next()) {
                String line = ("name: " + rs.getString("name") + ",age: " + rs.getInt("age") + ",gender: "
                        + rs.getString("gender") + ",mobile no: " + rs.getString("mobileNo") + ",from: "+ rs.getString("source")
                        + ",seat no: " + rs.getString("seatno") + ",to: " + rs.getString("destination")
                        + ",travel's name: " + rs.getString("TravelsName"));
                writer.println(line);
            }
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateTicket() {
        final String SQL = "truncate ticket";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.executeUpdate();
            System.out.println("program terminated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
