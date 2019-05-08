import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


public class Board {

    JFrame frame;
    ImagePanel imp;
    ImagePanel1 imp1;
    ImagePanel2 imp2;
    ImagePanel3 imp3;
    ImagePanel4 imp4;
    ImagePanel5 imp5;
    ImagePanel6 imp6;
    JButton button, button1;
    JTable table;
    JScrollPane scrollPane;
    JTextArea text;

    ImageIcon button_bg = new ImageIcon("src/button_bg.png");
    ImageIcon table_bg = new ImageIcon("src/table_bg.png");

    public void createBoard() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = null;
        Insets insets = new Insets(0, 0, 0, 0);

        frame = new JFrame("Kosci");
        frame.getContentPane().setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 700));

        imp = new ImagePanel();
        imp.setPreferredSize(new Dimension(500, 120));
        c = new GridBagConstraints(1, 0, 1, 1, 0.6, 0.8, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, insets, 100, 200);
        frame.getContentPane().add(imp, c);

        imp1 = new ImagePanel1();
        imp1.setPreferredSize(new Dimension(500, 120));
        c = new GridBagConstraints(1, 1, 1, 1, 0.6, 0.8, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, insets, 100, 200);
        frame.getContentPane().add(imp1, c);

        imp2 = new ImagePanel2();
        imp2.setPreferredSize(new Dimension(500, 120));
        c = new GridBagConstraints(1, 2, 1, 1, 0.6, 0.8, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, insets, 100, 200);
        frame.getContentPane().add(imp2, c);

        imp3 = new ImagePanel3();
        imp3.setPreferredSize(new Dimension(500, 120));
        c = new GridBagConstraints(1, 3, 1, 1, 0.6, 0.8, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, insets, 100, 200);
        frame.getContentPane().add(imp3, c);

        imp4 = new ImagePanel4();
        imp4.setPreferredSize(new Dimension(500, 120));
        c = new GridBagConstraints(1, 4, 1, 1, 0.6, 0.8, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, insets, 100, 200);
        frame.getContentPane().add(imp4, c);


        button = new JButton("Rzut");
        button.setPreferredSize(new Dimension(140, 45));
        button.setBackground(new Color(0,0,0,0));
        button.setOpaque(false);
        button.setFont(new Font("Kalam", Font.BOLD, 20));
        button.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.WHITE));
        button.setForeground(Color.WHITE);

        button1 = new JButton( "Potwierdź");
        button1.setPreferredSize(new Dimension(140, 45));
        button1.setBackground(new Color(0,0,0,0));
        button1.setOpaque(false);
        button1.setFont(new Font("Kalam", Font.BOLD, 20));
        button1.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.WHITE));
        button1.setForeground(Color.WHITE);

        text = new JTextArea(  );
        text.setPreferredSize(new Dimension(100,70));
        text.setOpaque(false);
        text.setFont(new Font("Kalam", Font.BOLD, 12));
        text.setBorder(null);
        text.setForeground(Color.WHITE);
        text.setText("Tura gracza: " + "\n" + Main.player1Name + "\nPozostało \nrzutów: " + 3);

        imp6 = new ImagePanel6();
        imp6.setPreferredSize(new Dimension(500, 100));
        imp6.add(button, new FlowLayout(FlowLayout.CENTER));
        imp6.add(button1, new FlowLayout(FlowLayout.LEFT));
        imp6.add(text, new FlowLayout( FlowLayout.RIGHT ));
        c = new GridBagConstraints(1, 5, 1, 1, 0.6, 0.2, GridBagConstraints.PAGE_END, GridBagConstraints.BOTH, insets, 100, 50);
        frame.getContentPane().add(imp6, c);


        TableModel model = new TableModel();

        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

                Component c = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent) c;

                if (row == 6 || row == 15) {
                    int top = 2;
                    int left = 0;
                    int bottom = 2;
                    int right = 0;
                    jc.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.WHITE));
                }
                if (row == 7 || row == 16) {
                    int top = 0;
                    int left = 0;
                    int bottom = 2;
                    int right = 0;
                    jc.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.WHITE));
                }

                if (isRowSelected(row) && row != 6 && row != 7 && row != 15 && row != 16) {
                    int top = (row > 0 && isRowSelected(row - 1)) ? 1 : 2;
                    int left = column == 0 ? 2 : 0;
                    int bottom = (row < getRowCount() - 1 && isRowSelected(row + 1)) ? 1 : 2;
                    int right = column == getColumnCount() - 1 ? 2 : 0;

                    jc.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, this.getSelectionBackground()));
                }

                if(column == 1 || column == 2){
                    jc.setFont(new Font("Kalam", Font.BOLD, 16));
                }

                return c;
            }
        };

        table.setPreferredSize(new Dimension(300, 595));
        for (int i = 0; i < 18; i++) {
            table.setRowHeight(i, 35);
        }
        table.setRowSelectionAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 3; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 618));

        imp5 = new ImagePanel5();
        imp5.setPreferredSize(new Dimension(300, 700));
        c = new GridBagConstraints(0, 0, 1, 6, 0.4, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, insets, 210, 0);
        frame.getContentPane().add(imp5, c);
        imp5.add(scrollPane);

        table.setFont(new Font("Kalam", Font.BOLD, 12));
        table.setForeground(Color.white);
        table.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));

        table.setBackground(new Color(0,0,0,0));
        table.setOpaque(false);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);


        frame.pack();
        frame.setVisible(true);
    }
}