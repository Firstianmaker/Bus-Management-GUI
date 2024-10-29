package Model;

public class Jadwal {
    private String kodeRute, rute, kodeBus;
    private int waktuTunggu;

    public Jadwal(String kodeRute, String Rute, String kodeBus, int waktuTunggu){
        this.kodeRute = kodeRute;
        this.rute = Rute;
        this.kodeBus = kodeBus;
        this.waktuTunggu = waktuTunggu;
    }

    public String getKodeRute(){
        return kodeRute;
    }
    public String getRute(){
        return rute;
    }
    public String getKodeBus(){
        return kodeBus;
    }
    public int getWaktuTunggu(){
        return waktuTunggu;
    }
}
