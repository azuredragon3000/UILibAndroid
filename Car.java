/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package main;

/**
 * @author PCH1HC
 *
 */
public class Car {

  double Posx;
  double Posy;
  double vt;
  int size;
  double dx;
  double dy;
  
  /**
   * @param x
   * @param y
   * @param size
   * @param dx
   * @param dy
   */
  @SuppressWarnings("javadoc")
  public Car(int x, int y,int size,double dx,double dy) {
    Posx = x;
    Posy = y;
    this.size = size;
    this.dx =dx;
    this.dy = dy;
  }

  
  /**
   * @return the posx
   */
  public double getPosx() {
    return Posx;
  }

  /**
   * @param posx the posx to set
   */
  public void setPosx(int posx) {
    Posx = posx;
  }
  
  /**
   * @return the posy
   */
  public double getPosy() {
    return Posy;
  }

  /**
   * @param posy the posy to set
   */
  public void setPosy(int posy) {
    Posy = posy;
  }
  
  
  /**
   * 
   */
  public void thinking() {
    
    // think to give out the decision
    
    // look around ( use 1 con mat de quet) if detect somethink as input
    // con mat nay la 1 1/4 hinh tron 
    
    
    // analyse input is food or not
    
    // if its food eat and learn , if not skip and learn
    
    // face it again , give out decision , no need to analyse again
    
  }

  
  /**
   * @param time 
   * @param width 
   * @param height 
   * 
   */
  @SuppressWarnings("javadoc")
  public void move(long time, int width,int height) {
     if (Posx + dx < 0 || Posx + dx + size > width) {
       dx = -dx;  
     }

     if (Posy + dy < 0 || Posy + dy + size > height) {
       dy = -dy; 
     }
   
     // decide to move
     Posx += dx;
     Posy += dy;  
  }


  /**
   * @return
   */
  @SuppressWarnings("javadoc")
  public int getSize() {
    return size;
  }
  
}

