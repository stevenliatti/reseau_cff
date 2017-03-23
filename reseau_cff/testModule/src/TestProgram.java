import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by raed on 21.03.17.
 */
public class TestProgram {
    private String[] testsNamesList = {"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"};

    private JPanel mainPanel;
    private JButton startButton;
    private JComboBox testsList;
    private JTextArea leftTextArea;
    private JTextArea rightTextArea;
    private JLabel firstCommandTextArea;
    private JLabel secondCommandTextArea;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel stateLabel;
    private JPanel statePanel;

    public TestProgram() {
        startButton.addActionListener(e -> executeCommand(testsList.getSelectedIndex() + 1));
    }

    private void executeCommand(int x) {
        int lineOfFirstDiff = 0;
        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();
        try {
            leftLabel.setText("my_result_" + x + ".txt");
            rightLabel.setText("_" + x + ".txt");

//            final Process proce = Runtime.getRuntime().exec(new String[] {"bash", "-c", "pwd"});
//            proce.waitFor(1000, TimeUnit.MILLISECONDS);
//            BufferedReader readerd = new BufferedReader(new InputStreamReader(proce.getInputStream()));
//            String lf;
//            try {
//                while((lf = readerd.readLine()) != null) {
//                    System.out.println(lf);
//                }
//            } finally {
//                readerd.close();
//            }

            String cmd1 = "cat testModule/commandes_de_test/cmd_a_tester_pt_" + x + ".txt | " +
                    "java -cp out/production/reseau_cff Main > testModule/results_out/my_results_" + x + ".txt";
            firstCommandTextArea.setText(cmd1);
            final Process process1 = Runtime.getRuntime().exec(new String[] {"bash", "-c", cmd1});
            process1.waitFor(1000, TimeUnit.MILLISECONDS);
//            System.out.println(process1.exitValue());
//            if (process1.exitValue() != 0) {
//                BufferedReader readerErr = new BufferedReader(new InputStreamReader(process1.getErrorStream()));
//                String l;
//                try {
//                    while((l = readerErr.readLine()) != null) {
//                        System.out.println(l);
//                    }
//                } finally {
//                    readerErr.close();
//                }
//            }

//            String cmdLeft = "diff -y testModule/results_out/my_results_" + x + ".txt testModule/results_out/_" + x + ".txt";
            String cmdLeft = "cat testModule/results_out/my_results_" + x + ".txt";
            secondCommandTextArea.setText(cmdLeft);
//            cmdLeft += " | awk -F ' ' '{print $1}' ";
            final Process processLeft = Runtime.getRuntime().exec(new String[] {"bash", "-c", cmdLeft});
            processLeft.waitFor(1000, TimeUnit.MILLISECONDS);
//            System.out.println(processLeft.exitValue());

//            String cmdRight = "diff -y testModule/results_out/my_results_" + x + ".txt testModule/results_out/_" + x + ".txt" +
//                    " | awk -F ' ' '{print $2 \" \" $3}' ";
            String cmdRight = "cat testModule/results_out/_" + x + ".txt";
            final Process processrRight = Runtime.getRuntime().exec(new String[] {"bash", "-c", cmdRight});
            processrRight.waitFor(1000, TimeUnit.MILLISECONDS);
//            System.out.println(processrRight.exitValue());

            BufferedReader readerLeft = new BufferedReader(new InputStreamReader(processLeft.getInputStream()));
            BufferedReader readerRight = new BufferedReader(new InputStreamReader(processrRight.getInputStream()));
            String line;
            String content = "";
            int i = 0;
            try {
                while((line = readerLeft.readLine()) != null) {
                    left.add(line);
                    content += i + ".\t" + line + "\n";
                    i++;
                }
                leftTextArea.setText(content);
                content = "";
                i = 0;
                while((line = readerRight.readLine()) != null) {
                    right.add(line);
                    content += i + ".\t" + line + "\n";
                    i++;
                }
                rightTextArea.setText(content);
            } finally {
                readerLeft.close();
                readerRight.close();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int infSize = left.size() > right.size() ? right.size() : left.size();

        for (int i = 0; i < infSize; i++) {
            if (!left.get(i).equals(right.get(i))) {
                lineOfFirstDiff = i;
                break;
            }
        }

        if (leftTextArea.getText().equals(rightTextArea.getText())) {
            stateLabel.setText("Test validé!");
            statePanel.setBackground(Color.green);
        } else {
            stateLabel.setText("Test échoué!\tPremière différence : ligne " + lineOfFirstDiff);
            statePanel.setBackground(Color.red);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        testsList = new JComboBox(testsNamesList);
    }
}
