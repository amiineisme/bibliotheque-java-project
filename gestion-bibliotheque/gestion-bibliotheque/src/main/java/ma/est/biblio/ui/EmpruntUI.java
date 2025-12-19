package ma.est.biblio.ui;

import ma.est.biblio.service.EmpruntService;
import ma.est.biblio.util.UIStyle;

import javax.swing.*;
import java.awt.*;

public class EmpruntUI extends JFrame {

    private JTextField txtAdherentId, txtIsbn;
    private EmpruntService service = new EmpruntService();

    public EmpruntUI() {

        setTitle("Gestion des Emprunts");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(UIStyle.BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        txtAdherentId = UIStyle.createTextField();
        txtIsbn = UIStyle.createTextField();

        panel.add(UIStyle.createLabel("ID Adhérent"));
        panel.add(txtAdherentId);
        panel.add(UIStyle.createLabel("ISBN Livre"));
        panel.add(txtIsbn);

        JButton btnEmprunter = UIStyle.createButton("Emprunter");
        JButton btnRetour = UIStyle.createButton("Retourner");

        panel.add(btnEmprunter);
        panel.add(btnRetour);

        add(panel);

        btnEmprunter.addActionListener(e -> {
            try {
                service.emprunter(
                        Integer.parseInt(txtAdherentId.getText()),
                        txtIsbn.getText()
                );
                JOptionPane.showMessageDialog(this, "Emprunt effectué");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRetour.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "ID Emprunt");
            service.retourner(Integer.parseInt(id), txtIsbn.getText());
            JOptionPane.showMessageDialog(this, "Livre retourné");
        });

        setVisible(true);
    }
}