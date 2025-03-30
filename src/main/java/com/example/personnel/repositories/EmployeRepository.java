package com.example.personnel.repositories;

import com.example.personnel.config.Database;
import com.example.personnel.models.Employe;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeRepository {
    public static Employe login(String email, String password) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employe WHERE MAIL_EMP = ? AND MDP_EMP = ?");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        Employe employe;

        if (rs.next()) {
            employe = new Employe(
                    rs.getInt("ID_EMP"),
                    rs.getString("NOM_EMP"),
                    rs.getString("PRENOM_EMP"),
                    rs.getString("MDP_EMP"),
                    rs.getDate("DATE_ARRIVE").toLocalDate(),
                    rs.getDate("DATE_DEPART").toLocalDate(),
                    rs.getInt("ADMIN"),
                    rs.getString("MAIL_EMP")
            );
        }
        else {
            return null;
        }
        return employe;
    }
    /* public static boolean  editRoot() throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement Rootstmt = conn.prepareStatement("SELECT * FROM employe WHERE 'ADMIN' = 2");
        ResultSet rs = Rootstmt.executeQuery();

        if (!rs.next()) {
            result ;
        }
        PreparedStatement EditRootstmt = conn.prepareStatement("UPDATE EMPLOYE SET NOM_EMP = ? WHERE ID_EMP = ?;");
        EditRootstmt.setString(1, rs.getString("NOM_EMP"));
        EditRootstmt.setInt(2, rs.getInt("ID_EMP"));
        EditRootstmt.executeUpdate();
        return true;
    }*/
}
