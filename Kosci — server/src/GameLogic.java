import java.util.Arrays;
import java.util.Random;

public class GameLogic {
    //private Player current_player = new Player();
    private  int maxRoll = 3;
    private int dice, dice1, dice2, dice3, dice4, dice5;


    int roll(){
        Random r = new Random();
        dice = r.nextInt( 6 )+1;
        return dice;
    }

    public int[] check (int dice1, int dice2, int dice3, int dice4, int dice5){
         int dices[] = {dice1,dice2,dice3,dice4,dice5};
         Arrays.sort(dices);

  //checking how many dice we threw
        int dicesValue[] = {0,0,0,0,0,0};
        for(int i = 0; i<5; i++) {
            int a = dices[i];
            switch (a) {
                case 1:
                    dicesValue[0] += 1;
                    break;
                case 2:
                    dicesValue[1] += 1;
                    break;
                case 3:
                    dicesValue[2] += 1;
                    break;
                case 4:
                    dicesValue[3] += 1;
                    break;
                case 5:
                    dicesValue[4] += 1;
                    break;
                case 6:
                    dicesValue[5] += 1;
                    break;
            }
        }
        // score[] contains points for : {1,2,3,4,5,6,premia,suma,3x,4x,2+3x,ms,ds,g,sz}
        int score[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int msOrDs = 0;
        int full = 0;
        for(int i = 0; i<6; i++){
           int a = dicesValue[i];
           score[i] = a*(i+1);
           score[14] += a*(i+1);
           if(a >= 3)
               for(int j = 0; j<6; j++ ) {
                   int b = dicesValue[j];
                   score[8] += b*(j+1);
               }
           if(a >= 4)
               for(int j = 0; j<6; j++ ) {
                   int b = dicesValue[j];
                   score[9] += b*(j+1);
               }
           if(a == 5)
               score[13] = 50;
           if(a >= 1) {
               msOrDs += 1;
               if (msOrDs == 4 &&
                       ((dicesValue[0] == 0 && dicesValue[1] == 0) ||
                               (dicesValue[4] == 0 && dicesValue[5] == 0) ||
                               (dicesValue[0] == 0 && dicesValue[5] == 0)))
                   score[11] = 30;
               if (msOrDs == 5 &&
                       ((dicesValue[4] == 0) || (dicesValue[1] == 0))) {
                   score[11] = 30;
               }


               if (msOrDs == 5 && (dicesValue[0] == 0 || dicesValue[5] == 0)) {
                   score[12] = 40;
                   score[11] = 30;
               }

           }
           if(a == 2 || a == 3) {
               full += a;
               if(full == 5)
                   score[10] = 25;
           }
        }
        return score;
    }


}
