package com.example.personnel.models;

import com.example.personnel.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Ligue {
    private int id;
    private String nom;

    public Ligue(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Ligue{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }

    public Ligue cloneLigue() throws SQLException {
        String nvNom = "copie de " + this.nom;
        Ligue newLigue = new Ligue(this.id, nvNom);

        Connection conn = Database.connectDatabase();
        PreparedStatement clonestmt = conn.prepareStatement("INSERT INTO ligue(NOM_L) VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
        clonestmt.setString(1, nvNom);
        int rowsAffected = clonestmt.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new SQLException("La création de la ligue a échoué, aucune ligne affectée.");
        }

        ResultSet generatedKeys = clonestmt.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new SQLException("La création de la ligue a échoué, impossible d'obtenir l'ID.");
        }
        int newLigueId = generatedKeys.getInt(1);
        newLigue.setId(newLigueId);

        // Employé provisoire
        LocalDate ajd = LocalDate.now();
        LocalDate anneepro = ajd.plusYears(1);
        PreparedStatement addEmployeStmt = conn.prepareStatement(
                "INSERT INTO employe(NOM_EMP, PRENOM_EMP, MDP_EMP, DATE_ARRIVE, DATE_DEPART, ADMIN, MAIL_EMP, ID_LIGUE) " +
                        "VALUES (?,?,?,?,?,?,?,?);"
        );
        addEmployeStmt.setString(1, "A définir");
        addEmployeStmt.setString(2, "A définir");
        addEmployeStmt.setString(3, "password");
        addEmployeStmt.setString(4, ajd.toString());
        addEmployeStmt.setString(5, anneepro.toString());
        addEmployeStmt.setInt(6, 1);
        addEmployeStmt.setString(7, "a définir");
        addEmployeStmt.setInt(8, newLigueId);
        
        rowsAffected = addEmployeStmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("La création de l'employé provisoire a échoué, aucune ligne affectée.");
        }

        return newLigue;
    }
}
