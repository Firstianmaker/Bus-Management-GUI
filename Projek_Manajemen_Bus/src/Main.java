import Data.DataControl;
import GUI.EditArmada;
import GUI.EditJadwal;
import GUI.ListJadwal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel timePanel;
    private JPanel buttonPanel;
    private DataControl dataControl;

    public Main() {
        dataControl = new DataControl();

        setTitle("Manajemen Bus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Panel latar belakang
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(173, 216, 230)); // Warna biru muda

        // Judul
        JLabel titleLabel = new JLabel("Manajemen Bus");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Deskripsi
        JLabel descriptionLabel = new JLabel("Selamat datang di aplikasi manajemen bus.");
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(descriptionLabel, BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);

        EditArmada editArmadaPanel = new EditArmada(dataControl);
        EditJadwal editJadwalPanel = new EditJadwal(dataControl);
        ListJadwal listJadwalPanel = new ListJadwal(dataControl);

        this.cardPanel.add(editArmadaPanel.getEditArmadaPanel(), "Edit Armada");
        this.cardPanel.add(editJadwalPanel.getEditJadwalPanel(), "Edit Jadwal");
        this.cardPanel.add(listJadwalPanel.getListJadwalPanel(), "Daftar Jadwal");

        // Panel waktu
        timePanel = new JPanel(new BorderLayout());
        TimeClock clock = new TimeClock();
        timePanel.add(clock, BorderLayout.CENTER);

        // Panel tombol
        buttonPanel = new JPanel();

        JButton button1 = new JButton("Edit Armada");
        JButton button2 = new JButton("Edit Jadwal");
        JButton button3 = new JButton("Daftar Jadwal");

        button1.setBackground(new Color(52, 152, 219)); // Warna biru
        button2.setBackground(new Color(46, 204, 113)); // Warna hijau
        button3.setBackground(new Color(155, 89, 182)); // Warna ungu

        button1.setForeground(Color.white); // Warna teks putih
        button2.setForeground(Color.white);
        button3.setForeground(Color.white);

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1.setBackground(new Color(41, 128, 185)); // Warna biru saat hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button1.setBackground(new Color(52, 152, 219)); // Kembalikan warna biru
            }
        });

        // Lakukan hal yang sama untuk button2 dan button3

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(this.cardPanel, BorderLayout.CENTER);
        backgroundPanel.add(timePanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(backgroundPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Edit Armada")) {
            cardLayout.show(this.cardPanel, "Edit Armada");
        } else if (e.getActionCommand().equals("Edit Jadwal")) {
            cardLayout.show(this.cardPanel, "Edit Jadwal");
        } else if (e.getActionCommand().equals("Daftar Jadwal")) {
            cardLayout.show(this.cardPanel, "Daftar Jadwal");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
