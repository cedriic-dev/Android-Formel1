package at.ac.htlperg.viewmodeldemo.model;

public class Driver {
    private String driverId;
    private String permanentNumber;
    private String givenName;
    private String familyName;
    private String nationality;
    private String image;
    private String team;
    private String highest_race_finish;
    private String grands_prix;
    private String points;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPermanentNumber() {
        return permanentNumber;
    }

    public void setPermanentNumber(String permanentNumber) {
        this.permanentNumber = permanentNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String driver) {
        this.team = driver;
    }

    public String getHighest_race_finish() {
        return highest_race_finish;
    }

    public void setHighest_race_finish(String highest_race_finish) {
        this.highest_race_finish = highest_race_finish;
    }

    public String getGrands_prix() {
        return grands_prix;
    }

    public void setGrands_prix(String grands_prix) {
        this.grands_prix = grands_prix;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", permanentNumber='" + permanentNumber + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}