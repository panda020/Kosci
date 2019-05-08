import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image image;
    ImageIcon bg1;

    void setImage(Image image, Image image2) {
        if(Main.click1) {
            this.image = image2;
        }else {
            this.image = image;
        }
        repaint();
    }

    public int random(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
    /*
        @Override
        public void paint(Graphics g){
           // super.paintComponent(g);
            bg1 = new ImageIcon("src/imp_bg.png");
            g.drawImage(bg1.getImage(), 0, 0, null);
        }
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg1 = new ImageIcon("src/Images/imp_bg.png");
        if(Main.click1) {
            g.drawImage(bg1.getImage(), 0, 0, null);
            g.drawImage(image, 10, 10, this);
        }else {
            g.drawImage(bg1.getImage(), 0, 0, null);
            g.drawImage(image, random(0, this.getWidth() - 70), random(0, this.getHeight() - 60), null);
        }
    }
}