package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class RenderManager extends JFrame{

    private MainGame game;

    private int height = 800;
    private int width = 800;

    private JPanel mainPanel;
    private JPanel p0_0;
    private JPanel p0_1;
    private JPanel p0_2;
    private JPanel p0_3;
    private JPanel p1_0;
    private JPanel p1_1;
    private JPanel p1_2;
    private JPanel p1_3;
    private JPanel p2_0;
    private JPanel p2_1;
    private JPanel p2_2;
    private JPanel p2_3;
    private JPanel p3_0;
    private JPanel p3_1;
    private JPanel p3_2;
    private JPanel p3_3;
    private JButton undoButton;
    private JButton newButton;
    private JLabel l0_0;
    private JLabel l0_1;
    private JLabel l0_2;
    private JLabel l0_3;
    private JLabel l1_0;
    private JLabel l1_1;
    private JLabel l1_2;
    private JLabel l1_3;
    private JLabel l2_0;
    private JLabel l2_1;
    private JLabel l2_2;
    private JLabel l2_3;
    private JLabel l3_0;
    private JLabel l3_1;
    private JLabel l3_2;
    private JLabel l3_3;
    private JLabel[][] board = new JLabel[4][4];
    private JLabel[][] lastStep = new JLabel[4][4];
    private JPanel[][] boardPanel = new JPanel[4][4];

    public RenderManager(MainGame Game) {
        game = Game;
        init();
    }

    private void init(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("2048");
        setBounds(screenSize.width/2 -width/2, screenSize.height/2 - height/2, width, height);
        assignLabel();
        assignButton();

        newButton.addActionListener(e -> { restartGame();
        });

        undoButton.addActionListener(e -> { undoStep();
        });
        setVisible(true);

        KeyManager km = new KeyManager(game);
        this.addKeyListener(km);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void assignButton() {
        undoButton = new JButton();
        newButton = new JButton();
    }

    private void assignLabel() {

        mainPanel = new JPanel(new GridLayout(4,4,8,8));
        mainPanel.setBackground(Color.GRAY);

        this.add(mainPanel);

        p0_0 = new JPanel();
        p0_0.setBackground(Color.BLACK);
        p0_0.setLayout(new FlowLayout());
        l0_0 = new JLabel();
        l0_0.setVerticalAlignment(JLabel.CENTER);
        p0_0.add(l0_0, BorderLayout.CENTER);
        mainPanel.add(p0_0);

        p0_1 = new JPanel();
        p0_1.setBackground(Color.BLACK);
        mainPanel.add(p0_1);

        p0_2 = new JPanel();
        p0_2.setBackground(Color.BLACK);
        mainPanel.add(p0_2);

        p0_3 = new JPanel();
        p0_3.setBackground(Color.BLACK);
        mainPanel.add(p0_3);

        //-------------------------

        p1_0 = new JPanel();
        p1_0.setBackground(Color.BLACK);
        mainPanel.add(p1_0);

        p1_1 = new JPanel();
        p1_1.setBackground(Color.BLACK);
        mainPanel.add(p1_1);

        p1_2 = new JPanel();
        p1_2.setBackground(Color.BLACK);
        mainPanel.add(p1_2);

        p1_3 = new JPanel();
        p1_3.setBackground(Color.BLACK);
        mainPanel.add(p1_3);

        //------------------

        p2_0 = new JPanel();
        p2_0.setBackground(Color.BLACK);
        mainPanel.add(p2_0);

        p2_1 = new JPanel();
        p2_1.setBackground(Color.BLACK);
        mainPanel.add(p2_1);

        p2_2 = new JPanel();
        p2_2.setBackground(Color.BLACK);
        mainPanel.add(p2_2);

        p2_3 = new JPanel();
        p2_3.setBackground(Color.BLACK);
        mainPanel.add(p2_3);

        //---------------------

        p3_0 = new JPanel();
        p3_0.setBackground(Color.BLACK);
        mainPanel.add(p3_0);

        p3_1 = new JPanel();
        p3_1.setBackground(Color.BLACK);
        mainPanel.add(p3_1);

        p3_2 = new JPanel();
        p3_2.setBackground(Color.BLACK);
        mainPanel.add(p3_2);

        p3_3 = new JPanel();
        p3_3.setBackground(Color.BLACK);
        mainPanel.add(p3_3);






        l0_0 = new JLabel();
        l0_0.setBackground(Color.GRAY);

        l0_1 = new JLabel();
        l0_1.setBackground(Color.WHITE);

        l0_2 = new JLabel();
        l0_3 = new JLabel();
        l1_0 = new JLabel();
        l1_1 = new JLabel();
        l1_2 = new JLabel();
        l1_3 = new JLabel();
        l2_0 = new JLabel();
        l2_1 = new JLabel();
        l2_2 = new JLabel();
        l2_3 = new JLabel();
        l3_0 = new JLabel();
        l3_1 = new JLabel();
        l3_2 = new JLabel();
        l3_3 = new JLabel();

        board[0][0] = l0_0;
        board[0][1] = l0_1;
        board[0][2] = l0_2;
        board[0][3] = l0_3;
        board[1][0] = l1_0;
        board[1][1] = l1_1;
        board[1][2] = l1_2;
        board[1][3] = l1_3;
        board[2][0] = l2_0;
        board[2][1] = l2_1;
        board[2][2] = l2_2;
        board[2][3] = l2_3;
        board[3][0] = l3_0;
        board[3][1] = l3_1;
        board[3][2] = l3_2;
        board[3][3] = l3_3;

        boardPanel[0][0] = p0_0;
        boardPanel[0][1] = p0_1;
        boardPanel[0][2] = p0_2;
        boardPanel[0][3] = p0_3;
        boardPanel[1][0] = p1_0;
        boardPanel[1][1] = p1_1;
        boardPanel[1][2] = p1_2;
        boardPanel[1][3] = p1_3;
        boardPanel[2][0] = p2_0;
        boardPanel[2][1] = p2_1;
        boardPanel[2][2] = p2_2;
        boardPanel[2][3] = p2_3;
        boardPanel[3][0] = p3_0;
        boardPanel[3][1] = p3_1;
        boardPanel[3][2] = p3_2;
        boardPanel[3][3] = p3_3;
    }

    private void undoStep() {
        board = lastStep;
    }

    private void restartGame() {
    }

    public void renderGrid(Tile[][] matrix) {
        lastStep = board;
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                board[i][j].setText(matrix[i][j].toString());
                board[i][j].setForeground(Color.WHITE);
                board[i][j].setHorizontalAlignment(JLabel.CENTER);
                board[i][j].setFont(new Font("Serif", Font.PLAIN, 45));
                boardPanel[i][j].add(board[i][j], BorderLayout.CENTER);
            }
        }
    }

    public void refreshGrid(Tile[][] matrix) {
        lastStep = board;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j].setText(matrix[i][j].toString());
                if (matrix[i][j].getValue() == 0) {
                    board[i][j].setVisible(false);
                }
                else {
                    board[i][j].setVisible(true);
                }
            }
        }
    }
}
