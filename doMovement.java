  
  import javax.swing.*;
  import java.awt.*;
  import java.awt.event.*;
  // the following imports are needed for pictures
  import java.io.File;
  import java.io.IOException;
  import javax.imageio.ImageIO;
  import java.awt.image.BufferedImage;
  import java.util.*;
  import java.util.Timer;

  public class doMovement extends Thread {
    private volatile int [][] place = {{0,0,1,0,0}, 
                                       {0,0,0,0,0}, 
                                       {0,0,0,0,0}, 
                                       {0,0,0,0,0}, 
                                       {0,0,3,0,0}};
    public void run(){
        
        int xPos = 0;
        int yPos = 0;

        while (true){
            for (int row = 0; row < 5; row++){
                for (int col = 0; col < 5; col++){
                    if (place[row][col] == 1){
                        xPos = row;
                        yPos = col;
                    }
                }
            }
            
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
    
    
            if (xPos == 4 && yPos == 2){
                
            }
            else{
                if (place[xPos+1][yPos] == 3){
                    try
                    {
                        Thread.sleep(10000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    place[xPos][yPos] = 0;
                    place[xPos+1][yPos] = 1;
                }
                else{
                    place[xPos][yPos] = 0;
                    place[xPos+1][yPos] = 1;
                }
            }
            System.out.println(Arrays.deepToString(place));
        
        }
    }

    public int getValue() {
        return value;
    }
  }