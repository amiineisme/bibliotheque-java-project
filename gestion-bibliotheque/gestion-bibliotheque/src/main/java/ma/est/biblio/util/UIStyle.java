package ma.est.biblio.util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIStyle {

    public static final Color BACKGROUND = new Color(15, 23, 42); 
    public static final Color PANEL = new Color(30, 41, 59);        
    public static final Color PRIMARY = new Color(56, 189, 248); 
    public static final Color PRIMARY_HOVER = new Color(14, 165, 233);
    public static final Color TEXT = new Color(226, 232, 240);    
    public static final Color MUTED_TEXT = new Color(148, 163, 184);
    public static final Color INPUT = new Color(51, 65, 85);       
    public static final Color ERROR = new Color(239, 68, 68);       
    public static final Color SUCCESS = new Color(34, 197, 94); 


    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);


    public static JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(BUTTON_FONT);
        btn.setBackground(PRIMARY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(createRoundedBorder(14, PRIMARY));
        btn.setOpaque(true);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY);
            }
        });

        return btn;
    }


    public static JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(NORMAL_FONT);
        txt.setBackground(INPUT);
        txt.setForeground(TEXT);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(createRoundedBorder(12, INPUT));
        return txt;
    }

    public static JPasswordField createPasswordField() {
        JPasswordField txt = new JPasswordField();
        txt.setFont(NORMAL_FONT);
        txt.setBackground(INPUT);
        txt.setForeground(TEXT);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(createRoundedBorder(12, INPUT));
        return txt;
    }

    

    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(NORMAL_FONT);
        lbl.setForeground(TEXT);
        return lbl;
    }

    public static JLabel createTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(TITLE_FONT);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    public static JLabel createMutedLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(NORMAL_FONT);
        lbl.setForeground(MUTED_TEXT);
        return lbl;
    }


    public static JPanel createPanel() {
        JPanel p = new JPanel();
        p.setBackground(PANEL);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return p;
    }


    private static Border createRoundedBorder(int radius, Color color) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 1, true),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        );
    }
}
