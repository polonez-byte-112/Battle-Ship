package com.college;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicalUserInterface extends JFrame {


    public int SCREEN_HEIGHT=800;
    public int SCREEN_WIDTH=600;
    public int spacing=2;


    public int mouseX=-100;
    public int mouseY=-100;

    public int gameMode=-1;
    public boolean creationPlayerBTurn=false;
    // -1 (budowanie statków)
    // 0 gra
    //1 koniec gry
    Color hit = new Color(255,115,115);
    Color unhitedShips = new Color(153,69,69);
   //budowanie
   public boolean[][] shipsA =new boolean[10][10];
    public boolean[][] shipsB = new boolean[10][10];
    public int countShipsA=0 ;//max 30
    public int countShipsB=0 ;//max 30

    //laczna dlugosc :30

    //gra
    boolean playerATurn=true;
    boolean playerBTurn=false;


    public  boolean isWhite;
    public int combo=0;
    public int countShootShipsOfA=0;
    public int countShootShipsOfB=0;

    public boolean[][] revealedA= new boolean[10][10];
    public boolean[][] revealedB= new boolean[10][10];

    public GraphicalUserInterface(){
        this.setSize(SCREEN_WIDTH+6,SCREEN_HEIGHT+29);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ship Battle");
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);


        Board board = new Board();
        this.setContentPane(board);

        MouseMovement mouseMovement = new MouseMovement();

        this.addMouseMotionListener(mouseMovement);



        MouseClicked mouseClicked= new MouseClicked();

        this.addMouseListener(mouseClicked);

        ResetClass resetClass= new ResetClass();

            this.addKeyListener(resetClass);



        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                revealedA[i][j]=false;
                revealedB[i][j]=false;

            }
        }
    }

    public class Board extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.black);
            if( creationPlayerBTurn==true  && countShipsA>30 && countShipsB==0){
                g.drawString(" Player B turn", 120,20);
            }


            g.setColor(Color.lightGray);
            g.fillRect(0,0,600,800);

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    g.setColor(Color.darkGray);





                    if(gameMode==-1){
                        if(shipsA[i][j]==true && playerATurn==true){
                            g.drawString("Creative Mode: Player A ("+countShipsA+"/31)",150,20);
                            g.setColor(new Color(5,208,239));
                        }


                    }

                    if(gameMode==0){
                        if(countShootShipsOfB==31 && countShootShipsOfA<31){
                            g.setColor(Color.darkGray);
                            g.drawString("Player A wins",300,20);

                            if(shipsA[i][j]==true && revealedA[i][j]==false){
                                g.setColor(unhitedShips);
                            }




                        }

                        if(revealedA[i][j]==true){


                            if(playerATurn==true && playerBTurn==false){
                                g.drawString("          Current Turn: Player B                                 Attack Player A",120,20);
                            }


                            if(playerATurn==false && playerBTurn==true){
                                g.drawString("          Current Turn: Player A                                 Attack Player B",120,20);
                            }


                            if(countShipsB>=31 &&gameMode==0 ){
                                g.drawString("Battle Mode!",20,20);
                            }





                                if(revealedA[i][j]==true){
                                    if(shipsA[i][j]==false){
                                        g.setColor(Color.white);

                                    }
                                    if( shipsA[i][j]==true){
                                        g.setColor(hit);

                                    }
                                }



                    }}






                    g.fillRect(i*30+2*spacing+ 150, j*30+2*spacing+50,30-2*spacing,30-2*spacing);



                }
            }


            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    g.setColor(Color.darkGray);


                    if(gameMode==-1){

                        if(shipsB[i][j]==true){
                           if(playerBTurn==true) {g.drawString("Creative Mode: Player B ("+countShipsB+"/31)",150,20);}
                            g.setColor(new Color(5,208,239));
                        }
                    }


                    if(gameMode==0){

                        if(countShootShipsOfA==31 && countShootShipsOfB<31){
                            g.setColor(Color.darkGray);
                            g.drawString("Player B wins",300,20);

                            if(shipsB[i][j]==true && revealedB[i][j]==false){
                                g.setColor(unhitedShips);
                            }


                        }

                            if(revealedB[i][j]==true){
                                if(shipsB[i][j]==false){
                                    g.setColor(Color.white);

                                }
                                if( shipsB[i][j]==true){
                                    g.setColor(hit);

                                }
                            }


                    }




                    //wyswietlanie tylko wyniku gry






                    g.fillRect(i*30+2*spacing+ 150, j*30+2*spacing+420,30-2*spacing,30-2*spacing);

                    if(countShipsB>=31 &&gameMode==0 ){
                        g.drawString("Battle Mode! ",20,20);
                    }


                }
            }





            g.setColor(Color.darkGray);
            g.drawString("Press ESC to reset Game", 20,770);

            g.setColor(Color.darkGray);
            g.drawString("Start Game by  Building Ships (Player A)",320,770);

            if(gameMode==-1 &&  playerBTurn==true && countShipsB<31){
                g.drawString("Creative Mode: Player B ("+countShipsB+"/31)",150,20);
            }



            if(gameMode==-1 &&  playerATurn==true && countShipsA<31){
                g.drawString("Creative Mode: Player A ("+countShipsA+"/31)",150,20);
            }


                    if( countShipsB==31 && gameMode==0 && playerATurn==true && playerBTurn==false){
                        g.drawString("          Current Turn: Player B                                 Attack Player A",120,20);
                    }
        }
    }
    public class MouseMovement implements MouseMotionListener{


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX=e.getX();
            mouseY=e.getY();


            System.out.println("countShootShipsOf  A: "+ countShootShipsOfA+ ", B: "+countShootShipsOfB);


        }
    }
    public class MouseClicked implements MouseListener  {
        @Override
        public void mouseClicked(MouseEvent e) {


        }

        @Override
        public void mousePressed(MouseEvent e) {



            mouseX=e.getX();
            mouseY=e.getY();


            //dodac ze jesli trafiles to dalej strzelasz

            //teraz dodac system tworzenia statkow (kolor niech ich bedzie cyan)
            //po koncu tworzenia kolor znika itd


            //budowanie statków
            if(gameMode==-1){

                // 1 5 dlugosciowy
                // 2 4dlugosciowe
                //3 3 dlugosciowe
                //2 2dlugosciowe
                //4 dlugosciowe



                if(countShipsA<=30){
                    if (playerATurn == true && playerBTurn == false) {
                        if (inBoxXA() != -1 && inBoxYA() != -1&& shipsA[inBoxXA()][inBoxYA()]==false) {
                            shipsA[inBoxXA()][inBoxYA()] = true;

                            countShipsA++;





                        }
                    }
                }else{
                    playerBTurn=true;
                    playerATurn=false;
                    creationPlayerBTurn=true;
                }

                if(creationPlayerBTurn==true){
                if( countShipsB<=30){
                    if (playerBTurn == true && playerATurn == false ) {
                        if (inBoxXB() != -1 && inBoxYB() != -1 && shipsB[inBoxXB()][inBoxYB()]==false) {
                            shipsB[inBoxXB()][inBoxYB()] = true;
                            countShipsB++;


                        }
                    }


            }}

                if(countShipsB>30 && creationPlayerBTurn==true){
                    gameMode=0;
                    creationPlayerBTurn=false;
                    playerATurn=true;
                    playerBTurn=false;
                }



            }


            //system atakowania Siebie nawzajem
            if(gameMode==0 && countShootShipsOfB<31 && countShootShipsOfA<31) {
                if (playerATurn == true && playerBTurn == false) {
                    if (inBoxXA() != -1 && inBoxYA() != -1) {


                        if(revealedA[inBoxXA()][inBoxYA()]==false && shipsA[inBoxXA()][inBoxYA()]==true){
                            countShootShipsOfA++;
                            revealedA[inBoxXA()][inBoxYA()] = true;
                            if(isWhite==true){
                            isWhite=false;}
                        }
                        if(revealedA[inBoxXA()][inBoxYA()]==false && shipsA[inBoxXA()][inBoxYA()]==false){
                            revealedA[inBoxXA()][inBoxYA()] = true;
                            if(isWhite==false){
                            isWhite=true;}
                        }

                        if(isWhite==true) {
                            playerATurn = false;
                            playerBTurn = true;
                        }
                    }
                }


                if (playerBTurn == true && playerATurn == false) {
                    if (inBoxXB() != -1 && inBoxYB() != -1) {



                        if(revealedB[inBoxXB()][inBoxYB()]==false&& shipsB[inBoxXB()][inBoxYB()]==true){
                            countShootShipsOfB++;
                            revealedB[inBoxXB()][inBoxYB()] = true;
                            if(isWhite==true){
                                isWhite=false;}
                        }
                        if(revealedB[inBoxXB()][inBoxYB()]==false&& shipsB[inBoxXB()][inBoxYB()]==false){
                            revealedB[inBoxXB()][inBoxYB()] = true;
                            if(isWhite==false){
                                isWhite=true;}
                        }


                        if(isWhite==true){
                            playerBTurn = false;
                            playerATurn = true;}

                    }
                }


            }




        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class ResetClass implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()){
                case 27: resetMethod(); break;
                default : break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public int inBoxXA(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(mouseX>=i*30+2*spacing+ 155&&mouseX<i*30+2*spacing+ 155+30-2*spacing && mouseY>=j*30+30+2*spacing+50&& mouseY<j*30+30+2*spacing+50+30-2*spacing){
                    return i;
                }
            }
        }
        return -1;
    }
    public int inBoxYA(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(mouseX>=i*30+2*spacing+ 155&&mouseX<i*30+2*spacing+ 155+30-2*spacing && mouseY>=j*30+30+2*spacing+50&& mouseY<j*30+30+2*spacing+50+30-2*spacing){
                    return j;
                }
            }
        }
        return -1;
    }

    public int inBoxXB(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(mouseX>=i*30+2*spacing+ 156&&mouseX<i*30+2*spacing+ 155+30-2*spacing && mouseY>=j*30+30+2*spacing+420&& mouseY<j*30+30+2*spacing+420+30-2*spacing){
                    return i;
                }
            }
        }
        return -1;
    }
    public int inBoxYB(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(mouseX>=i*30+2*spacing+ 156&&mouseX<i*30+2*spacing+ 155+30-2*spacing && mouseY>=j*30+30+2*spacing+420&& mouseY<j*30+30+2*spacing+420+30-2*spacing){
                    return j;
                }
            }
        }
        return -1;
    }

    public void resetMethod(){
        playerATurn=true;
        playerBTurn=false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                shipsA[i][j]=false;
                shipsB[i][j]=false;
                revealedA[i][j]=false;
                revealedB[i][j]=false;
                countShipsA=0;
                countShipsB=0;
                creationPlayerBTurn=false;

            }
        }


        gameMode=-1;
    }
}
