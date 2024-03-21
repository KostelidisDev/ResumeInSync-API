package gr.ihu.ict.resumeinsync.security.service;

import gr.ihu.ict.resumeinsync.domain.entity.system.User;
import gr.ihu.ict.resumeinsync.security.domain.CustomUserDetails;
import io.vavr.control.Try;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    default Try<CustomUserDetails> userToCustomUserDetails(User user) {
        return Try.of(CustomUserDetails::new)
                .map(customUserDetails -> {
                    if(user == null) {
                        return customUserDetails;
                    }
                    if(customUserDetails == null) {
                        return null;
                    }
                    BeanUtils.copyProperties(
                            user,
                            customUserDetails
                            );

                    return customUserDetails;
                });
    }
}