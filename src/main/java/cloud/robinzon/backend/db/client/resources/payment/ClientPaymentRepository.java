package cloud.robinzon.backend.db.client.resources.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPaymentRepository
        extends JpaRepository<ClientPayment, ClientPaymentKey> {
}
