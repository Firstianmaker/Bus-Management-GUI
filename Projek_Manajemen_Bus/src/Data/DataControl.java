package Data;

import Model.Bus;
import Model.Jadwal;

import java.util.ArrayList;

public class DataControl {
    private ArrayList<Jadwal> arrayDataJadwal;
    private ArrayList<Bus> arrayDataArmada;

    public DataControl() {
        arrayDataJadwal = new ArrayList<>();
        arrayDataArmada = new ArrayList<>();
    }

    public ArrayList<Jadwal> getArrayDataJadwal() {
        return arrayDataJadwal;
    }

    public ArrayList<Bus> getArrayDataArmada() {
        return arrayDataArmada;
    }

    public void addDataArmada(Bus bus) {
        arrayDataArmada.add(bus);
    }

    public void addDataJadwal(Jadwal jadwal) {
        arrayDataJadwal.add(jadwal);
    }

    public void removeDataArmada(int index) {
        if (index >= 0 && index < arrayDataArmada.size()) {
            arrayDataArmada.remove(index);
        } else {
            System.out.println("Index out of bounds or array is empty");
        }
    }

    public void removeDataJadwal(int index) {
        if (index >= 0 && index < arrayDataJadwal.size()) {
            arrayDataJadwal.remove(index);
        } else {
            System.out.println("Index out of bounds or array is empty");
        }
    }
}
