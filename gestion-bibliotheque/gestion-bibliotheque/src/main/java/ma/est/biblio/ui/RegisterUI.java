package ma.est.biblio.ui;

import ma.est.biblio.service.UtilisateurService;
import ma.est.biblio.util.UIStyle;

import javax.swing.*;
import java.awt.*;

public class RegisterUI extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private UtilisateurService service = new UtilisateurService();

    public RegisterUI() {

        setTitle("Inscription Utilisateur");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initUI();
        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UIStyle.BACKGROUND);

        JLabel title = new JLabel("Inscription", SwingConstants.CENTER);
        title.setFont(UIStyle.TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(UIStyle.PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        txtEmail = UIStyle.createTextField();
        txtPassword = UIStyle.createPasswordField();

        form.add(UIStyle.createLabel("Email"));
        form.add(txtEmail);
        form.add(UIStyle.createLabel("Mot de passe"));
        form.add(txtPassword);

        JButton btnRegister = UIStyle.createButton("Créer le compte");

        main.add(title, BorderLayout.NORTH);
        main.add(form, BorderLayout.CENTER);
        main.add(btnRegister, BorderLayout.SOUTH);

        add(main);


        btnRegister.addActionListener(e -> {

            if (!txtEmail.getText().endsWith("@edu.umi.ac.ma")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Email universitaire obligatoire(@edu.umi.ac.ma)",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            service.inscrireUtilisateur(
                    txtEmail.getText(),
                    new String(txtPassword.getPassword())
            );

            JOptionPane.showMessageDialog(this, "Inscription réussie");
            new LoginUI();
            dispose();
        });
    }
}
