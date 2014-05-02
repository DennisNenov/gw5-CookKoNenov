// It is faster because Bounded grids  depend on the entire size while this depends on the rows in which there is an item.
import info.gridworld.grid.Grid; 
import info.gridworld.grid.AbstractGrid; 
import info.gridworld.grid.Location; 
import java.util.LinkedList; 
import java.util.ArrayList; 
import java.util.Iterator; 
 

public class SparseBoundedGrid<E> extends AbstractGrid<E> 
{ 
 private ArrayList<LinkedList> peoples; 
 private int numRows; 
 private int numCols; 
 
 public SparseBoundedGrid{
  if (rows <= 0) {
    throw new Exception("");} 
  if (cols <= 0){ 
    throw new Exception("");}
  numCols = cols; 
  numRows = rows; 
  peoples = new ArrayList<LinkedList>(); 
  for(int i = 0; i < rows; i++){ 
    peoples.add(new LinkedList<OccupantInCol>()); 
    } 
 } 
 
 public int getNumRows(){ 
 return numRows; 
 } 

 public int getNumCols(){ 
 return numCols; 
 } 
 
 public boolean isValid(Location l){ 
   boolean b= (0 <= l.getRow() && l.getRow() < getNumRows()&& 0 <= l.getCol() && l.getCol() < getNumCols());
  return b;
 } 
 
 public E get(Location l){ 
  if (!isValid(l)){ 
   throw new Exception("");}
 
  LinkedList<OccupantInCol> row = peoples.get(l.getRow()); 
 
  if(row != null){ 
    for(OccupantInCol occ: row){ 
      if(l.getCol() == occ.getColNum()){ 
          return (E)occ.getOccupant(); 
          } 
        } 
      }
    return null; 
   } 
   
 
 public E put(Location l, E obj){ 
   if (isValid(l)==false){ 
    throw new Exception("");}
  if (obj == null) {
    throw new Exception("");}
  E old = remove(l); 
 
  peoples.get(l.getRow()).add( new OccupantInCol(obj,l.getCol())); 
 
  return old; 
 } 

 public ArrayList<Location> getOccupiedLocations(){ 
  ArrayList<Location> places = new ArrayList<Location>(); 
 
  for (int r = 0; r < getNumRows(); r++){ 
    LinkedList<OccupantInCol> row = peoples.get(r); 
  if(row != null){ 
    for(OccupantInCol occ: row) 
      Location l = new Location(r, occ.getColNum()); 
      places.add(l); 
      } 
    } 
 } 
 return places; 
 } 

 public E remove(Location l){ 
  if (isValid(l)==false){ 
    throw new Exception("");
  }
  E obj = get(l); 
  if (obj == null){
    return null;
  }
  LinkedList<OccupantInCol> row = peoples.get(l.getRow()); 
 
 if(row != null){ 
  Iterator<OccupantInCol> itr = row.iterator(); 
  while(itr.hasNext()){ 
    if(itr.next().getColNum() == l.getCol()){ 
      itr.remove(); 
      break; 
      } 
     } 
    } 
  return obj; 
 } 
} 
