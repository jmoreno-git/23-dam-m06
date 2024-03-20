package cat.proven.storejpa.model;

import cat.proven.storejpa.model.Product;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-22T09:03:56", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> code;
    public static volatile SingularAttribute<Category, Long> id;
    public static volatile ListAttribute<Category, Product> products;

}