/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter
{
  private static final double DARKRATE=.05;
    private int courage;
    public BlusterCritter(int cour){
      super();
      courage=cour;
    }
    
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int ctr=0;
      for (Actor s: actors){
        if(s instanceof Critter) {
        ctr++;
        }
      }
      if (ctr>courage)
        getBrighter();
      else
        getDarker();
      
    }
    
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc=getLocation();
        for ( int r=loc.getRow()-2; r<=loc.getRow()+2; r++){
          for ( int c = loc.getCol() - 2; c<=loc.getCol()+2;c++){
            Location tempLoc= new Location(r, c);
            if(getGrid().isValid(tempLoc)){
              
              Actor a = getGrid().get(tempLoc);
              if (a != null && a != this)
                actors.add(a);
            }
          }
        }

        return actors;
    }
    private void getDarker(){
      //taken from Flower.java
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKRATE));
        int green = (int) (c.getGreen() * (1 - DARKRATE));
        int blue = (int) (c.getBlue() * (1 - DARKRATE));
        setColor(new Color(red, green, blue));
    }
     private void getBrighter()
     {
       Color c = getColor();
       int red = c.getRed();
       int green = c.getGreen();
       int blue = c.getBlue();
       if (red < 255) {
         red++;
       }
       if (green < 255) {
         green++;
       }
       if (blue < 255) {
         blue++;
       }
       setColor(new Color(red, green, blue));
     }
}
