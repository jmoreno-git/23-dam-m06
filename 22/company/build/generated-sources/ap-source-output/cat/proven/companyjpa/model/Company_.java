package cat.proven.companyjpa.model;

import cat.proven.companyjpa.model.Employee;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T10:19:25", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile ListAttribute<Company, Employee> employeeList;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, String> nif;
    public static volatile SingularAttribute<Company, Integer> foundationYear;
    public static volatile SingularAttribute<Company, Long> id;

}