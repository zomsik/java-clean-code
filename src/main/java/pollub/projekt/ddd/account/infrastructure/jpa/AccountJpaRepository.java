package pollub.projekt.ddd.account.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AccountJpaRepository  extends JpaRepository<AccountEntity, Integer> {

    @Query(value = """
        SELECT CASE WHEN COUNT(ac) > 0 THEN true ELSE false END
        FROM AccountEntity ac
        WHERE ac.username = :login""")
    boolean existsByLogin(@Param("login") String login);

    @Query(value = """
        SELECT ac.password
        FROM AccountEntity ac
        WHERE ac.username = :login""")
    String getPasswordByLogin(@Param("login") String login);

    @Query(value = """
        SELECT ac.registerDate
        FROM AccountEntity ac
        WHERE ac.username = :login""")
    LocalDateTime getRegisterDateByLogin(@Param("login") String login);

    @Query(value = """
        SELECT ac.id
        FROM AccountEntity ac
        WHERE ac.username = :login""")
    Integer getIdByLogin(@Param("login") String login);
}
