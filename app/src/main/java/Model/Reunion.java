package Model;

public class Reunion {
    private String nom_reunion;
    private String date;
    private String heure;
    private String participant;
    private String salle;

    public Reunion(String nom_reunion, String date, String heure, String participant, String salle) {
        this.nom_reunion = nom_reunion;
        this.date = date;
        this.heure = heure;
        this.participant = participant;
        this.salle = salle;
    }

    public String getNom_reunion() {
        return nom_reunion;
    }

    public void setNom_reunion(String nom_reunion) {
        this.nom_reunion = nom_reunion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
