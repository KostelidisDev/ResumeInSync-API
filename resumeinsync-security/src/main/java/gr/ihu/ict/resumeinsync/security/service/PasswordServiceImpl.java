package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordServiceImpl(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Try<User> encryptUserPassword(final User user) {
        return Try.run(() -> Objects.requireNonNull(user, "userToEncrypt is null"))
                .map(aVoid -> Option.of(user.getPassword()))
                .flatMap(Value::toTry)
                .flatMap(this::encryptPassword)
                .map(encryptedPassword -> {
                    user.setPassword(encryptedPassword);
                    return user;
                });
    }

    @Override
    public Try<String> encryptPassword(final String password) {
        return Try.run(() -> Objects.requireNonNull(password, "password is null"))
                .flatMap(ignored -> Try.of(() -> passwordEncoder.encode(password)))
                .recover(throwable -> password);
    }

    @Override
    public Try<Boolean> passwordMatches(final String password,
            final String hash) {
        return Try.run(() -> {
            Objects.requireNonNull(password, "plainText is null");
            Objects.requireNonNull(hash, "hashed is null");
        }).flatMap(ignored -> Try.of(() -> passwordEncoder.matches(password, hash)));

    }
}
