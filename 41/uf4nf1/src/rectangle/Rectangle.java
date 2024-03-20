package rectangle;

/**
 *
 * @author ProvenSoft
 */
public class Rectangle {
    //attributes
    private double base;
    private double height;
    
    //constructors
    public Rectangle() {
        
    }
    
    public Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    //accessors
    
    public double getBase() {
        return base;
    }
    
    public void setBase(double base) {
        this.base = base;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }  
    
    //methods
    
    public double perimeter() {
        return 2.0 * (base + height);
    }
    
    public double area() {
        return base * height;
    }

    /**
     * compare this with obj.
     * Two rectangles are considered equal if their base are equal and their height are equal
     * @param obj the object to compare with
     * @return true if equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean b= false;
        if (obj == null) {  //null object
            b = false;
        } else {
            if (this == obj) {  //same object (or instance)
                b = true;
            } else {
                if (obj instanceof Rectangle) {  //same class
                    Rectangle other = (Rectangle) obj;  //type cast
                    b = ( (this.base==other.base) && (this.height==other.height) );
                } else {  //not the same class
                    b = false;
                }
            }
        }
        return b;
    }  
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangle{");
        sb.append("base=");
        sb.append(base);
        sb.append(", height=");
        sb.append(height);
        sb.append("}");
        return sb.toString();        
    }
} 
