package ma.est.biblio.ui;

import ma.est.biblio.model.Adherent;
import ma.est.biblio.service.AdherentService;
import ma.est.biblio.util.UIStyle;

import javax.swing.*;
import java.awt.*;

public class AdherentUI extends JFrame {

    private JTextField txtNom, txtEmail;
    private JTextArea area;
    private AdherentService service = new AdherentService();

    public AdherentUI() {
        setTitle("Gestion des Adhérents");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UIStyle.BACKGROUND);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(UIStyle.PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNom = UIStyle.createTextField();
        txtEmail = UIStyle.createTextField();

        form.add(UIStyle.createLabel("Nom"));
        form.add(txtNom);
        form.add(UIStyle.createLabel("Email"));
        form.add(txtEmail);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIStyle.BACKGROUND);

        JButton btnAdd = UIStyle.createButton("Ajouter");
        JButton btnList = UIStyle.createButton("Lister");
        JButton btnDelete = UIStyle.createButton("Supprimer");

        buttons.add(btnAdd);
        buttons.add(btnList);
        buttons.add(btnDelete);

        area = new JTextArea();
        area.setEditable(false);

        main.add(form, BorderLayout.NORTH);
        main.add(buttons, BorderLayout.CENTER);
        main.add(new JScrollPane(area), BorderLayout.SOUTH);

        add(main);

        // Actions
        btnAdd.addActionListener(e -> {
            if (txtNom.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Tous les champs sont obligatoires",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            service.ajouterAdherent(new Adherent(
                    txtNom.getText(),
                    txtEmail.getText()
            ));
            JOptionPane.showMessageDialog(this, "Adhérent ajouté");
        });

        btnList.addActionListener(e -> {
            area.setText("");
            for (Adherent a : service.getAdherents()) {
                area.append(
                        a.getId() + " | " +
                        a.getNom() + " | " +
                        a.getEmail() + " | Bloqué: " +
                        a.isBloque() + "\n"
                );
            }
        });

        btnDelete.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "ID à supprimer");
            if (idStr != null) {
                service.supprimerAdherent(Integer.parseInt(idStr));
                JOptionPane.showMessageDialog(this, "Adhérent supprimé");
            }
        });
    }
}
