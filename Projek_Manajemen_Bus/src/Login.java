import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Login extends JDialog {
    private JPanel loginPage;
    private JTextField usernameLogin;
    private JPasswordField passwordLogin;
    private JCheckBox showPasswordCheckBox;  // Tambahkan checkbox untuk menampilkan password
    private JButton createOneButton;
    private JButton loginButton;

    public Login(JFrame parent) {
        // GUI Login
        super(parent);
        setTitle("Manajemen Bus - Login");
        loginPage = new JPanel();
        setContentPane(loginPage);
        setLocationRelativeTo(parent);
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ganti warna latar belakang menjadi biru tua
        loginPage.setBackground(new Color(0, 0, 50)); // Warna Midnight Blue

        // Initialize components
        usernameLogin = new JTextField(20);
        passwordLogin = new JPasswordField(20);
        showPasswordCheckBox = new JCheckBox("Show Password");
        createOneButton = new JButton("Buat Akun");
        loginButton = new JButton("Login");

        loginButton.setBackground(new Color(46, 204, 113)); // Warna hijau
        createOneButton.setBackground(new Color(52, 152, 219)); // Warna biru

        loginButton.setForeground(Color.white);
        createOneButton.setForeground(Color.white);

        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordLogin.setEchoChar((char) 0);
                } else {
                    passwordLogin.setEchoChar('\u2022');
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginProcess()) {
                    // If login is successful, go to Main
                    Main mainFrame = new Main();
                    mainFrame.setVisible(true);
                    dispose();
                }
            }
        });

        createOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register registerPage = new Register(null);
                registerPage.setVisible(true);
                dispose();
            }
        });

        // Use GridBagLayout for better component placement control
        loginPage.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ganti warna teks "Username" dan "Password" menjadi putih
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPage.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPage.add(usernameLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPage.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPage.add(passwordLogin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPage.add(showPasswordCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        loginPage.add(createOneButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        loginPage.add(loginButton, gbc);

        setVisible(true);
    }

    private boolean loginProcess() {
        String username = usernameLogin.getText();
        String password = String.valueOf(passwordLogin.getPassword());
        boolean found = false;

        try {
            File myObj = new File("database.txt");
            Scanner read = new Scanner(myObj);

            while (read.hasNextLine()) {
                String data = read.nextLine();
                String[] acct = data.split(",");
                String _user = acct[0];
                String _pass = acct[1];

                if (_user.equals(username) && _pass.equals(password)) {
                    found = true;
                    break;
                }
            }
            read.close();

            if (found) {
                showMessage("Login berhasil!");
                return true;
            } else {
                showError("Username atau Password Salah!");
                clearFields();
                return false;
            }
        } catch (IOException ex) {
            showError("Belum terdaftar! Silakan daftar terlebih dahulu.");
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(loginPage, message, "Coba lagi", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(loginPage, message);
    }

    private void clearFields() {
        usernameLogin.setText("");
        passwordLogin.setText("");
    }

    public static void main(String[] args) {
        Login loginPage = new Login(null);
        loginPage.setVisible(true);
    }
}
