package com.example.personnel.repositories;

import com.example.personnel.config.Database;
import com.example.personnel.models.Ligue;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LigueRepository {
    public static ObservableList<Ligue> getLigues() throws SQLException {
        ObservableList<Ligue> ligues = FXCollections.observableArrayList();

        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ligue;");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ligues.add(new Ligue(
                    rs.getInt("ID_LIGUE"),
                    rs.getString("NOM_L")
            ));
        }

        return ligues;
    }
    public static boolean AjouterLigue(Ligue ligue) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ligue WHERE NOM_L = ?;");
        stmt.setString(1, ligue.getNom());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return false;
        }
        PreparedStatement stmtajt = conn.prepareStatement("INSERT INTO ligue(NOM_L) VALUES (?);");
        stmtajt.setString(1, ligue.getNom());
        stmtajt.executeUpdate();
        return true;
    }
    public static boolean UpdateLigue(Ligue ligue) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement Checkstmt = conn.prepareStatement("SELECT * FROM ligue WHERE NOM_L = ?;");
        Checkstmt.setString(1, ligue.getNom());
        ResultSet rs = Checkstmt.executeQuery();
        if (rs.next()) {
            return false;
        }

        PreparedStatement stmtedit = conn.prepareStatement("UPDATE ligue SET NOM_L = ? WHERE ID_LIGUE = ?;");
        stmtedit.setString(1, ligue.getNom());
        stmtedit.setInt(2, ligue.getId());
        stmtedit.executeUpdate();
        return true;
    }
    public static boolean SuppLigue(Ligue ligue) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement Checkstmt = conn.prepareStatement("SELECT * FROM ligue WHERE NOM_L = ?;");
        Checkstmt.setString(1, ligue.getNom());
        ResultSet rs = Checkstmt.executeQuery();
        if (!rs.next()) {
            return false;
        }

        PreparedStatement Suppstmt = conn.prepareStatement("DELETE FROM ligue WHERE NOM_L = ?;");
        Suppstmt.setString(1, ligue.getNom());
        Suppstmt.executeUpdate();
        return true;
    }
}
