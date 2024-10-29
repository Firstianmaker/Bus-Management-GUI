package GUI;

import Data.DataControl;
import Model.Jadwal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditJadwal {
    private JButton hapusButton;
    private JButton tambahButton;
    private JTable tableJadwal;
    private DefaultTableModel tableModel;
    private JPanel EditJadwalPanel;
    private JButton updateButton;
    private DataControl dataControl;

    public JPanel getEditJadwalPanel(){
        return EditJadwalPanel;
    }

    public void updateTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        for (Jadwal jadwal : dataControl.getArrayDataJadwal()) {
            Object[] rowData = {jadwal.getKodeRute(), jadwal.getRute(), jadwal.getKodeBus(), jadwal.getWaktuTunggu() + " Menit"};
            tableModel.addRow(rowData);
        }
    }

    public EditJadwal(DataControl dataControl) {
        this.dataControl = dataControl;

        String[] columnNames = {"Kode Rute", "Rute", "Kode Bus", "Waktu Tunggu"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableJadwal.setModel(tableModel);

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormJadwal formJadwal = new FormJadwal(dataControl, EditJadwal.this);
                formJadwal.pack();
                formJadwal.setVisible(true);
            }
        });
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tableJadwal.getSelectedRow();

                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
                    return;
                }

                dataControl.getArrayDataArmada().get(rowSelected).setStatusJadwal(false);
                dataControl.removeDataJadwal(rowSelected);
                updateTable();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            }
        });
        updateTable();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
    }
}
