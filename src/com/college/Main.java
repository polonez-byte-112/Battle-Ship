package com.college;

import java.awt.*;

public class Main  implements Runnable{

    GraphicalUserInterface gui =new GraphicalUserInterface();

    public static void main(String[] args) {
	 new Thread(new Main()).start();
    }

    @Override
    public  void run() {

        while (true){
            //tutaj wszystko powtarza także też dodać jakis boolean ze jeżeli cos jest na false jak jakis reset to cos innego robi
            gui.repaint();




    }


    }
}
