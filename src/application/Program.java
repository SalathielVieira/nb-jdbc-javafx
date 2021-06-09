package application;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        
        try {
            conn = DB.getConnection();
            /*
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, "Jeff Bennet");
            st.setString(2, "jeff@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("31/12/1990").getTime()));
            st.setDouble(4, 4500.0);
            st.setInt(5, 2);
            */
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) "
                    + "VALUES ('D3'),('D4')",
                    Statement.RETURN_GENERATED_KEYS);
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No row affected!");
            }
            
        } catch (SQLException/* | ParseException*/ e) {
            throw new DbException(e.getMessage());
        }
        
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
