package bg.tu.varna.informationSystem.repository;

import bg.tu.varna.informationSystem.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
}
