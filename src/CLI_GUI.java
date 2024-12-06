import javax.swing.*;
import java.awt.event.*;

public class CLI_GUI extends JDialog {
    private JPanel contentPane;
    private JButton CLIButton;
    private JButton GUIButton;

    public CLI_GUI() {
        setContentPane(contentPane);
        setModal(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == CLIButton) {
                    AutoCompleteCLI.main(new String[]{});
                    CLI_GUI.this.dispose();
                } else if (e.getSource() == GUIButton) {
                    CLI_GUI.this.setVisible(false);
                    AutoCompleteGUI.main(new String[]{});
                }
            }
        };
        CLIButton.addActionListener(listener);
        GUIButton.addActionListener(listener);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CLI_GUI dialog = new CLI_GUI();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
