package daos;

import java.sql.*;

public class Dao {

    private final String databaseName;

    public Dao(String databaseName){
        this.databaseName = databaseName;
    }

    /**
     * Helper method to get connection to the database
     * @return Connection object
     */
    public Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "mysql";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
        }
        return con;
    }

    /**
     * Helper method to close resources after database operation
     * @param rs - ResultSet object to be closed
     * @param ps - PreparedStatement object to be closed
     * @param con - Connection object to be closed
     */
    public void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred while closing resources: " + e.getMessage());
        }
    }

}
