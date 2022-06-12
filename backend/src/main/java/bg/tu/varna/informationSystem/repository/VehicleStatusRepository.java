package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.VehicleStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatuses, Long> {
}
