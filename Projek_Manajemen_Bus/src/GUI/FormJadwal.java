package GUI;

import Data.DataControl;
import Model.Bus;
import Model.Jadwal;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormJadwal extends JDialog {
    private JPanel FormJadwalPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfKodeRute;
    private JTextField tfRute;
    private JTextField tfKodeBus;
    private JTextField tfWaktuTunggu;
    private DataControl dataControl;
    private EditJadwal editJadwal;

    public FormJadwal(DataControl dataControl, EditJadwal editJadwal) {
        this.dataControl = dataControl;
        this.editJadwal = editJadwal;

        setContentPane(FormJadwalPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        FormJadwalPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String kodeRute = tfKodeRute.getText();
        String rute = tfRute.getText();
        String kodeBus = tfKodeBus.getText();
        int waktuTunggu = Integer.parseInt(tfWaktuTunggu.getText());

        ArrayList<String> daftarKodeBus = new ArrayList<>();
        for (Bus bus : dataControl.getArrayDataArmada()) {
            if (!bus.getStatusJadwal()){
                daftarKodeBus.add(bus.getKodeBus());
            }
        }

        if (daftarKodeBus.contains(kodeBus)) {
                Jadwal jadwalBaru = new Jadwal(kodeRute, rute, kodeBus, waktuTunggu);
                dataControl.addDataJadwal(jadwalBaru);
                for (Bus bus : dataControl.getArrayDataArmada()){
                    if (kodeBus.equals(bus.getKodeBus())){
                        bus.setStatusJadwal(true);
                    }
                }
        } else {
            return;
        }

        editJadwal.updateTable();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
