package co.edu.usbcali.sebastech.repository;

import co.edu.usbcali.sebastech.domain.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
