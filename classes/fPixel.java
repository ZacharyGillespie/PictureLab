/**
 * This is an augmented Pixel class that adds a flag for whether or not this pixel is an edge
 * 
 * @author Zachary Gillespie
 * @version 2/5/2020
 */
public class fPixel extends Pixel {
    private boolean edge;
    
    public fPixel(DigitalPicture picture, int x, int y) {
        super(picture, x, y);
    }
    
    /**
     * This sets the flag for being an edge to true
     */
    public void setEdge(boolean b) {
        edge = b;
    }
    
    /**
     * This gets the flag for being an edge
     */
    public boolean getEdge() {
        return edge;
    }
    
}
