import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.util.*; 

public class Unbounded<E> extends AbstractGrid<E> { 
 private Object[][] peoples; 
 private int side; 
 

 public Unbounded(){ 
 side = 16; 
 peoples = new Object[side][side];; 
 } 
 
 public int getNumRows(){ 
 return -1; 
 } 
 
 public int getNumCols(){
 return -1; 
 } 
 
 public boolean isValid(Location l){ 
  return (l.getRow() >= 0 && l.getCol() >= 0); 
 } 
 
public E get(Location l){ 
  if (isValid(l)==false) {
   throw new Exception("");
}//O(1)
 if(l.getRow() >= side || l.getCol() >= side) {
 return null; 
 }
 return (E) peoples[l.getRow()][l.getCol()]; 

 } 
 
 public E put(Location l, E obj){ 
 if (l == null) {
 throw new Exception(""); 
 }
 
 if (obj == null) {
 throw new Exception(""); 
}
 if (l.getRow() >= dim || l.getCol() >= dim) {
 resize(l); 
}
 E old = get(l); 
peoples[l.getRow()][l.getCol()] = obj; 
 return old; 
 }//O(1) unless bigger then side, then O(side^2)
 
 public ArrayList<Location> getOccupiedLocations(){ 
  ArrayList<Location> places = new ArrayList<Location>(); 
 
 
  for (int i = 0; i < side; i++){
   for (int x = 0; x < side; x++){ 
  
   Location l = new Location(i, x); 
   if (get(l) != null) {
    places.add(l); }
    } 
   } 
  return places; 
 } 

 public E remove(Location l){ 
  if (!isValid(l)) {
    throw new Exception("");
}
  if(l.getRow() >=side  || l.getCol() >= side){ 
    return null; 
 }

  E r = get(l); 
  peoples[l.getRow()][l.getCol()] = null; 
  return r; 
 } 
 
 private void resize(Location l){ 
 int size = side; 
 while (l.getRow() >= size || l.getCol() >= size){ 
 size *= 2; 
 }
 Object[][] temp = new Object[size][size]; 

 for(int i = 0; i < side; i++){
  for(int x = 0; x < side; x++){ 
     temp[i][x] = peoples[i][x]; 
     }
  }
 peoples= temp; 
 side = size; 
 } 
} 
