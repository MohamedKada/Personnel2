package com.example.personnel.repositories;

import com.example.personnel.config.Database;
import com.example.personnel.models.Employe;
import com.example.personnel.models.Ligue;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeRepository {
    public static Employe rootUser;

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
        PreparedStatement Rootstmt = conn.prepareStatement("SELECT * FROM employe WHERE ADMIN = 2");
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

    public static ObservableList<Employe> getEmploye(int id) throws SQLException {
        ObservableList<Employe> employes = FXCollections.observableArrayList();

        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employe WHERE ID_LIGUE = ?;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            employes.add(new Employe(
                    rs.getInt("ID_EMP"),
                    rs.getString("NOM_EMP"),
                    rs.getString("PRENOM_EMP"),
                    rs.getString("MDP_EMP"),
                    rs.getDate("DATE_ARRIVE").toLocalDate(),
                    rs.getDate("DATE_DEPART").toLocalDate(),
                    rs.getInt("ADMIN"),
                    rs.getString("MAIL_EMP"),
                    rs.getInt("ID_LIGUE")
            ));
        }
        return employes;
    }

    public static boolean addEmploye (Employe employe, Ligue ligue) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employe WHERE MAIL_EMP = ?;");
        stmt.setString(1, employe.getMail());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return false;
        }
        PreparedStatement stmtajt = conn.prepareStatement("INSERT INTO employe(NOM_EMP, PRENOM_EMP, MDP_EMP, DATE_ARRIVE, DATE_DEPART, ADMIN, MAIL_EMP, ID_LIGUE) VALUES (?,?,?,?,?,?,?,?);");
        stmtajt.setString(1, employe.getNom());
        stmtajt.setString(2, employe.getPrenom());
        stmtajt.setString(3, employe.getPassword());
        stmtajt.setString(4, employe.getDateArrive().toString());
        stmtajt.setString(5, employe.getDateDepart().toString());
        stmtajt.setInt(6, employe.getAdmin());
        stmtajt.setString(7, employe.getMail());
        stmtajt.setInt(8, ligue.getId());
        stmtajt.executeUpdate();
        return true;
    }

    public static boolean editEmploye(Employe employe, int id) throws SQLException {
        Connection conn = Database.connectDatabase();

        PreparedStatement editstmt = conn.prepareStatement("UPDATE employe SET NOM_EMP = ?, PRENOM_EMP = ?, MDP_EMP = ?, DATE_ARRIVE = ?, DATE_DEPART = ?, ADMIN = ?, MAIL_EMP = ?, ID_LIGUE = ? WHERE ID_EMP = ?");
        editstmt.setString(1, employe.getNom());
        editstmt.setString(2, employe.getPrenom());
        editstmt.setString(3, employe.getPassword());
        editstmt.setString(4, employe.getDateArrive().toString());
        editstmt.setString(5, employe.getDateDepart().toString());
        editstmt.setInt(6, employe.getAdmin());
        editstmt.setString(7, employe.getMail());
        editstmt.setInt(8, employe.getligueID());
        editstmt.setInt(9, id); // Ajout de l'ID de l'employé pour la condition WHERE
        editstmt.executeUpdate();

        int rowsAffected = editstmt.executeUpdate();
        return rowsAffected > 0;
    } // Retourne true si au moins une ligne a été mise à jour

    public static void deleteEmploye(int id) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM employe WHERE ID_EMP = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public static boolean deleteToLigue(int id) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("UPDATE EMPLOYE SET ID_LIGUE = NULL WHERE ID_EMP = ?");
        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}
