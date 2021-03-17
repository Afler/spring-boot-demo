package group.springbootdemo.service;

import group.springbootdemo.model.Auto;
import group.springbootdemo.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private final AutoRepository autoRepository;

    @Autowired
    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public Auto findAutoById(int id) {
        return autoRepository.findById(id).orElse(null);
    }

    public List<Auto> findAllAuto() {
        return autoRepository.findAll();
    }

    public Auto saveAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public void deleteAutoById(int id) {
        autoRepository.deleteById(id);
    }

}
