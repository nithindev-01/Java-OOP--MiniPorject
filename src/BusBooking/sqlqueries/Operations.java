package BusBooking.sqlqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations implements Interface {

    @Override
    public int searchPerson(String name,String id) {
        Connection con = connectionmaker.getConnection();
        final String SQL = "select * from ticket where name = ? and ticketID = ?";
        int count = 0;
        try(PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, name);
            stmt.setString(2, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                count++;
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
