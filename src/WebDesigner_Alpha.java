import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebDesigner_Alpha extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox h1CheckBox;
    private JTextField textField3;
    private JButton chooseFileButton;

    public WebDesigner_Alpha() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        final JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField3.setText(selectedFile.getPath());
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
    }

    private void onOK() {
        // add your code here
        File myObj = new File(textField3.getText()+"\\"+textField1.getText()+".txt");


        try {
            FileWriter myWriter = new FileWriter(myObj);

            System.out.println(textField3.getText());
            String HTMLCode = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>"+textField1.getText()+"</title>\n" +
                    "</head>\n" +
                    "<body>\n";
            if (h1CheckBox.isSelected()) {
               String extraTagsOpen = "<h1>";
               String extraTagsClose = "</h1>";

               HTMLCode += extraTagsOpen+textField2.getText()+extraTagsClose + "</body>\n"+"</html>";
                System.out.println(HTMLCode);
            }
            else
            {
                HTMLCode += "<span>"+textField2.getText().toString()+"</span>"+"</body>\n"+"</html>";
            }
            myWriter.write(HTMLCode);
            myWriter.close();

            File webPage = new File(textField3.getText()+"\\"+textField1.getText()+".html");

            myObj.renameTo(webPage);
            System.out.println("success");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        WebDesigner_Alpha dialog = new WebDesigner_Alpha();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
