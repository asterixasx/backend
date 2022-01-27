package lt.mackelo.backend.security;

public enum ApplicationUserPermission {
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write"),
    BLOG_READ("blog:read"),
    BLOG_WRITE("blog:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
