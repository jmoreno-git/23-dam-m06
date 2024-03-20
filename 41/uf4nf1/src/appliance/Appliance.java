package appliance;

import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Appliance {
    private String name;
    private String color;
    private double price;
    private EnergyType energyType;
    private double weight;
    //
    public static double vat = 0.21;
    
    public Appliance(String name, String color, double price, EnergyType energyType, double weight) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.energyType = energyType;
        this.weight = weight;
    }

    public Appliance() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appliance other = (Appliance) obj;
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appliance{");
        sb.append("name=").append(name);
        sb.append(", color=").append(color);
        sb.append(", price=").append(price);
        sb.append(", energyType=").append(energyType);
        sb.append(", weight=").append(weight);
        sb.append(", vat=").append(vat);
        sb.append('}');
        return sb.toString();
    }
    
    public double getPriceWithVat() {
        return this.price * (1.0 + vat);
    }
    
    public static double calcPriceWithVat(double amount) {
        return amount * (1.0 + vat);
    }    
}
