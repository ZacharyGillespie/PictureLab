/**
 * This class is used to create an array of fPixels
 * 
 * @author Zachary Gillespie
 * @version 3/5/2020
 */
public class fPicture extends Picture{
    
    public fPicture(String filename) {
        super(filename);
    }
    
    public fPicture(Picture p) {
        super(p.getFileName());
    }
    
    /**
     * This is almost a carbon copy of getPixels2D() method in Picture 
     * class but returns fPixels instead of regular Pixels 
     * 
     * @returns an array of fPixels
     */
    public fPixel[][] getfPixels2D() {
        int width = getWidth();
        int height = getHeight();
        fPixel[][] fpixelArray = new fPixel[height][width];
   
        // loop through height rows from top to bottom
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                fpixelArray[row][col] = new fPixel(this,col,row);
            }
        }
    
        return fpixelArray;
    }
}
