package lt.mackelo.backend.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static lt.mackelo.backend.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    PERSON(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(BLOG_READ, BLOG_WRITE, PERSON_READ, PERSON_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
