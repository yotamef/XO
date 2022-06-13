import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private int xPoints;
    private int oPoints;
    private List<JButton> buttons;
    private List<JLabel> labels;
    private char[] currentPlayer;

    public GamePanel(int width, int height) {
        this.setBounds(0,0,width,height);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        xPoints=0;
        oPoints=0;
        buttons = new ArrayList<>();
        labels = new ArrayList<>();
        currentPlayer = new char[1];
        currentPlayer[0] = 'x';

        JLabel score = new JLabel("x points: "+xPoints+" , o points: "+oPoints);
        score.setBounds(0,0,200,50);
        score.setVisible(true);
        this.add(score);

        for (int y=100; y<700; y= y+BUTTON_SIZE) {
            for (int x=0; x<=400; x = x+BUTTON_SIZE) {
                JButton button = new JButton();
                button.setBounds(x,y,BUTTON_SIZE,BUTTON_SIZE);
                button.setVisible(true);
                buttons.add(button);
                this.add(button);
                JLabel label = new JLabel();
                label.setBounds(button.getX(),button.getY(),button.getWidth(),button.getHeight());
                label.setVisible(false);
                this.add(label);
                labels.add(label);
            }
        }


        for (int i=0;i<buttons.size(); i++) {
            int[] finalI = new int[1];
            finalI[0] = i;
            buttons.get(i).addActionListener(event -> {
                buttons.get(finalI[0]).setVisible(false);
                labels.get(finalI[0]).setText(currentPlayer[0]+"");
                labels.get(finalI[0]).setVisible(true);
                boolean won = checkWinner();


                if (won) {
                    new Thread(() -> {
                        
                        buttons.get(finalI[0]).setVisible(false);
                        repaint();
                        for (JButton button : buttons) {
                            button.setVisible(true);
                        }
                        for (JLabel label : labels) {
                            label.setText("");
                        }
                        if (currentPlayer[0] == 'x') {
                            xPoints++;
                        } else {
                            oPoints++;
                        }
                        score.setText("x points: " + xPoints + " , o points: " + oPoints);
                        repaint();
                    }).start();
                }

                if (won) {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {

                    }
                }


                if (currentPlayer[0]=='x') {
                    currentPlayer[0] = '0';
                } else  {
                    currentPlayer[0] = 'x';
                }

            });
        }
    }

    public boolean checkWinner() {
        boolean won = false;
        for (int i=0; i<3; i++) {
            if (labels.get(i).getText().equals(labels.get(i + 3).getText()) && labels.get(i).getText().equals(labels.get(i + 6).getText()) && (labels.get(i).getText().equals("x") || labels.get(i).getText().equals("0"))) {
                won = true;
                break;
            }
        }
        if (!won) {
            for (int i=0; i<9; i= i+3) {
                if (labels.get(i).getText().equals(labels.get(i + 1).getText()) && labels.get(i).getText().equals(labels.get(i + 2).getText()) && (labels.get(i).getText().equals("x") || labels.get(i).getText().equals("0"))) {
                    won = true;
                    break;
                }
            }
        }
        if (!won) {
            if (labels.get(0).getText().equals(labels.get(4).getText()) && labels.get(0).getText().equals(labels.get(8).getText()) && (labels.get(0).getText().equals("x") || labels.get(0).getText().equals("0"))) {
                won = true;
            }
        }
        if (!won) {
            if (labels.get(2).getText().equals(labels.get(4).getText()) && labels.get(2).getText().equals(labels.get(6).getText()) && (labels.get(2).getText().equals("x") || labels.get(2).getText().equals("0"))) {
                won = true;
            }
        }

        return won;
    }
    public static final int BUTTON_SIZE = 200;
}
