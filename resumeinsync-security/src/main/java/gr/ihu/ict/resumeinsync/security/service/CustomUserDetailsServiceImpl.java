package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.domain.repository.system.UserRepository;
import gr.ihu.ict.resumeinsync.security.domain.CustomUserDetails;
import io.vavr.control.Option;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {
        return Option.ofOptional(userRepository.findByUsername(usernameOrEmail)).toTry()
                .orElse(() -> Option.ofOptional(userRepository.findByEmail(usernameOrEmail)).toTry())
                .flatMap(this::userToCustomUserDetails)
                .getOrElseThrow(() -> new UsernameNotFoundException(usernameOrEmail));
    }
}
