package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Long> {
}
