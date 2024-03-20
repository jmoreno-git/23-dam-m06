package athlets;

/**
 *
 * @author alumne
 */
public class Athlet extends Person {
    private String discipline;
    private int number;

    public Athlet(String name, int age, String discipline, int number) {
        super(name, age);
        this.discipline = discipline;
        this.number = number;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Athlet{");
        sb.append(super.toString());
        sb.append(", discipline=").append(discipline);
        sb.append(", number=").append(number);
        sb.append('}');
        return sb.toString();
    }
    

    @Override
    public String attendClass() {
        return String.format("I’m an athlet, my name is %s and I’m %s",
                getName(), "attending class");
    }

    @Override
    public String jump() {
        String s = null;
        //TODO
        return s;
    }

    @Override
    public String run() {
        String s = null;
        //TODO
        return s;
    }

    @Override
    public String swim() {
        String s = null;
        //TODO
        return s;
    }
    
}
