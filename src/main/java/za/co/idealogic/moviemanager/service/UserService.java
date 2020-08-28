package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.config.security.Constants;
import za.co.idealogic.moviemanager.domain.Authority;
import za.co.idealogic.moviemanager.domain.User;
import za.co.idealogic.moviemanager.repository.AuthorityRepository;
import za.co.idealogic.moviemanager.repository.UserRepository;
import za.co.idealogic.moviemanager.service.dto.UserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }


    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

}
