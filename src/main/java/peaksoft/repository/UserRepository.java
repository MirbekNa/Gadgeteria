package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
