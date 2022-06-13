import javax.swing.*;

public class Window extends JFrame {

    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 700;
    public Window() {
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.add(gamePanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
