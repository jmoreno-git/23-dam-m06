package friend;

/**
 *
 * @author ProvenSoft
 */
public class Friend {
    //attributes
    private String name;
    private int age;
    //constructors
    /**
     * default constructor
     */
    public Friend() {
        
    }
    
    /**
     * full initializer constructor
     * @param name
     * @param age 
     */
    public Friend(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    //accessors
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    
    //methods
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Friend{");
        sb.append("name=");
        sb.append(name);
        sb.append(", age=");
        sb.append(age);
        sb.append("}");
        return sb.toString();        
    }
    
    /**
     * compares this with obj.
     * Two friends are considered equal to each other if their names are equal
     * @param obj the object to compare with
     * @return true if they are equal, false otherwise
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
                if (obj instanceof Friend) {  //same class
                    Friend other = (Friend) obj;  //type cast
                    b = this.name.equals(other.name);
                } else {  //not the same class
                    b = false;
                }
            }
        }
        return b;
    }    
}
