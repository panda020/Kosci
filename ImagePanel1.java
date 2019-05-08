import javax.swing.*;
import java.awt.*;

public class ImagePanel1 extends JPanel {

    private Image image1;
    ImageIcon bg2;

    void setImage(Image image, Image image2) {
        if(Main.click2) {
            this.image1 = image2;
        }else {
            this.image1 = image;
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
        bg2 = new ImageIcon("src/Images/imp1_bg.png");
        if(Main.click2) {
            g.drawImage(bg2.getImage(), 0, 0, null);
            g.drawImage(image1, 10, 10, this);
        }else {
            g.drawImage(bg2.getImage(), 0, 0, null);
            g.drawImage(image1, random(0, this.getWidth() - 70), random(0, this.getHeight() - 60), null);
        }
    }
}