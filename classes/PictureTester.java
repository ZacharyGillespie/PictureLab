import java.awt.Color;
public class PictureTester {
    /**
     * Detects if an fPixel is an edge and if so it sets the edge flag to true
     */
    public static fPixel[][] edgeDetection(fPicture p, int edgeDist)   {    
        fPixel currentfPixel = null;  
        
        fPixel nextfPixel = null;
        fPixel previousfPixel = null;

        Color nextColor = null;  
        Color currentColor = null;
        Color previousColor = null;
        
        fPixel[][] fPixels = p.getfPixels2D();
        
        // this loop checks for edges on both the left boundary edges
        for (int row = 0; row < fPixels.length; row++){       
            for (int col = 1; col < fPixels[0].length-1; col++){         
                currentfPixel = fPixels[row][col];  // the pixel you are on currently  
                
                nextfPixel = fPixels[row][col+1];   // the pixel to the right of the current pixel   
                nextColor = nextfPixel.getColor();  // the color of the nextfPixel

                // only make it an edge if the color distance is greater than the threshold on the left
                if (currentfPixel.colorDistance(nextColor) > edgeDist) { 
                    // only make it an edge if the pixel to the left of it is not an edge
                    if( (col != 0) && (fPixels[row][col-1].getEdge() != true) ) {
                            currentfPixel.setEdge(true);   
                            //currentfPixel.setGreen(255);
                    }
                } 
            }   
        }
        
        // these loops check for right boundary edges
        for (int row = fPixels.length  - 1; row > 0; row--){       
            for (int col = fPixels[0].length - 1; col > 1; col--){ 
                currentfPixel = fPixels[row][col];  // the pixel you are on currently   
                
                previousfPixel = fPixels[row][col-1];   // the pixel to the left of the current pixel   
                previousColor = previousfPixel.getColor();  // the color of the previousfPixel
                
                // only make it an edge if the color distance is greater than the threshold
                if (currentfPixel.colorDistance(previousColor) > edgeDist){   
                    // only make it an edge if the pixel to the right of it is not an edge
                    if( (col != fPixels[0].length - 1) && (fPixels[row][col+1].getEdge() != true) ) {
                            currentfPixel.setEdge(true);   
                            //currentfPixel.setGreen(255);
                    }
                }    
            }   
        }
        
        return fPixels;
    }
    
    /**
     * blurs fPixels that are on edges to reduce the sharpness of an image
     * 
     * @param p the fPicture that you want to perform the antialias on
     */
    public static void antialias(fPicture p, int pixelsToBlur, int edgeDist) {
        fPixel pixel = null;
        int r, g, b, totalPixels, count = 0;
        
        fPicture copy = new fPicture(p);
        
        fPixel[][] copyPix = edgeDetection(copy, edgeDist);
        fPixel[][] pix = edgeDetection(p, edgeDist);
        
        // goes through each pixel in the array, and blurs it if it is an array
        for(int row = 0; row < pix.length; row++) {
            for(int col = 0; col < pix[0].length; col++) {
                int maxX = row - pixelsToBlur;
                int maxY = col - pixelsToBlur;
                
                r = 0;
                g = 0;
                b = 0; 
                totalPixels = 0;

                if(pix[row][col].getEdge() == true){
                    // loop for pixels by amount pixelsToBlur
                    for(int x = row - pixelsToBlur; x <= maxX + pixelsToBlur; x++) {
                        for(int y = col - pixelsToBlur; y <= maxY + pixelsToBlur; y++) { 
                            if( (x >= 0) && (y >= 0) && (x < pix.length) && (y < pix[0].length)) {
                                pixel = copyPix[x][y];
                                r += pixel.getRed();
                                g += pixel.getGreen();
                                b += pixel.getBlue();
                                totalPixels++;
                                count++;
                            }
                        }
                    }
                    
                    r /= totalPixels;
                    g /= totalPixels;
                    b /= totalPixels;
                    
                    pix[row][col].setRed(r);
                    pix[row][col].setGreen(g);
                    pix[row][col].setBlue(b);
                }
            }
        }
        System.out.println("The innermost loop was ran " + count + " times.");
    }
    
    public static void main(String[] args){
        fPicture mst;
        mst = new fPicture("butterfly1.jpg");
        
        mst.explore();
        
        // picture p, pixelsToBlur, edgeDist
        antialias(mst,1,30);

        mst.explore();      
    }

}