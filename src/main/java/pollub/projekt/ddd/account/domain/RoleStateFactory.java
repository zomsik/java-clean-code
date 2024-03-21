package pollub.projekt.ddd.account.domain;

public class RoleStateFactory {
    public static RoleState getRoleState(String role) {
        switch (role) {
            case "1":
                return new UserRole();
            case "2":
                return new ModeratorRole();
            case "3":
                return new AdminRole();
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
