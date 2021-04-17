package group.springbootdemo.service;

import group.springbootdemo.model.Detail;
import group.springbootdemo.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public List<Detail> findAll() {
        return detailRepository.findAll();
    }

    public Detail findByName(String name) {
        return detailRepository.findDetailByName(name);
    }

}
