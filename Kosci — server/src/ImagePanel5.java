import javax.swing.*;
import java.awt.*;

public class ImagePanel5 extends JPanel {

    ImageIcon table_bg;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_bg = new ImageIcon("src/Images/table_bg.png");
        g.drawImage(table_bg.getImage(), 0, 0, null);
    }
}
