package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
