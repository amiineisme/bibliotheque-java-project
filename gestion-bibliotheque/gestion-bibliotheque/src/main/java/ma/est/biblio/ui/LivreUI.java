package ma.est.biblio.ui;

import ma.est.biblio.model.Livre;
import ma.est.biblio.service.LivreService;
import ma.est.biblio.util.CSVUtil;
import ma.est.biblio.util.UIStyle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class LivreUI extends JFrame {

    private JTextField txtIsbn, txtTitre, txtAuteur, txtCategorie, txtTotal, txtDispo;
    private JTextArea area;
    private LivreService service = new LivreService();

    public LivreUI() {

        setTitle("Gestion des Livres");
        setSize(760, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        initUI();
        setVisible(true);
    }

    private void initUI() {

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UIStyle.BACKGROUND);


        JPanel form = new JPanel(new GridLayout(6, 2, 12, 12));
        form.setBackground(UIStyle.PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtIsbn = UIStyle.createTextField();
        txtTitre = UIStyle.createTextField();
        txtAuteur = UIStyle.createTextField();
        txtCategorie = UIStyle.createTextField();
        txtTotal = UIStyle.createTextField();
        txtDispo = UIStyle.createTextField();

        form.add(UIStyle.createLabel("ISBN *"));
        form.add(txtIsbn);
        form.add(UIStyle.createLabel("Titre *"));
        form.add(txtTitre);
        form.add(UIStyle.createLabel("Auteur"));
        form.add(txtAuteur);
        form.add(UIStyle.createLabel("Catégorie"));
        form.add(txtCategorie);
        form.add(UIStyle.createLabel("Exemplaires Totaux *"));
        form.add(txtTotal);
        form.add(UIStyle.createLabel("Exemplaires Disponibles *"));
        form.add(txtDispo);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIStyle.BACKGROUND);

        JButton btnAdd = UIStyle.createButton("Ajouter");
        JButton btnList = UIStyle.createButton("Lister");
        JButton btnDelete = UIStyle.createButton("Supprimer");
        JButton btnExport = UIStyle.createButton("Exporter CSV");
        JButton btnImport = UIStyle.createButton("Importer CSV");

        buttons.add(btnAdd);
        buttons.add(btnList);
        buttons.add(btnDelete);
        buttons.add(btnExport);
        buttons.add(btnImport);

        area = new JTextArea(10, 40);
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createTitledBorder("Catalogue"));

        main.add(form, BorderLayout.NORTH);
        main.add(scroll, BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);


        add(main);

        btnAdd.addActionListener(e -> {

            if (txtIsbn.getText().isEmpty() ||
                txtTitre.getText().isEmpty() ||
                txtTotal.getText().isEmpty() ||
                txtDispo.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Veuillez remplir tous les champs obligatoires (*)",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                int total = Integer.parseInt(txtTotal.getText());
                int dispo = Integer.parseInt(txtDispo.getText());

                if (dispo > total || total < 0 || dispo < 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Valeurs numériques invalides",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                Livre livre = new Livre(
                        txtIsbn.getText().trim(),
                        txtTitre.getText().trim(),
                        txtAuteur.getText().trim(),
                        txtCategorie.getText().trim(),
                        total,
                        dispo
                );

                service.ajouterLivre(livre);

                JOptionPane.showMessageDialog(this, "Livre ajouté avec succès");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Veuillez saisir des nombres valides",
                        "Erreur de saisie",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnList.addActionListener(e -> {
            area.setText("");
            for (Livre l : service.getLivres()) {
                area.append(
                        l.getIsbn() + " | " +
                        l.getTitre() + " | " +
                        l.getAuteur() + " | " +
                        l.getCategorie() + " | " +
                        l.getNbExemplairesDisponibles() + "/" +
                        l.getNbExemplairesTotal() + "\n"
                );
            }
        });

        btnDelete.addActionListener(e -> {
            if (txtIsbn.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Veuillez saisir l'ISBN",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            service.supprimerLivre(txtIsbn.getText().trim());
            JOptionPane.showMessageDialog(this, "Livre supprimé");
        });

        btnExport.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Exporter les livres");
            chooser.setSelectedFile(new java.io.File("livres.csv"));

            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                CSVUtil.exportLivres(
                        service.getLivres(),
                        chooser.getSelectedFile().getAbsolutePath()
                );
                JOptionPane.showMessageDialog(this, "Export CSV terminé");
            }
        });

        btnImport.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Importer un fichier CSV");
            chooser.setFileFilter(
                    new FileNameExtensionFilter("Fichiers CSV", "csv")
            );

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {
                    service.importerCSV(
                            chooser.getSelectedFile().getAbsolutePath()
                    );
                    JOptionPane.showMessageDialog(
                            this,
                            "Import CSV réussi",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Erreur lors de l'import",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}
