package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select not exists (select "
            + "case when rents.id is not null then false "
            + "else true "
            + "end from rents "
            + "where rents.date_from <= :dateTo "
            + "and  rents.date_to >= :dateFrom "
            + "and vehicle_id = :vehicleId) as available ", nativeQuery = true)
    Boolean isAvailable(@Param("vehicleId") Long id,
                        @Param("dateFrom") LocalDateTime dateFrom,
                        @Param("dateTo") LocalDateTime dateTo);
}
