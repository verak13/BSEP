package hospital.hospital.model.cep.alarms;

public abstract class Alarm {
    //ovo je mejl na koji saljemo upozorenje, nek bude nas neki da mozemo da vidimo
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }
}
