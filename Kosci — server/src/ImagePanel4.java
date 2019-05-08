import javax.swing.*;
import java.awt.*;

public class ImagePanel4 extends JPanel {

    private Image image4;
    ImageIcon bg5;

    void setImage(Image image, Image image2) {
        if(Main.click5) {
            this.image4 = image2;
        }else {
            this.image4 = image;
        }
        repaint();
    }

    public int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg5 = new ImageIcon("src/Images/imp4_bg.png");
        if(Main.click5) {
            g.drawImage(bg5.getImage(), 0, 0, null);
            g.drawImage(image4, 10, 10, this);
        }else {
            g.drawImage(bg5.getImage(), 0, 0, null);
            g.drawImage(image4, random(0, this.getWidth() - 70), random(0, this.getHeight() - 60), null);
        }
    }
}