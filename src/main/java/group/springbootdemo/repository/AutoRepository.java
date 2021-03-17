package group.springbootdemo.repository;

import group.springbootdemo.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoRepository extends JpaRepository<Auto, Integer> {

}
