package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team58.healthy.model.Authority;
import team58.healthy.repository.AuthorityRepository;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findByName(String name) {
        return authorityRepository.findByName(name);
    }

    public Authority findById(Long id)
    {
        return authorityRepository.findById(id).orElseGet(null);
    }
}
