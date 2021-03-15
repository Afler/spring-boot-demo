package group.springbootdemo.repository;

import group.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring data automatically create implement of the interface
public interface UserRepository extends JpaRepository<User, Integer> {

}
