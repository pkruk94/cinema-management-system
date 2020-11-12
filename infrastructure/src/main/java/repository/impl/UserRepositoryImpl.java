package repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import repository.jpa.JpaUserRepository;
import user.User;
import user.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> addOrUpdate(User user) {
        return Optional.of(jpaUserRepository.save(user));
    }

    @Override
    public List<User> addOrUpdateMany(List<User> items) {
        return jpaUserRepository.saveAll(items);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return jpaUserRepository.findAllById(ids);
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return jpaUserRepository
                .findById(id)
                .flatMap(user -> {
                    jpaUserRepository.deleteById(id);
                    return Optional.of(user);
                });
    }

    @Override
    public List<User> deleteAllById(List<Long> ids) {
        List<User> users = jpaUserRepository.findAllById(ids);
        jpaUserRepository.deleteAll(users);
        return users;
    }

    @Override
    public boolean deleteAll() {
        jpaUserRepository.deleteAll();
        return true;
    }
}
