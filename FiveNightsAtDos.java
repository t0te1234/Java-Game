/* FiveNightsAtDos
 * Desc: Final Project Game: Five Nights at Do's - A FNAF Remix
 * Bobby Yan and Samuel Ke ICS3U
 * Version: 1 - June 1 2022
 */

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.awt.*;
import java.awt.event.*;
// the following imports are needed for pictures
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.security.spec.EncodedKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Timer;
import javax.sound.sampled.*;
import java.io.*;
import java.lang.Thread;

public class FiveNightsAtDos{ 
  static int winCount = 0;
  static int deathCount = 0;
  // Game Window properties
  static JFrame gameWindow;
  static GraphicsPanel canvas;
  static final int WIDTH = 1215;
  static final int HEIGHT = 680;
  
  static JButton menuButton;
  
  static JButton leftDoorButton;
  static JButton rightDoorButton;
  static JButton mapButton;
  static JButton stageButton;
  static JButton diningButton;
  static JButton rightHallwayButton;
  static JButton leftHallwayButton;
  static JButton coveButton;
  static JButton closetButton;
  static JButton restroomButton;
  static JButton kitchenButton;
  
  static boolean leftDoor = false;
  static boolean rightDoor = false;
  
  // picture properties
  static BufferedImage winImage;
  static BufferedImage officePicture;
  static BufferedImage darkOfficePicture;
  static BufferedImage doScareImage;
  static BufferedImage moomiScareImage;
  static BufferedImage grigorovScareImage;
  static BufferedImage mapImage;
  static BufferedImage stageImage;
  static BufferedImage doStageImage;
  static BufferedImage diningImage;
  static BufferedImage doDiningImage;
  static BufferedImage leftDoorImage;
  static BufferedImage rightDoorImage;
  static BufferedImage leftHallwayImage;
  static BufferedImage doLeftHallwayImage;
  static BufferedImage moomiLeftHallwayImage;
  static BufferedImage moomiDoLeftHallwayImage;
  static BufferedImage rightHallwayImage;
  static BufferedImage doRightHallwayImage;
  static BufferedImage doGrigorovRightHallwayImage;
  static BufferedImage grigorovRightHallwayImage;
  static BufferedImage coveImage;
  static BufferedImage stageOneCoveImage;
  static BufferedImage stageTwoCoveImage;
  static BufferedImage stageThreeCoveImage;
  static BufferedImage grigorovKitchenImage;
  static BufferedImage grigorovRestroomImage;
  static BufferedImage doGrigorovDiningImage;
  static BufferedImage grigorovDiningImage;
  static BufferedImage closetImage;
  
  static BufferedImage restroomImage;

  static BufferedImage kitchenImage;
  
  static BufferedImage menuImage;
  static BufferedImage deathImage;
  
  static boolean menu = true;
  static boolean lose = false;
  static boolean loseStatic = false;
  static boolean doLose = false;
  static boolean moomiLose = false;
  static boolean grigorovLose = false;
  static boolean win = false;
  static boolean map = false;
  static boolean stage = false;
  static boolean dining = false;
  static boolean rightHallway = false;
  static boolean leftHallway = false;
  static boolean cove = false;
  static boolean closet = false;
  static boolean restroom = false;
  static boolean kitchen = false;
  static boolean blackout = false;
  static boolean inGame = true;
  
  static int power = 100;
  
  static int xPos = 0;
  static int yPos = 0;
  static int randNum = 0;
  static int doCount = 0;
  static int moomiCount = 0;
  static int grigorovCount = 0;
  
  static int [][] doPlace = {{2,2,0,0,1,0,0,2,2}, 
                             {2,2,2,2,0,2,2,2,2}, 
                             {0,0,0,0,0,0,0,0,0}, 
                             {0,0,2,0,0,0,2,0,0}, 
                             {0,0,2,0,0,0,2,0,0}, 
                             {2,2,2,0,0,0,2,0,2}, 
                             {0,0,2,0,2,0,2,0,0}, 
                             {0,0,0,0,2,0,2,0,0}, 
                             {0,0,2,0,3,0,2,0,0}};                          
  
  static AudioInputStream ambienceAudio;
  static Clip ambienceClip;
  static AudioInputStream jumpScareAudio;
  static Clip jumpScareClip;
  static AudioInputStream cameraFlipAudio;
  static Clip cameraFlipClip;
  static AudioInputStream powerOutAudio;
  static Clip powerOutClip;
  static AudioInputStream winAudio;
  static Clip winClip;
  static AudioInputStream doorSlamAudio;
  static Clip doorSlamClip;
  static AudioInputStream runningAudio;
  static Clip runningClip;
  static AudioInputStream doorBangingAudio;
  static Clip doorBangingClip;
  static AudioInputStream menuAudio;
  static Clip menuClip;
  static AudioInputStream deathAudio;
  static Clip deathClip;
  static AudioInputStream phoneGuyAudio;
  static Clip phoneGuyClip;
  
  public static void main(String[] args){
    gameWindow = new JFrame("Game Window");
    gameWindow.setSize(WIDTH,HEIGHT);
    gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas = new GraphicsPanel();
    canvas.setLayout(null);
    gameWindow.add(canvas); 
    
    menuButton = new JButton("Play");
    menuButton.setBounds(250, 525, 200,50);
    canvas.add(menuButton);
    menuButton.addActionListener(new menuButtonListener());
    
    mapButton = new JButton("Map");
    mapButton.setBounds(500, 615, 200,30);
    canvas.add(mapButton);
    mapButton.addActionListener(new mapButtonListener());
    
    stageButton = new JButton("");
    stageButton.setBounds(1015, 482, 64,16);
    canvas.add(stageButton);
    stageButton.setVisible(false);
    stageButton.setOpaque(false);
    stageButton.setContentAreaFilled(false);
    stageButton.setBorderPainted(false);
    stageButton.addActionListener(new stageButtonListener());
    
    diningButton = new JButton("");
    diningButton.setBounds(983, 501, 134,66);
    canvas.add(diningButton);
    diningButton.setVisible(false);
    diningButton.setOpaque(false);
    diningButton.setContentAreaFilled(false);
    diningButton.setBorderPainted(false);
    diningButton.addActionListener(new diningButtonListener());
    
    rightHallwayButton = new JButton("");
    rightHallwayButton.setBounds(1068, 580, 20,70);
    canvas.add(rightHallwayButton);
    rightHallwayButton.setVisible(false);
    rightHallwayButton.setOpaque(false);
    rightHallwayButton.setContentAreaFilled(false);
    rightHallwayButton.setBorderPainted(false);
    rightHallwayButton.addActionListener(new rightHallwayButtonListener());
    
    leftHallwayButton = new JButton("");
    leftHallwayButton.setBounds(1007, 580, 20,70);
    canvas.add(leftHallwayButton);
    leftHallwayButton.setVisible(false);
    leftHallwayButton.setOpaque(false);
    leftHallwayButton.setContentAreaFilled(false);
    leftHallwayButton.setBorderPainted(false);
    leftHallwayButton.addActionListener(new leftHallwayButtonListener());
    
    coveButton = new JButton("");
    coveButton.setBounds(950, 499, 22,40);
    canvas.add(coveButton);
    coveButton.setVisible(false);
    coveButton.setOpaque(false);
    coveButton.setContentAreaFilled(false);
    coveButton.setBorderPainted(false);
    coveButton.addActionListener(new coveButtonListener());
    
    closetButton = new JButton("");
    closetButton.setBounds(977, 584, 22,40);
    canvas.add(closetButton);
    closetButton.setVisible(false);
    closetButton.setOpaque(false);
    closetButton.setContentAreaFilled(false);
    closetButton.setBorderPainted(false);
    closetButton.addActionListener(new closetButtonListener());
    
    restroomButton = new JButton("");
    restroomButton.setBounds(1123, 509, 16,64);
    canvas.add(restroomButton);
    restroomButton.setVisible(false);
    restroomButton.setOpaque(false);
    restroomButton.setContentAreaFilled(false);
    restroomButton.setBorderPainted(false);
    restroomButton.addActionListener(new restroomButtonListener());
    
    kitchenButton = new JButton("");
    kitchenButton.setBounds(1098, 578, 46,36);
    canvas.add(kitchenButton);
    kitchenButton.setVisible(false);
    kitchenButton.setOpaque(false);
    kitchenButton.setContentAreaFilled(false);
    kitchenButton.setBorderPainted(false);
    kitchenButton.addActionListener(new kitchenButtonListener());
    
    leftDoorButton = new JButton(); 
    leftDoorButton.setBounds(0, 300, 70, 85);
    canvas.add(leftDoorButton);
    leftDoorButton.addActionListener(new leftButtonListener());
    
    rightDoorButton = new JButton(); 
    rightDoorButton.setBounds(1115, 300, 70, 85);
    canvas.add(rightDoorButton);
    rightDoorButton.addActionListener(new rightButtonListener());
    
    try {                
      winImage = ImageIO.read(new File("win.jpg"));
      doScareImage = ImageIO.read(new File("doJumpScare.png"));
      moomiScareImage = ImageIO.read(new File("moomiJumpScare.png"));
      mapImage = ImageIO.read(new File("map.jpg"));
      officePicture = ImageIO.read(new File("gameOffice.jpg"));
      darkOfficePicture = ImageIO.read(new File("darkGameOffice.jpg"));
      stageImage = ImageIO.read(new File("stage.png"));
      doStageImage = ImageIO.read(new File("doStage.png"));
      diningImage = ImageIO.read(new File("dining.png"));
      doDiningImage = ImageIO.read(new File("doDining.png"));
      leftDoorImage = ImageIO.read(new File("leftDoor.png"));
      rightDoorImage = ImageIO.read(new File("rightDoor.png"));
      rightHallwayImage = ImageIO.read(new File("rightHallway.png"));
      leftHallwayImage = ImageIO.read(new File("leftHallway.png"));
      doRightHallwayImage = ImageIO.read(new File("doRightHallway.png"));
      doLeftHallwayImage = ImageIO.read(new File("doLeftHallway.png"));
      moomiLeftHallwayImage = ImageIO.read(new File("moomiLeftHallway.png"));
      moomiDoLeftHallwayImage = ImageIO.read(new File("moomiDoLeftHallway.png"));
      coveImage = ImageIO.read(new File("cove.png"));
      stageOneCoveImage = ImageIO.read(new File("stageOneCove.png"));
      stageTwoCoveImage = ImageIO.read(new File("stageTwoCove.png"));
      stageThreeCoveImage = ImageIO.read(new File("stageThreeCove.png"));
      closetImage = ImageIO.read(new File("closet.png"));
      restroomImage = ImageIO.read(new File("restroom.png"));
      kitchenImage = ImageIO.read(new File("kitchen.png"));
      menuImage = ImageIO.read(new File("menu.png"));
      deathImage = ImageIO.read(new File("death.png"));
    } catch (IOException ex){}

    try{
      grigorovScareImage = ImageIO.read(new File("grigorovJumpScare.png"));
      grigorovKitchenImage = ImageIO.read(new File("grigorovKitchen.png"));
      grigorovRestroomImage = ImageIO.read(new File("grigorovRestroom.png"));
      grigorovDiningImage =  ImageIO.read(new File("grigorovDining.png"));
      doGrigorovDiningImage = ImageIO.read(new File("doGrigorovDining.png"));
      doGrigorovRightHallwayImage = ImageIO.read(new File("doGrigorovRightHallway.png"));
      grigorovRightHallwayImage = ImageIO.read(new File("grigorovRightHallway.png"));
    }catch (IOException ex){}
    
    try {
      File ambience = new File("ambience.wav");
      ambienceAudio = AudioSystem.getAudioInputStream(ambience);
      ambienceClip = AudioSystem.getClip();
      ambienceClip.open(ambienceAudio);
      File doorSlam = new File("doorSlam.wav");
      doorSlamAudio = AudioSystem.getAudioInputStream(doorSlam);
      doorSlamClip = AudioSystem.getClip();
      doorSlamClip.open(doorSlamAudio);
      File powerOut = new File("powerOut.wav");
      powerOutAudio = AudioSystem.getAudioInputStream(powerOut);
      powerOutClip = AudioSystem.getClip();
      powerOutClip.open(powerOutAudio);
      File jumpScare = new File("jumpScare.wav");
      jumpScareAudio = AudioSystem.getAudioInputStream(jumpScare);
      jumpScareClip = AudioSystem.getClip();
      jumpScareClip.open(jumpScareAudio);
      File win = new File("win.wav");
      winAudio = AudioSystem.getAudioInputStream(win);
      winClip = AudioSystem.getClip();
      winClip.open(winAudio);
      File cameraFlip = new File("cameraFlip.wav");
      cameraFlipAudio = AudioSystem.getAudioInputStream(cameraFlip);
      cameraFlipClip = AudioSystem.getClip();
      cameraFlipClip.open(cameraFlipAudio);
      File doorBanging = new File("doorBanging.wav");
      doorBangingAudio = AudioSystem.getAudioInputStream(doorBanging);
      doorBangingClip = AudioSystem.getClip();
      doorBangingClip.open(doorBangingAudio);
      File running = new File("running.wav");
      runningAudio = AudioSystem.getAudioInputStream(running);
      runningClip = AudioSystem.getClip();
      runningClip.open(runningAudio);
      File menu = new File("menu.wav");
      menuAudio = AudioSystem.getAudioInputStream(menu);
      menuClip = AudioSystem.getClip();
      menuClip.open(menuAudio);
      File death = new File("death.wav");
      deathAudio = AudioSystem.getAudioInputStream(death);
      deathClip = AudioSystem.getClip();
      deathClip.open(deathAudio);
      File phoneGuy = new File("phoneGuy.wav");
      phoneGuyAudio = AudioSystem.getAudioInputStream(phoneGuy);
      phoneGuyClip = AudioSystem.getClip();
      phoneGuyClip.open(phoneGuyAudio);
    } catch (Exception ex){} 
    
    gameWindow.setVisible(true);
    runGameLoop();
    
  } // main method end
  
//------------------------------------------------------------------------------   
  public static void runGameLoop(){
    boolean game = true;
    
    while (game) {
      while (menu){
        if(menuClip.isRunning()){
          grigorovLose = false;
          doLose = false;
          moomiLose = false;
          blackout = false;
        }
        else{
          deathClip.stop();
          deathClip.flush();
          deathClip.setFramePosition(0);
          menuClip.start();
          menuClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        gameWindow.repaint();
      }
      long startTime = System.currentTimeMillis();
      long doPreviousTime = System.currentTimeMillis() - startTime;
      long moomiPreviousTime = System.currentTimeMillis() - startTime;
      long grigorovPreviousTime = System.currentTimeMillis() - startTime;
      long powerPreviousTime = System.currentTimeMillis() - startTime;
      while (inGame){
        menuClip.stop();
        menuClip.flush();
        menuClip.setFramePosition(0);
        if(ambienceClip.isRunning()){
        }
        else{
          if((loseStatic != true && win != true) && blackout != true){
            ambienceClip.start();
            //phoneGuyClip.start();
            ambienceClip.loop(Clip.LOOP_CONTINUOUSLY);
            leftDoor = false;
            rightDoor = false;
          }
        }
        final int DO_INTERVAL_DELAY = 5000; //TBD
        final int MOOMI_INTERVAL_DELAY = 20000; //TBD
        final int GRIGOROV_INTERAL_DELAY = 12000;
        final int POWER_INTERVAL_DELAY = 10800; // Final: 10800
        final int WIN_INTERVAL_DELAY = 540000; // Final: 540000
        gameWindow.repaint();
        
        if ((System.currentTimeMillis() - startTime) - doPreviousTime >= DO_INTERVAL_DELAY == true){
          doPreviousTime = System.currentTimeMillis() - startTime;
          doMovement();
        }
        
        if ((System.currentTimeMillis() - startTime) - moomiPreviousTime >= MOOMI_INTERVAL_DELAY == true){
          moomiPreviousTime = System.currentTimeMillis() - startTime;
          moomiMovement();
        }

        if ((System.currentTimeMillis() - startTime) - grigorovPreviousTime >= GRIGOROV_INTERAL_DELAY == true){
          grigorovPreviousTime = System.currentTimeMillis() - startTime;
          grigorovMovement();
        }
        
        if ((System.currentTimeMillis() - startTime) - powerPreviousTime >= POWER_INTERVAL_DELAY == true){
          powerPreviousTime = System.currentTimeMillis() - startTime;
          powerDown();
        }
        
        if (((System.currentTimeMillis() - startTime) >= WIN_INTERVAL_DELAY == true && lose == false)){
          win = true;
        }
        
        if (power <= 0){
          power = 0;
          blackout = true;
        }
      }
    }
    
  } // runGameLoop method end
  
//------------------------------------------------------------------------------  
  static class GraphicsPanel extends JPanel{
    public GraphicsPanel(){
      setFocusable(true);
      requestFocusInWindow();
    }
    public void paintComponent(Graphics g){ 
      super.paintComponent(g); //required
      g.setFont(new Font("ComicSans", Font.PLAIN, 30));
      
      // draw the picture ("this" refers to the graphics panel)
      if (blackout == true){
        if(rightDoor==true || leftDoor==true){
          doorSlamClip.stop();
          doorSlamClip.flush();
          doorSlamClip.setFramePosition(0);
          doorSlamClip.start();
        }
        map = false;
        stage = false;
        dining = false;
        leftDoor = false;
        rightDoor = false;
        rightHallway = false;
        leftHallway = false;
        cove = false;
        closet = false;
        restroom = false;
        kitchen = false;
        g.drawImage(darkOfficePicture, 0, 0, this);
        diningButton.setVisible(false);
        stageButton.setVisible(false);
        rightHallwayButton.setVisible(false);
        leftHallwayButton.setVisible(false);
        coveButton.setVisible(false);
        closetButton.setVisible(false);
        restroomButton.setVisible(false);
        kitchenButton.setVisible(false);
        leftDoorButton.setVisible(false);
        rightDoorButton.setVisible(false);
        mapButton.setVisible(false);
        phoneGuyClip.stop();
        phoneGuyClip.flush();
        phoneGuyClip.setFramePosition(0);
        ambienceClip.stop();
        ambienceClip.flush();
        ambienceClip.setFramePosition(0);
        if(!menu){
          powerOutClip.start();
        }
      }
      else{
        g.drawImage(officePicture,0,0, this);
      }
      
      if (leftDoor == true){
        g.drawImage(leftDoorImage,-250,140, this);
      }
      
      if (rightDoor == true){
        g.drawImage(rightDoorImage, 700, 140, this);
      }
      
      if (lose == true){
        map = false;
        stage = false;
        dining = false;
        rightHallway = false;
        leftHallway = false;
        cove = false;
        closet = false;
        restroom = false;
        kitchen = false;
        diningButton.setVisible(false);
        stageButton.setVisible(false);
        rightHallwayButton.setVisible(false);
        leftHallwayButton.setVisible(false);
        coveButton.setVisible(false);
        closetButton.setVisible(false);
        restroomButton.setVisible(false);
        kitchenButton.setVisible(false);
        leftDoorButton.setVisible(true);
        rightDoorButton.setVisible(true);
        phoneGuyClip.stop();
        phoneGuyClip.flush();
        phoneGuyClip.setFramePosition(0);
        ambienceClip.stop();
        ambienceClip.flush();
        ambienceClip.setFramePosition(0);
        powerOutClip.stop();
        powerOutClip.flush();
        powerOutClip.setFramePosition(0);
        if(deathCount == 0 && !menu){
          jumpScareClip.start();
        }
        if (doLose == true){
          g.drawImage(doScareImage, 100, 100, this);
        }
        if (moomiLose == true){
          g.drawImage(moomiScareImage, 100, 100, this);
        }
        if (grigorovLose == true){
          g.drawImage(grigorovScareImage, 100, 100, this);
        }
        win = false;
      }
      
      if (stage == true){
        if (doLocation() == "stage"){
          g.drawImage(doStageImage, 0, 0, this);
        }
        else{
          g.drawImage(stageImage, 0,0,this);
        }
      }
      
      if (dining == true){
        if (doLocation() == "dining" && grigorovCount == 2){
          g.drawImage(doGrigorovDiningImage, 0, 0, this);
        }
        else if (doLocation() == "dining"){
          g.drawImage(doDiningImage, 0, 0, this);
        }
        else if (grigorovCount == 2){
          g.drawImage(grigorovDiningImage, 0, 0, this);
        } 
        else{
          g.drawImage(diningImage, 0,0,this);
        }
      }
      
      if (rightHallway == true){
        if (doLocation() == "rightHallway" && grigorovCount >= 3){
          g.drawImage(doGrigorovRightHallwayImage, 0, 0, this);
        }
        else if (doLocation() == "rightHallway"){
          g.drawImage(doRightHallwayImage, 0,0,this);
        }
        else if (grigorovCount >= 3){
          g.drawImage(grigorovRightHallwayImage, 0,0, this);
        }
        else{
          g.drawImage(rightHallwayImage, 0,0,this);
        }
      }
      
      if (leftHallway == true){
        if (doLocation() == "leftHallway" && moomiCount >= 3){
          g.drawImage(moomiDoLeftHallwayImage, 0,0,this);
        }
        else if (doLocation() == "leftHallway"){
          g.drawImage(doLeftHallwayImage, 0,0,this);
        }
        else if (moomiCount >= 3){
          g.drawImage(moomiLeftHallwayImage, 0, 0, this);
        }
        else{
          g.drawImage(leftHallwayImage, 0,0,this);
        }
      }
      
      if (cove == true){
        if (moomiCount == 0){
          g.drawImage(stageOneCoveImage, 0, 0, this);
        }
        else if (moomiCount == 1){
          g.drawImage(stageTwoCoveImage, 0,0,this);
        }
        else if (moomiCount == 2){
          g.drawImage(stageThreeCoveImage, 0,0,this);
        }
        else{
          g.drawImage(coveImage, 0, 0, this);
        }
      }
      
      if (closet == true){
        g.drawImage(closetImage, 0,0,this);
      }
      
      if (restroom == true){
        if (grigorovCount == 1){
          g.drawImage(grigorovRestroomImage, 0, 0, this);
        } 
        else{
          g.drawImage(restroomImage, 0,0,this);
        }
      }
      
      if (kitchen == true){
        if (grigorovCount == 0){
          g.drawImage(grigorovKitchenImage, 0, 0, this);
        }
        else{
          g.drawImage(kitchenImage, 0,0,this);
        }
      }
      
      if (map == true){
        g.drawImage(mapImage,897,470,this);
      }
      
      g.setColor(Color.WHITE);
      String powerString = String.valueOf(power);
      g.drawString("Power: " + powerString, 0,630);
      
      if (win == true){
        phoneGuyClip.stop();
        phoneGuyClip.flush();
        phoneGuyClip.setFramePosition(0);
        ambienceClip.stop();
        ambienceClip.flush();
        ambienceClip.setFramePosition(0);
        powerOutClip.stop();
        powerOutClip.flush();
        powerOutClip.setFramePosition(0);
        if(winCount == 1 && !menu){
          winClip.start();
        }
        g.drawImage(winImage, 0,0, this);
        moomiCount = 0;
        doCount = 0;
        grigorovCount = 0;
        map = false;
        stage = false;
        dining = false;
        rightHallway = false;
        leftHallway = false;
        cove = false;
        closet = false;
        restroom = false;
        kitchen = false;
        diningButton.setVisible(false);
        stageButton.setVisible(false);
        rightHallwayButton.setVisible(false);
        leftHallwayButton.setVisible(false);
        coveButton.setVisible(false);
        closetButton.setVisible(false);
        restroomButton.setVisible(false);
        kitchenButton.setVisible(false);
        leftDoorButton.setVisible(false);
        rightDoorButton.setVisible(false);
        mapButton.setVisible(false);
        try  {Thread.sleep(500);} catch(Exception e){}
        winCount++;
      }
      
      if (menu == true){
        win = false;
        lose = false;
        loseStatic = false;
        moomiCount = 0;
        doCount = 0;
        grigorovCount = 0;
        menuButton.setVisible (true);
        mapButton.setVisible(false);
        leftDoorButton.setVisible(false);
        rightDoorButton.setVisible(false);
        g.drawImage(menuImage,0,0, this);
        inGame = true;
      }
      
      if (loseStatic == true){
        ambienceClip.stop();
        ambienceClip.flush();
        ambienceClip.setFramePosition(0);
        mapButton.setVisible(false);
        leftDoorButton.setVisible(false);
        rightDoorButton.setVisible(false);
        try  {Thread.sleep(2000);} catch(Exception e){}
        g.drawImage(deathImage,0,0, this);
        deathCount++;
        
        if(deathCount == 1){
          jumpScareClip.stop();
          jumpScareClip.flush();
          jumpScareClip.setFramePosition(0);
          deathClip.start();
        }
        
        if (deathCount == 2){
          deathCount = 0;
          power = 100;
          menu = true;
          loseStatic = false;
          inGame = false;
          for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
              if (doPlace[row][col] == 1){
                xPos = row;
                yPos = col;
              }
            }
          }
          doPlace[xPos][yPos] = 0;
          doPlace[0][4] = 1;
        }
      }
      
      if (winCount == 20){
        winClip.stop();
        winClip.flush();
        winClip.setFramePosition(0);
        win = false;
        winCount = 0;
        power = 100;
        menu = true;
        inGame = false;
      }
      
      if (lose == true){
        loseStatic = true;
      }
    }
  }
  
  static class menuButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      menu = false;
      mapButton.setVisible(true);
      leftDoorButton.setVisible(true);
      rightDoorButton.setVisible(true);
      menuButton.setVisible(false);
    }
  }
  
  static class rightButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      doorSlamClip.stop();
      doorSlamClip.flush();
      doorSlamClip.setFramePosition(0);
      doorSlamClip.start();
      if (rightDoor == true){
        rightDoor = false;
      }
      else{
        rightDoor = true;
      }
    }
  }
  
  static class leftButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      doorSlamClip.stop();
      doorSlamClip.flush();
      doorSlamClip.setFramePosition(0);
      doorSlamClip.start();
      if (leftDoor == true){
        leftDoor = false;
      }
      else{
        leftDoor = true;
      }
    }
  }
  
  static class mapButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if (map == true){
        map = false;
        stage = false;
        dining = false;
        rightHallway = false;
        leftHallway = false;
        cove = false;
        closet = false;
        restroom = false;
        kitchen = false;
        diningButton.setVisible(false);
        stageButton.setVisible(false);
        rightHallwayButton.setVisible(false);
        leftHallwayButton.setVisible(false);
        coveButton.setVisible(false);
        closetButton.setVisible(false);
        restroomButton.setVisible(false);
        kitchenButton.setVisible(false);
        leftDoorButton.setVisible(true);
        rightDoorButton.setVisible(true);
      }
      
      else{
        map = true;
        stageButton.setVisible(true);
        diningButton.setVisible(true);
        rightHallwayButton.setVisible(true);
        leftHallwayButton.setVisible(true);
        coveButton.setVisible(true);
        closetButton.setVisible(true);
        restroomButton.setVisible(true);
        kitchenButton.setVisible(true);
        leftDoorButton.setVisible(false);
        rightDoorButton.setVisible(false);
      }
    }
  }
  
  static class stageButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (stage == false){
        cameraFlip("stage");
      }
      else{
        stage = false;
      }
      
    }
  }
  
  static class diningButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (dining == false){
        cameraFlip("dining");
      }
      else{
        dining = false;
      }
    }
  }
  
  static class rightHallwayButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (rightHallway == false){
        cameraFlip("rightHallway");
      }
      else{
        rightHallway = false;
      }
    }
  }
  
  static class leftHallwayButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (leftHallway == false){
        cameraFlip("leftHallway");
      }
      else{
        leftHallway = false;
      }
    }
  }
  
  static class coveButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (cove == false){
        cameraFlip("cove");
      }
      else{
        cove = false;
      }
    }
  }
  
  static class closetButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (closet == false){
        cameraFlip("closet");
      }
      else{
        closet = false;
      }
    }
  }
  
  static class restroomButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (restroom == false){
        cameraFlip("restroom");
      }
      else{
        restroom = false;
      }
    }
  }
  
  static class kitchenButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      cameraFlipClip.stop();
      cameraFlipClip.flush();
      cameraFlipClip.setFramePosition(0);
      cameraFlipClip.start();
      if (kitchen == false){
        cameraFlip("kitchen");
      }
      else{
        kitchen = false;
      }
    }
  }
  
  public static void doMovement(){
    for (int row = 0; row < 9; row++){
      for (int col = 0; col < 9; col++){
        if (doPlace[row][col] == 1){
          xPos = row;
          yPos = col;
        }
      }
    }
    
    if (doCount <= 4){
      doPlace[xPos][yPos] = 0;
      doPlace[xPos+1][yPos] = 1;
      doCount = doCount + 1;
    }
    else if (doCount == 5){
      randNum = (int) (Math.random()*2)+1;
      
      if(randNum == 1){
        doPlace[xPos][yPos] = 0;
        doPlace[xPos][yPos-1] = 1;
        doCount = doCount + 1;
      }
      else if(randNum == 2){
        doPlace[xPos][yPos] = 0;
        doPlace[xPos][yPos+1] = 1;
        doCount = doCount + 1;
      }
    }
    else if (doCount <= 8){
      doPlace[xPos][yPos] = 0;
      doPlace[xPos+1][yPos] = 1;
      doCount = doCount + 1;
    }
    else if (doCount == 9){
      if (randNum == 1){
        if (leftDoor == true){
          doorBangingClip.stop();
          doorBangingClip.flush();
          doorBangingClip.setFramePosition(0);
          doorBangingClip.start();
          doCount = 0;
          doPlace[xPos][yPos] = 0;
          doPlace[0][4] = 1;
        }
        else{
          lose = true;
          doLose = true;
          return;
        }
      }
      else if (randNum == 2){
        if (rightDoor == true){
          doorBangingClip.stop();
          doorBangingClip.flush();
          doorBangingClip.setFramePosition(0);
          doorBangingClip.start();
          doCount = 0;
          doPlace[xPos][yPos] = 0;
          doPlace[0][4] = 1;
        }
        else{
          lose = true;
          doLose = true;
          return;
        }
      }
    }
    xPos = 0;
    yPos = 0;
    return;
  }
  
  public static void grigorovMovement(){
    if (grigorovCount <= 3){
      grigorovCount++;
    }
    else if (grigorovCount == 4){
      if (rightDoor == false){
        lose = true;
        grigorovLose = true;
      }
      else{
        doorBangingClip.stop();
        doorBangingClip.flush();
        doorBangingClip.setFramePosition(0);
        doorBangingClip.start();
        grigorovCount = 0;
      }
    }
    return;
  }

  public static void moomiMovement(){
    if (moomiCount <= 3){
      moomiCount = moomiCount + 1;
      if(moomiCount==3){
        runningClip.stop();
        runningClip.flush();
        runningClip.setFramePosition(0);
        runningClip.start();
      }
    }
    
    else if (moomiCount == 4){
      if (leftDoor == false){
        lose = true;
        moomiLose = true;
      }
      else{
        doorBangingClip.stop();
        doorBangingClip.flush();
        doorBangingClip.setFramePosition(0);
        doorBangingClip.start();
        moomiCount = 0;
      }
    }
    return;
  }
  
  public static String doLocation(){
    if (doPlace[0][4] == 1){
      return "stage";
    }
    
    else if (doPlace[2][4] == 1 || doPlace[3][4] == 1 || doPlace[4][4] == 1){
      return "dining";
    }
    
    else if (doPlace[5][3] == 1 || doPlace[6][3] == 1 || doPlace[7][3] == 1 || doPlace[8][3] == 1){
      return "leftHallway";
    }
    
    else if (doPlace[5][5] == 1 || doPlace[6][5] == 1 || doPlace[7][5] == 1 || doPlace[8][5] == 1){
      return "rightHallway";
    }
    
    else if (doPlace[0][2] == 1 || doPlace[0][3] == 1 || doPlace[0][4] == 1 || doPlace[1][2] == 1 || doPlace[1][3] == 1
               || doPlace[0][4] == 1 || doPlace[2][2] == 1){
      return "cove";
    }
    
    else if (doPlace[0][6] == 1 || doPlace[0][7] == 1 || doPlace[0][8] == 1 || doPlace[1][6] == 1 || doPlace[1][7] == 1 
               || doPlace[1][8] == 1 || doPlace[2][7] == 1){
      return "closet";
    }
    
    else if (doPlace[6][2] == 1 || doPlace[7][2] == 1 || doPlace[7][3] == 1 || doPlace[7][4] == 1 || doPlace[8][2] == 1 
               || doPlace[8][3] == 1 || doPlace[8][4] == 1){
      return "restroom";
    }
    
    else if (doPlace[7][5] == 1 || doPlace[7][6] == 1 || doPlace[7][7] == 1 || doPlace[7][8] == 1 || doPlace[8][6] == 1 
               || doPlace[8][7] == 1 || doPlace[8][8] == 1){
      return "kitchen";
    }
    
    else{
      return " ";
    }
  }
  
  public static void cameraFlip(String camera){
    dining = false;
    stage = false;
    rightHallway = false;
    leftHallway = false;
    cove = false;
    closet = false;
    restroom = false;
    kitchen = false;
    
    if (camera == "dining"){
      dining = true;
    }
    else if (camera == "stage"){
      stage = true;
    }
    else if (camera == "rightHallway"){
      rightHallway = true;
    }
    else if (camera == "leftHallway"){
      leftHallway = true;
    }
    else if (camera == "cove"){
      cove = true;
    }
    else if (camera == "closet"){
      closet = true;
    }
    else if (camera == "restroom"){
      restroom = true;
    }
    else if (camera == "kitchen"){
      kitchen = true;
    }
  }
  
  public static void powerDown(){
    power = power - 1;
    if (map == true){
      power = power - 1;
      
    }
    if (leftDoor == true){
      power = power - 1;
    }
    
    if (rightDoor == true){
      power = power - 1;
    }
    return;
  }
}