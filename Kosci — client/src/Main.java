import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Stream;


public class Main {

    static Image dice1, dice2, dice3, dice4, dice5, zaz1, zaz2, zaz3, zaz4, zaz5;
    static ImageIcon d1, d2, d3, d4, d5, d6, z1, z2, z3, z4, z5, z6;
    static boolean click1 = false, click2 = false, click3 = false, click4 = false, click5 = false;
    static int kostka1, kostka2, kostka3, kostka4, kostka5;
    static boolean turn = false;
    static PrintWriter pr;
    static int rolls = 3, gameLength = 0;
    static int selected[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static boolean game = true;
    static String player1Name = "Gracz 1", player2Name = "Gracz 2";
    static int a, b;
    static int suma11, suma12, suma21, suma22, razem, razem1;
    static String ip;

    public static void main(String[] args){

        GameLogic l = new GameLogic();

        d1 = new ImageIcon("src/Images/kostka1.png");
        d2 = new ImageIcon("src/Images/kostka2.png");
        d3 = new ImageIcon("src/Images/kostka3.png");
        d4 = new ImageIcon("src/Images/kostka4.png");
        d5 = new ImageIcon("src/Images/kostka5.png");
        d6 = new ImageIcon("src/Images/kostka6.png");
        z1 = new ImageIcon("src/Images/zaznaczenie1.png");
        z2 = new ImageIcon("src/Images/zaznaczenie2.png");
        z3 = new ImageIcon("src/Images/zaznaczenie3.png");
        z4 = new ImageIcon("src/Images/zaznaczenie4.png");
        z5 = new ImageIcon("src/Images/zaznaczenie5.png");
        z6 = new ImageIcon("src/Images/zaznaczenie6.png");


        player2Name = JOptionPane.showInputDialog("Podaj swoję imię");
        if(player2Name == null){
            player2Name = "Gracz 2";
        }

        ip = JOptionPane.showInputDialog("Podaj ip serwera w postaci xxx.xxx.xxx.xxx");

        //tworzenie klienta
        Socket s = null;
        try {
            s = new Socket(ip, 4999); // change ip
            // s = new Socket("localhost",400);
            pr = new PrintWriter( s.getOutputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        pr.println( player2Name );
        pr.flush();
        InputStreamReader in = null;
        BufferedReader bf = null;
        String name;
        try {
            in = new InputStreamReader( s.getInputStream() );
            bf = new BufferedReader( in );
            name = bf.readLine();
            player1Name = name;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Board board = new Board();
        board.createBoard();

        board.imp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (!click1) {
                    click1 = true;
                    board.imp.setImage(dice1, zaz1);
                }else{
                    click1 = false;
                    board.imp.setImage(dice1, zaz1);
                }
            }
        });
        board.imp1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (!click2) {
                    click2 = true;
                    board.imp1.setImage(dice2, zaz2);
                }else{
                    click2 = false;
                    board.imp1.setImage(dice2, zaz2);
                }
            }
        });
        board.imp2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (!click3) {
                    click3 = true;
                    board.imp2.setImage(dice3, zaz3);
                }else{
                    click3 = false;
                    board.imp2.setImage(dice3, zaz3);
                }
            }
        });
        board.imp3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (!click4) {
                    click4 = true;
                    board.imp3.setImage(dice4, zaz4);
                }else{
                    click4 = false;
                    board.imp3.setImage(dice4, zaz4);
                }
            }
        });
        board.imp4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (!click5) {
                    click5 = true;
                    board.imp4.setImage(dice5, zaz5);
                }else{
                    click5 = false;
                    board.imp4.setImage(dice5, zaz5);
                }
            }
        });


        Socket finalS = s;
        board.button.addActionListener( new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (turn) {
                                                    GameLogic l = new GameLogic();
                                                    if (rolls > 0) {
                                                        rolls -= 1;
                                                        board.text.setText("Tura gracza: " + "\n" + player2Name + "\nPozostało \nrzutów: " + rolls);

                                                        if (!click1) {
                                                            kostka1 = l.roll();
                                                            dice1 = getDice( kostka1 );
                                                            zaz1 = getZaz( kostka1 );
                                                        }
                                                        if (!click2) {
                                                            kostka2 = l.roll();
                                                            dice2 = getDice( kostka2 );
                                                            zaz2 = getZaz( kostka2 );
                                                        }
                                                        if (!click3) {
                                                            kostka3 = l.roll();
                                                            dice3 = getDice( kostka3 );
                                                            zaz3 = getZaz( kostka3 );
                                                        }
                                                        if (!click4) {
                                                            kostka4 = l.roll();
                                                            dice4 = getDice( kostka4 );
                                                            zaz4 = getZaz( kostka4 );
                                                        }
                                                        if (!click5) {
                                                            kostka5 = l.roll();
                                                            dice5 = getDice( kostka5 );
                                                            zaz5 = getZaz( kostka5 );
                                                        }

                                                        int tab[] = l.check( kostka1, kostka2, kostka3, kostka4, kostka5 );

                                                        for (int i = 0; i < 17; i++) {

                                                            if (board.table.isRowSelected( i ) == false && selected[i] == 0) {
                                                                board.table.setValueAt( tab[i], i, 2 );
                                                                board.table.setValueAt(suma21,7,2);
                                                                board.table.setValueAt(suma22,15,2);
                                                                if(suma21 >= 63){
                                                                    board.table.setValueAt("35", 6, 2);
                                                                }
                                                                board.table.setValueAt(razem1,16,2);                                }
                                                            else{
                                                                selected[i] = 1;
                                                            }

                                                        }
                                                        board.imp.setImage( dice1, zaz1 );
                                                        board.imp1.setImage( dice2, zaz2 );
                                                        board.imp2.setImage( dice3, zaz3 );
                                                        board.imp3.setImage( dice4, zaz4 );
                                                        board.imp4.setImage( dice5, zaz5 );

                                                        pr.println( kostka1 + "," + kostka2 + "," + kostka3 + "," + kostka4 + "," + kostka5 );
                                                        pr.flush();
                                                    }
                                                }
                                            }
                                        }
        );

        board.button1.addActionListener( new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 gameLength += 1;
                                                 kostka1 = 0; kostka2 = 0; kostka3 = 0; kostka4 = 0; kostka5 = 0;
                                                 pr.println( kostka1 + "," + kostka2 + "," + kostka3 + "," + kostka4 + "," + kostka5 );
                                                 pr.flush();
                                                 click1 = false; click2 = false; click3 = false; click4 = false; click5 = false;
                                                 pr.flush();
                                                 board.text.setText("Tura gracza: " + "\n" + player1Name + "\nPozostało \nrzutów: " + 3);
                                                 for (int i = 0; i < 17; i++) {
                                                     board.table.setValueAt(suma21, 7, 2);
                                                     board.table.setValueAt(suma22, 15, 2);
                                                     if(suma21 >= 63){
                                                         board.table.setValueAt("35", 6, 2);
                                                     }
                                                     razem1 = suma21 + suma22 + Integer.parseInt( String.valueOf( board.table.getValueAt( 6, 2 ) ));
                                                     board.table.setValueAt(razem1,16,2);
                                                     if (board.table.isRowSelected( i ) == true) {
                                                         if (i < 7) {
                                                             suma21 += Integer.parseInt( String.valueOf( board.table.getValueAt( i, 2 ) ) );
                                                         } else if (7 < i && i < 15) {
                                                             suma22 += Integer.parseInt( String.valueOf( board.table.getValueAt( i, 2 ) ) );
                                                         }
                                                         a = Integer.parseInt( String.valueOf( board.table.getValueAt( i, 2 ) ) );
                                                         b = i;
                                                     }
                                                     pr.println(6 + "," + Integer.parseInt( String.valueOf( board.table.getValueAt( 6, 2))));
                                                     pr.println( 7 + "," + Integer.parseInt( String.valueOf( board.table.getValueAt( 7, 2 ) ) ) );
                                                     pr.println( 15 + "," + Integer.parseInt( String.valueOf( board.table.getValueAt( 15, 2 ) ) ) );
                                                     pr.println( 16 + "," + Integer.parseInt( String.valueOf( board.table.getValueAt( 16, 2 ) ) ) );

                                                 }
                                                 pr.println(b + "," + a);
                                                 turn = false;
                                                 int code = 11;
                                                 pr.println( code );
                                                 rolls = 3;
                                                 System.out.println( gameLength );
                                                 if(gameLength > 12) {
                                                     System.out.println( "Koniec gry" );
                                                     game = false;
                                                     int suma1 = Integer.parseInt( String.valueOf( board.table.getValueAt( 16, 1 )));
                                                     int suma2 = Integer.parseInt( String.valueOf( board.table.getValueAt( 16, 2 )));
                                                     if(suma1>suma2) {
                                                         String wygrany = player1Name;
                                                         int a = JOptionPane.showConfirmDialog(null,"Koniec rozgrywki, wygrał/a:" + player1Name, "Tytuł okienka", JOptionPane.CANCEL_OPTION);
                                                     }
                                                     if(suma1 < suma2){
                                                         String wygrany = player2Name;
                                                         int a = JOptionPane.showConfirmDialog(null,"Koniec rozgrywki, wygrał/a:" + player2Name,"Tytuł okienka", JOptionPane.CANCEL_OPTION);
                                                     }
                                                     if(suma1 == suma2){
                                                         String wygrany = "remis";
                                                         int a = JOptionPane.showConfirmDialog(null,"Koniec rozgrywki, remis","Tytuł okienka", JOptionPane.CANCEL_OPTION);
                                                     }
                                                 }
                                                 pr.println( code );
                                                 pr.flush();
                                             }
                                         }
        );
        while(game) {
            String str;
            int[] message;
            try {
                in = new InputStreamReader( s.getInputStream() );
                bf = new BufferedReader( in );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            while (!turn) {
                try {
                    str = bf.readLine();
                    System.out.println( "otrzymano: " + str );
                    message = parseSocketMsg( str );
                    if (message.length == 5) {
                        dice1 = getDice( message[0] );
                        dice2 = getDice( message[1] );
                        dice3 = getDice( message[2] );
                        dice4 = getDice( message[3] );
                        dice5 = getDice( message[4] );

                        board.imp.setImage( dice1, zaz1 );
                        board.imp1.setImage( dice2, zaz2 );
                        board.imp2.setImage( dice3, zaz3 );
                        board.imp3.setImage( dice4, zaz4 );
                        board.imp4.setImage( dice5, zaz5 );

                        int tab[] = l.check( message[0], message[1], message[2], message[3], message[4] );
                    }

                    if (message[0] == 11) {
                        turn = true;
                        rolls = 3;
                        board.text.setText("Tura gracza: " + "\n" + player2Name + "\nPozostało \nrzutów: " + rolls);
                        System.out.println(turn);
                    }
                    if(message.length == 2) {
                        board.table.setValueAt( message[1], message[0], 1 );
                    }

                    System.out.println( message );
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static Image getZaz(int kostka) {
        Image zaz;
        switch (kostka) {
            case 1:
                zaz = z1.getImage();
                break;
            case 2:
                zaz = z2.getImage();
                break;
            case 3:
                zaz = z3.getImage();
                break;
            case 4:
                zaz = z4.getImage();
                break;
            case 5:
                zaz = z5.getImage();
                break;
            case 6:
                zaz = z6.getImage();
                break;
            default:
                zaz = null;
        }
        return zaz;
    }

    private static Image getDice(int kostka) {
        Image dice;
        switch (kostka) {
            case 1:
                dice = d1.getImage();
                break;
            case 2:
                dice = d2.getImage();
                break;
            case 3:
                dice = d3.getImage();
                break;
            case 4:
                dice = d4.getImage();

                break;
            case 5:
                dice = d5.getImage();

                break;
            case 6:
                dice = d6.getImage();
                break;
            default:
                dice = null;
        }
        return dice;
    }

    private static int[] parseSocketMsg(String str) {
        String[] msg = str.split(",");
        int[] intArr = Stream.of(msg)
                .mapToInt(nb -> Integer.parseInt(nb))
                .toArray();

        if (intArr.length == 5)
            return intArr;
        if (intArr[0] == 11) {
            turn = true;
            return intArr;
        }
        if(intArr.length == 2)
            return intArr;

        return  null;
    }

}