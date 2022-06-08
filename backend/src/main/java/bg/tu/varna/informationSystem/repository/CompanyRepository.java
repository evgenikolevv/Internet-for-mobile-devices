package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Boolean existsByName(String name);

    List<Company> findByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "INSERT INTO users_companies(user_id,company_id) VALUES(:userId,:companyId)", nativeQuery = true)
    void assignUserToCompany(@Param("userId") Long userId, @Param("companyId") Long companyId);

    @Query(value = "select companies.id, companies.name, companies.created_ts, companies.updated_ts from users_companies "
            + "join users on users_companies.user_id = users.id "
            + "join companies on users_companies.company_id  = company_id "
            + "where users.id = :userId", nativeQuery = true)
    List<Company> getCompaniesByUserId(@Param("userId") Long userId);
}
