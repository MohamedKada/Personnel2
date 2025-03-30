package com.example.personnel.models;

import java.time.LocalDate;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String password;
    private LocalDate dateArrive;
    private LocalDate dateDepart;
    private int admin;
    private String mail;

    public Employe(int id, String nom, String prenom, String password, LocalDate dateArrive, LocalDate dateDepart, int admin, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.dateArrive = dateArrive;
        this.dateDepart = dateDepart;
        this.admin = admin;
        this.mail = mail;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDate dateArrive) {
        this.dateArrive = dateArrive;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", dateArrive=" + dateArrive +
                ", dateDepart=" + dateDepart +
                ", admin=" + admin +
                ", mail='" + mail + '\'' +
                '}';
    }
}
