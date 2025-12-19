package ma.est.biblio.ui;

import ma.est.biblio.model.Utilisateur;
import ma.est.biblio.util.UIStyle;

import javax.swing.*;
import java.awt.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI(Utilisateur user) {

        setTitle("Bibliothèque - Menu");
        setSize(520, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIStyle.BACKGROUND);

        JLabel lbl = new JLabel(
                "Bienvenue " + user.getLogin(),
                SwingConstants.CENTER
        );
        lbl.setFont(UIStyle.TITLE_FONT);
        lbl.setForeground(UIStyle.TEXT);
        lbl.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        mainPanel.add(lbl, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        btnPanel.setBackground(UIStyle.BACKGROUND);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        if ("ADMIN".equals(user.getRole())) {

            JButton btnLivres = UIStyle.createButton("Livres");
            JButton btnAdherents = UIStyle.createButton("Adhérents");
            JButton btnEmprunts = UIStyle.createButton("Emprunts");
            JButton btnUsers = UIStyle.createButton("Utilisateurs");
            JButton btnLogs = UIStyle.createButton("Logs");

            btnLivres.addActionListener(e -> new LivreUI());

            btnAdherents.addActionListener(e -> new AdherentUI());

            btnEmprunts.addActionListener(e -> new EmpruntUI());

            btnUsers.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "Utilisateurs - À venir")
            );

            btnLogs.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "Logs - À venir")
            );

            btnPanel.add(btnLivres);
            btnPanel.add(btnAdherents);
            btnPanel.add(btnEmprunts);
            btnPanel.add(btnUsers);
            btnPanel.add(btnLogs);

        } else {

            JButton btnCatalogue = UIStyle.createButton("Catalogue");
            JButton btnRecherche = UIStyle.createButton("Recherche");
            JButton btnMesEmprunts = UIStyle.createButton("Mes emprunts");

            btnCatalogue.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "Catalogue - À venir")
            );

            btnRecherche.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "Recherche - À venir")
            );

            btnMesEmprunts.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "Mes emprunts - À venir")
            );

            btnPanel.add(btnCatalogue);
            btnPanel.add(btnRecherche);
            btnPanel.add(btnMesEmprunts);
        }

        mainPanel.add(btnPanel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
    }
}
