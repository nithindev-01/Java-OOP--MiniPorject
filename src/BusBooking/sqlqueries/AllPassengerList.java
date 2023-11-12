package BusBooking.sqlqueries;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllPassengerList extends abstraction {

    @Override
    public void printPassengerList() throws IOException {
        Connection con = connectionmaker.getConnection();
        final String SQL = "select * from ticket";
        try (PreparedStatement stmt = con.prepareStatement(SQL)) {
            String DirectoryPath = System.getProperty("user.dir");
            ResultSet rs = stmt.executeQuery();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(
                    DirectoryPath+"//src//csvfiles//allPassengerList.csv")));
            while (rs.next()) {
                String line = ("name: "+rs.getString("name") +",mobile no: " +rs.getString("mobileNo") +",from: " + rs.getString("source")
                        +",seat no: " +rs.getString("seatno")+",to: " +rs.getString("destination") +",travel's name: " + rs.getString("TravelsName"));
                writer.println(line);
            }
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
