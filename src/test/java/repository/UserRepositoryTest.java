package repository;

import com.quicknotes.entity.User;
import com.quicknotes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void save_StoresRecord_WhenRecordIsValid() {

        final User expected = new User();
        expected.setUsername(randomUUID().toString());
        expected.setPassword(randomUUID().toString());

        final User saved = repository.save(expected);

        final User actual = entityManager.find(User.class, saved.getUsername());

        assertThat(actual).isEqualTo(expected);

    }


}
