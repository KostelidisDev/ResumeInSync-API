package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import io.vavr.control.Try;

public interface PasswordService {

    Try<User> encryptUserPassword(User user);

    Try<String> encryptPassword(String password);

    Try<Boolean> passwordMatches(String password, String hash);
}
