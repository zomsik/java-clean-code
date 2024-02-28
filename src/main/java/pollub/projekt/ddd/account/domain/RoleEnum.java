package pollub.projekt.ddd.account.domain;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum RoleEnum {

    USER("1", "USER"),
    MODERATOR("2", "MODERATOR"),
    ADMIN("3", "ADMIN");

    public final String id;
    public final String name;

}
