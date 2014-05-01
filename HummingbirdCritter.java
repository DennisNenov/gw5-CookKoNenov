/*****************************************************************

    SHERRY KO, DENNIS NENOV, MATTHEW COOK --- TEAM COOKKONENOV
    APCS pd 9
    HW35 -- GridWorld, Part 5
    2014-04-30 *


    class HummingbirdCritter *

 * BEACAUSE:

 We chose HummingbirdCritter because it looked pretty entertaining, as it 
could only move two spaces at a time. Also, changing the colors of the flowers
seemed to be a plausible spec to code. Also hummingbirds are cool.

 * SPECIFICATIONS:

HummingbirdCritter Specs:
A HummingbirdCritter is a critter that changes the color 
(making them brighter) of Flower critters found in the locations 
directly to its left/right or in front/back of it (4 locations of 
the 8 surrounding). It can only move forward and moves 2 spots at 
a time. If a HummingBird Critter cannot move, it turns 45 degrees, 
randomly to left/right.

 * TEST CASES:

HummingbirdCritter Test Cases:
If a HummingbirdCritter finds a flower in the spot in front of it
 and one in the spot to its left, it should set the colors of both 
to be brighter, and then move 2 spots in its current direction.
If a HummingbirdCritter is facing a rock or the edge of the grid 
(or has one within the 2 spaces in front of it), it should turn45
 degrees right/left and move 2 spaces in that direction instead.
 If the chosen direction is also blocked, it should keep turning in 
that direction.

 * ERRATA:

We had the color of the flower brighten by 100 if it was less than 155, which 
means that if the color was greater than 155, no change in color would be seen.
This was done because at lower rates of color change, the natural darkening of
the flower overpowered the getBrighter function.
 *****************************************************************/ 
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import java.awt.Color;
import java.util.ArrayList;

public class HummingbirdCritter   extends Critter
{
    public HummingbirdCritter()
    {
        setColor(Color.YELLOW);
    }
    
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs))
	    {
		Actor a = getGrid().get(loc);
		if (a != null)
		    actors.add(a);
	    }
	
        return actors;
    }
    
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid g= getGrid();
        twoAway(locs, getDirection() + Location.AHEAD);
        return locs;
        
    }
    private void twoAway(ArrayList<Location> locs, int dir){
	Grid g=getGrid();
	Location loc= getLocation();
	Location temp= loc.getAdjacentLocation(dir);
	if(g.isValid(temp) && g.get(temp)==null){
	    Location loc2=temp.getAdjacentLocation(dir);
	    if(g.isValid(loc2) && g.get(loc2)==null)
		locs.add(loc2);
	}
    }
    
    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
	    {
		double r = Math.random();
		int angle;
		if (r < 0.5)
		    angle = Location.HALF_LEFT;
		else
		    angle = Location.HALF_RIGHT;
		setDirection(getDirection() + angle);
	    }
        else
            super.makeMove(loc);
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
	
        for (int d : directions)
	    {
		Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
	    }
        return locs;
    }    
    
    
    private void getBrighter(Flower flo)
    {
	Color c = flo.getColor();
	int red = c.getRed();
	int green = c.getGreen();
	int blue = c.getBlue();
	if (red < 155) {
	    red=red+100;
	}
	if (green < 155) {
	    green=green+100;
       }
	if (blue < 155) {
	    blue=blue+100;
	}
	flo.setColor(new Color(red, green, blue));
    }
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
	    return;
	for(Actor other: actors){
         if (other instanceof Flower)
	     getBrighter((Flower)(other));
	}
    }
    
    
}
