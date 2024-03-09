package gr.ihu.ict.resumeinsync.security.config;

import gr.ihu.ict.resumeinsync.common.constants.security.UserRole;
import io.vavr.control.Try;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

    @Bean
    protected RoleHierarchy roleHierarchy() {
        return Try.of(RoleHierarchyImpl::new)
                .map(this::setDefaultRoleHierarchyString)
                .get();
    }

    private RoleHierarchy setDefaultRoleHierarchyString(final RoleHierarchyImpl roleHierarchy) {
        roleHierarchy.setHierarchy(getRoleHierarchyString());
        return roleHierarchy;
    }

    private String getRoleHierarchyString() {
        return String.format(
                "%s < %s",
                UserRole.USER.name(),
                UserRole.USER.name());
    }
}
