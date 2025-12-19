package ma.est.biblio.ui;

import ma.est.biblio.model.Utilisateur;
import ma.est.biblio.service.AuthService;
import ma.est.biblio.util.UIStyle;
import ma.est.biblio.service.UtilisateurService;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private UtilisateurService service = new UtilisateurService();

    public LoginUI() {

        setTitle("Bibliothèque - Connexion");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initUI();
        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(248, 250, 252)); 
        main.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JLabel title = new JLabel("Connexion", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(30, 41, 59)); 
        headerPanel.add(title, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(248, 250, 252));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel lblLogin = new JLabel("Login / Email");
        lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblLogin.setForeground(new Color(71, 85, 105));
        lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblLogin);
        centerPanel.add(Box.createVerticalStrut(8));

        txtLogin = new JTextField();
        styleTextField(txtLogin);
        txtLogin.setMaximumSize(new Dimension(350, 45));
        txtLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(txtLogin);
        centerPanel.add(Box.createVerticalStrut(25));

        JLabel lblPassword = new JLabel("Mot de passe");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(new Color(71, 85, 105));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblPassword);
        centerPanel.add(Box.createVerticalStrut(8));

        txtPassword = new JPasswordField();
        stylePasswordField(txtPassword);
        txtPassword.setMaximumSize(new Dimension(350, 45));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(txtPassword);
        centerPanel.add(Box.createVerticalStrut(35));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(248, 250, 252));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAdmin = createModernButton("Connexion ADMIN", new Color(59, 130, 246));
        JButton btnUser = createModernButton("Connexion UTILISATEUR", new Color(59, 130, 246));
        JButton btnRegister = createModernButton("Inscription UTILISATEUR", new Color(59, 130, 246)); 

        buttonsPanel.add(btnAdmin);
        buttonsPanel.add(Box.createVerticalStrut(12));
        buttonsPanel.add(btnUser);
        buttonsPanel.add(Box.createVerticalStrut(12));
        buttonsPanel.add(btnRegister);

        centerPanel.add(buttonsPanel);

        main.add(headerPanel, BorderLayout.NORTH);
        main.add(centerPanel, BorderLayout.CENTER);

        add(main);

        btnAdmin.addActionListener(e -> login("ADMIN"));

        btnUser.addActionListener(e -> {
            if (!txtLogin.getText().endsWith("@edu.umi.ac.ma")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Email invalide (ex: etudiant@edu.umi.ac.ma)",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            login("USER");
        });

        btnRegister.addActionListener(e -> {
            new RegisterUI();
            dispose();
        });
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(30, 41, 59));
        field.setCaretColor(new Color(59, 130, 246));
    }

    private void stylePasswordField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(30, 41, 59));
        field.setCaretColor(new Color(59, 130, 246));
    }

    private JButton createModernButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        button.setMaximumSize(new Dimension(350, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }

    private void login(String role) {

        String login = txtLogin.getText();
        String pwd = new String(txtPassword.getPassword());

        Utilisateur u = service.login(login, pwd, role);

        if (u == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Identifiants incorrects ou compte désactivé",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        new MainMenuUI(u);
        dispose();
    }
}