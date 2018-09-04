package one.synergy.synergyone.repository;

import one.synergy.synergyone.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);


}
