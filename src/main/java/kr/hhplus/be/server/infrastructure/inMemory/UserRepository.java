package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private static Map<Long, User> store = new ConcurrentHashMap<>();
    private static AtomicLong idGenerator = new AtomicLong(0);

    public User save(User user) {
        if(user.getUserId() == null) {
            user.setUserId(idGenerator.incrementAndGet());
        }
        store.put(user.getUserId(), user);
        return user;
    }

    public Optional<User> findById(long userId) {
        return Optional.ofNullable(store.get(userId));
    }
}
