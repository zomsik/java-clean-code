package pollub.projekt.ddd.post.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoryEnum {

    NONE(1, "none"),
    TECHNOLOGIA(2, "technologia"),
    INFORMACJE(3, "informacje"),
    CIEKAWOSTKI(4, "ciekawostki"),
    MOTORYZACJA(5, "motoryzacja"),
    UKRAINA(6, "ukraina"),
    PODROZE(7, "podroze"),
    SPORT(8, "sport"),
    ROZRYWKA(9,"rozrywka"),
    GOSPODARKA(10, "gospodarka");

    public final int id;
    public final String name;

    public static Integer getCategoryIdByName(String lowerCase) {
        for (CategoryEnum c : CategoryEnum.values()) {
            if (c.name.equalsIgnoreCase(lowerCase)) {
                return c.id;
            }
        }
        return NONE.id;
    }


}
