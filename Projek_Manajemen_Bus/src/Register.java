import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JDialog {
    private JPanel registerPage;
    private JTextField usernameRegister;
    private JPasswordField passwordRegister;
    private JPasswordField confirmPassword;
    private JButton backButton;
    private JButton registerButton;
    private JCheckBox showPasswordCheckbox; // Tambahkan checkbox untuk menampilkan password

    public Register(JFrame parent) {
        super(parent);

        // GUI Register
        setTitle("Manajemen Bus");
        registerPage = new JPanel();
        setContentPane(registerPage);
        setLocationRelativeTo(parent);
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ganti warna latar belakang menjadi biru tua
        registerPage.setBackground(new Color(0, 0, 50)); // Warna Midnight Blue

        // Initialize components
        usernameRegister = new JTextField(20);
        passwordRegister = new JPasswordField(20);
        confirmPassword = new JPasswordField(20);
        backButton = new JButton("Back");
        registerButton = new JButton("Register");
        showPasswordCheckbox = new JCheckBox("Show Password"); // Tambahkan checkbox untuk menampilkan password

        registerButton.setBackground(new Color(46, 204, 113)); // Warna hijau
        backButton.setBackground(new Color(52, 152, 219)); // Warna biru

        registerButton.setForeground(Color.white);
        backButton.setForeground(Color.white);

        // Tambahkan listener untuk checkbox
        showPasswordCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordRegister.setEchoChar((char) 0); // Tampilkan password
                    confirmPassword.setEchoChar((char) 0);
                } else {
                    passwordRegister.setEchoChar('\u2022'); // Gunakan karakter bintang untuk menyembunyikan password
                    confirmPassword.setEchoChar('\u2022');
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginPage = new Login(null);
                loginPage.setVisible(true);
                dispose();
            }
        });

        // Use GridBagLayout for better component placement control
        registerPage.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);
        registerPage.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPage.add(usernameRegister, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        registerPage.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPage.add(passwordRegister, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.white);
        registerPage.add(confirmPasswordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        registerPage.add(confirmPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        registerPage.add(showPasswordCheckbox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        registerPage.add(backButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        registerPage.add(registerButton, gbc);

        setVisible(true);
    }

    private void registerUser() {
        String username = usernameRegister.getText();
        String password = String.valueOf(passwordRegister.getPassword());
        String confirm = String.valueOf(confirmPassword.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showError("Harap isi semua kolom.");
            return;
        }

        if (!(password.length() >= 8)) {
            showError("Panjang Password Minimal 8!");
            return;
        }

        if (!password.equals(confirm)) {
            showError("Password tidak sama!");
            return;
        }

        try {
            FileWriter writer = new FileWriter("database.txt", true);
            writer.write(username + "," + password + "\n");
            writer.close();

            showMessage("Pendaftaran Berhasil!");

            Login loginPage = new Login(null);
            loginPage.setVisible(true);
            dispose();
        } catch (IOException ex) {
            showError("Pendaftaran Gagal, Coba lagi.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(registerPage, message, "Error", JOptionPane.ERROR_MESSAGE);
        clearFields();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(registerPage, message);
        clearFields();
    }

    private void clearFields() {
        usernameRegister.setText("");
        passwordRegister.setText("");
        confirmPassword.setText("");
    }

    public static void main(String[] args) {
        Register registerPage = new Register(null);
        registerPage.setVisible(true);
    }
}
