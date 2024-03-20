package cat.proven.storejpa.model;

import cat.proven.storejpa.model.Category;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-22T09:03:56", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> code;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, Double> stock;
    public static volatile SingularAttribute<Product, Category> category;

}