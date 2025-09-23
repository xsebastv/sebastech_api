package co.edu.usbcali.sebastech.repository;

import co.edu.usbcali.sebastech.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}