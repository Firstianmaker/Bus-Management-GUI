package GUI;

import Data.DataControl;
import Model.MikroBus;
import Model.RegulerBus;
import Model.RoyalBus;

import javax.swing.*;
import java.awt.event.*;

public class FormArmada extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfTipeBus;
    private JTextField tfKodeBus;
    private JTextField tfNomorPlat;
    private DataControl dataControl;
    private EditArmada editArmada;

    public FormArmada(DataControl dataControl, EditArmada editArmada) {
        this.dataControl = dataControl;
        this.editArmada = editArmada;

        setContentPane(contentPane);
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String tipeBus = tfTipeBus.getText();
        String kodeBus = tfKodeBus.getText();
        String noPlat = tfNomorPlat.getText();

        if (tipeBus.equalsIgnoreCase("Mikro Bus")) {
            MikroBus mikroBus = new MikroBus("Mikro Bus", kodeBus, noPlat);
            dataControl.addDataArmada(mikroBus);
        } else if (tipeBus.equalsIgnoreCase("Reguler Bus")) {
            RegulerBus regulerBus = new RegulerBus("Reguler Bus", kodeBus, noPlat);
            dataControl.addDataArmada(regulerBus);
        } else if (tipeBus.equalsIgnoreCase("Royal Bus")) {
            RoyalBus royalBus = new RoyalBus("RoyalBus", kodeBus, noPlat);
            dataControl.addDataArmada(royalBus);
        } else {
            return;
        }

        editArmada.updateTable();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
