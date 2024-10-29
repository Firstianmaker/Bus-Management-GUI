package Model;

public class Bus {
    private String tipeBus, kodeBus, platBus;
    private boolean statusJadwal = false;

    public Bus(String tipeBus, String kodeBus, String platBus){
        this.tipeBus = tipeBus;
        this.kodeBus = kodeBus;
        this.platBus = platBus;
    }

    public String getTipeBus() {
        return tipeBus;
    }
    public String getKodeBus() {
        return kodeBus;
    }
    public String getPlatBus() {
        return platBus;
    }
    public boolean getStatusJadwal() {
        return statusJadwal;
    }

    public void setStatusJadwal(boolean statusJadwal) {
        this.statusJadwal = statusJadwal;
    }
}
