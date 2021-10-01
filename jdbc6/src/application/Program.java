package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        try{
            conn = DB.getConnection();
            conn.setAutoCommit(false); //confirmação de integridade de operação
            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 Where DepartmentId = 1");

//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("Fake Error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 Where DepartmentId = 2");

            conn.commit();

            System.out.println("rows1 affected: " + rows1);
            System.out.println("rows2 affected: " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Cause by: " + e.getMessage());
            }
            catch (SQLException e1){
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.getConnection();
        }


    }
}