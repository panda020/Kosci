import javax.swing.*;
import java.awt.*;

public class ImagePanel2 extends JPanel {

    private Image image2;
    ImageIcon bg3;

    void setImage(Image image, Image image2) {
        if(Main.click3) {
            this.image2 = image2;
        }else {
            this.image2 = image;
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
        bg3 = new ImageIcon("src/Images/imp2_bg.png");
        if(Main.click3) {
            g.drawImage(bg3.getImage(), 0, 0, null);
            g.drawImage(image2, 10, 10, this);
        }else {
            g.drawImage(bg3.getImage(), 0, 0, null);
            g.drawImage(image2, random(0, this.getWidth() - 70), random(0, this.getHeight() - 60), null);
        }
    }
}