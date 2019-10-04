package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);


    static final int USER_ID = 1;
    static final int ADMIN_ID = 2;


    @Override
    public List<User> getAll() {
        return getCollection().stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail)).
                collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return getCollection().stream().
                filter(a -> email.equals(a.getEmail())).
                findFirst().orElse(null);
    }
}
