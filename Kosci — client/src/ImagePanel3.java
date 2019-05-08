import javax.swing.*;
import java.awt.*;

public class ImagePanel3 extends JPanel {

    private Image image3;
    ImageIcon bg4;

    void setImage(Image image, Image image2) {
        if(Main.click4) {
            this.image3 = image2;
        }else {
            this.image3 = image;
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
        bg4 = new ImageIcon("src/Images/imp3_bg.png");
        if(Main.click4) {
            g.drawImage(bg4.getImage(), 0, 0, null);
            g.drawImage(image3, 10, 10, this);
        }else {
            g.drawImage(bg4.getImage(), 0, 0, null);
            g.drawImage(image3, random(0, this.getWidth() - 70), random(0, this.getHeight() - 60), null);
        }
    }
}