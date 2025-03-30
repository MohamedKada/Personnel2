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
     public static boolean  editRoot(String name, String mail, String password) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement Rootstmt = conn.prepareStatement("SELECT * FROM employe WHERE 'ADMIN' = 2");
        ResultSet rs = Rootstmt.executeQuery();

        if (!rs.next()) {
            return false;
        }
        PreparedStatement EditRootstmt = conn.prepareStatement("UPDATE EMPLOYE SET NOM_EMP = ?, MAIL_EMP = ?, MDP_EMP = ? WHERE ADMIN = 2;");
        EditRootstmt.setString(1, name);
        EditRootstmt.setString(2, mail);
        EditRootstmt.setString(3, password);

        EditRootstmt.executeUpdate();
        return true;
    }
}
