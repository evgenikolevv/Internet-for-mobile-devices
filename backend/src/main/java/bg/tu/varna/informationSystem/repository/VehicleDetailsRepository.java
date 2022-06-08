package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.Vehicle;
import bg.tu.varna.informationSystem.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {

    Optional<VehicleDetails> findByVehicle(Vehicle vehicle);
}
