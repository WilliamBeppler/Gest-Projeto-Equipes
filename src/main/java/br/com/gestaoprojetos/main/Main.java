package br.com.gestaoprojetos.main;

import br.com.gestaoprojetos.view.TelaLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Ponto de entrada da aplicação.
 */
public class Main {
    public static void main(String[] args) {
        // Define o Look and Feel para uma aparência mais moderna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Garante que a interface gráfica seja criada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
