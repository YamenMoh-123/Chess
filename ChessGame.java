import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame extends JFrame {

    private JButton[][] chessboard;
    private int currX = -1;
    private int currY = -1;
    private boolean clicked = false;

    public ChessGame() {
        super("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        initializeChessboard();
    }

    private void initializeChessboard() {
        chessboard = new JButton[8][8];

        setLayout(new GridLayout(8, 8));

        ActionListener pieceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                // Handle piece movement logic here
                if (!clicked) {
                    currX = source.getX()/47;
                    currY = source.getY()/40;
                    clicked = true;
                } else {
                    System.out.println("Piece clicked: " + currX + currY);
                    System.out.println("Piece clicked: " + source.getX()/47 + source.getY()/40);
                    System.out.println(chessboard[0][0]);
                    clicked = false;
                }

                if (currY >= 0) {
                    System.out.println(currX);
                }
            }
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton square = new JButton();
                square.setName(getChessCoordinate(i, j));
                square.addActionListener(pieceListener);
                chessboard[i][j] = square;

                // Set background color for the chessboard pattern
                if ((i + j) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.BLACK);
                }

                add(square);
            }
        }

        // Set pieces on the board (you can customize this based on your needs)
        setPiece("R", 0, 0); setPiece("N", 0, 1); setPiece("B", 0, 2); setPiece("Q", 0, 3);
        setPiece("K", 0, 4); setPiece("B", 0, 5); setPiece("N", 0, 6); setPiece("R", 0, 7);

        for (int i = 0; i < 8; i++) {
            setPiece("P", 1, i); // Set up pawns in the second row
            setPiece("P", 6, i); // Set up pawns in the seventh row
        }

        setPiece("r", 7, 0); setPiece("n", 7, 1); setPiece("b", 7, 2); setPiece("q", 7, 3);
        setPiece("k", 7, 4); setPiece("b", 7, 5); setPiece("n", 7, 6); setPiece("r", 7, 7);
        // ... continue setting up the initial board state

        setVisible(true);
    }

    private void setPiece(String piece, int row, int col) {
        JButton square = chessboard[row][col];
        System.out.println("90");
        square.setText(piece);
    }

    private void movePiece(String piece, int row, int col) {
        JButton square = chessboard[row][col];
        System.out.println(square.getX()/47);
        System.out.println(square.getY()/47);
        square.setText(piece);

    }

    private String getChessCoordinate(int row, int col) {
        char file = (char) ('a' + col);
        int rank = 8 - row;
        System.out.println(row);
        System.out.println(col);
        return String.valueOf(file) + rank;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGame());
    }
}
