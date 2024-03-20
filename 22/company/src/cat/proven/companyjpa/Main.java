package cat.proven.companyjpa;

import cat.proven.companyjpa.model.Company;
import cat.proven.companyjpa.model.CompanyJpaDb;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("companyPU");
        CompanyJpaDb companyDbHelper = new CompanyJpaDb(emf);
        Company company = new Company(0L, "12345678Z", "ProvenSoft, S.A.", 2000);
        companyDbHelper.create(company);
    }
    
}
