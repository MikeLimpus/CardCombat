import javax.swing.*;

public class TCGDriver {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | 
            InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
        }
        PlayController game = new PlayController();
        game.round();
    }
}
