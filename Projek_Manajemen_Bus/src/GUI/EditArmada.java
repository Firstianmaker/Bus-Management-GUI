package GUI;

import Data.DataControl;
import Model.Bus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditArmada {
    private JButton hapusButton;
    private JTable tableArmada;
    private DefaultTableModel tableModel;
    private JPanel EditArmadaPanel;
    private JButton tambahButton;
    private JButton updateButton;
    private DataControl dataControl;

    public JPanel getEditArmadaPanel(){
        return EditArmadaPanel;
    }

    public void updateTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        for (Bus bus : dataControl.getArrayDataArmada()) {
            String Status = "Tidak Terjadwal";
            if (bus.getStatusJadwal()){
                Status = "Terjadwal";
            }
            Object[] rowData = {bus.getTipeBus(), bus.getKodeBus(), bus.getPlatBus(), Status};
            tableModel.addRow(rowData);
        }
    }

    public EditArmada(DataControl dataControl) {
        this.dataControl = dataControl;

        String[] columnNames = {"Tipe Bus", "Kode Bus", "Nomor Plat", "Status Jadwal"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableArmada.setModel(tableModel);

        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = tableArmada.getSelectedRow();

                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
                    return;
                }

                dataControl.removeDataArmada(rowSelected);
                updateTable();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            }
        });
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormArmada formArmada = new FormArmada(dataControl, EditArmada.this);
                formArmada.pack();
                formArmada.setVisible(true);
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
