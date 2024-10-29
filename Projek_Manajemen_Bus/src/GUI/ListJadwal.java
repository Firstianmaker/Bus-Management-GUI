package GUI;

import Data.DataControl;
import Model.Bus;
import Model.Jadwal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListJadwal {
    private JTable tableJadwal;
    private JButton lihatDetailBusButton;
    private JButton lihatDetailJadwalButton;
    private JPanel ListJadwalPanel;
    private JTextArea taDetail;
    private  DefaultTableModel tableModel;
    private JButton updateButton;
    private DataControl dataControl;

    public JPanel getListJadwalPanel(){
        return ListJadwalPanel;
    }

    public void updateTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        for (Jadwal jadwal : dataControl.getArrayDataJadwal()) {
            Object[] rowData = {jadwal.getKodeRute(), jadwal.getWaktuTunggu() + " Menit"};
            tableModel.addRow(rowData);
        }
    }

    public ListJadwal(DataControl dataControl) {
        this.dataControl = dataControl;

        String[] columnNames = {"Rute", "Waktu Kedatangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableJadwal.setModel(tableModel);

        lihatDetailBusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tableJadwal.getSelectedRow();

                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih jadwal!");
                    return;
                }

                String dataKodeBus = dataControl.getArrayDataJadwal().get(rowSelected).getKodeBus();
                String dataTipeBus = "";
                String dataNoPlat = "";

                for (Bus bus : dataControl.getArrayDataArmada()) {
                    if (bus.getKodeBus().equals(dataKodeBus)) {
                        dataTipeBus = bus.getTipeBus();
                        dataNoPlat = bus.getPlatBus();
                    }
                }

                taDetail.setText(
                        "Tipe Bus  \t\t: " + dataTipeBus + "\n" +
                        "Kode Bus  \t\t: " + dataKodeBus + "\n" +
                        "Nomor Plat\t\t: " + dataNoPlat + "\n");
            }
        });
        lihatDetailJadwalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tableJadwal.getSelectedRow();

                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih jadwal!");
                    return;
                }

                String dataKodeRute = dataControl.getArrayDataJadwal().get(rowSelected).getKodeRute();
                String dataRute = dataControl.getArrayDataJadwal().get(rowSelected).getRute();
                String dataKodeBus = dataControl.getArrayDataJadwal().get(rowSelected).getKodeBus();
                String dataWaktuTunggu = dataControl.getArrayDataJadwal().get(rowSelected).getWaktuTunggu() + " Menit";

                taDetail.setText(
                        "Kode Rute \t\t: " + dataKodeRute + "\n" +
                        "Rute      \t\t: " + dataRute + "\n" +
                        "Kode Bus  \t\t: " + dataKodeBus + "\n" +
                        "Waktu Tunggu\t\t: " + dataWaktuTunggu);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
    }
}
