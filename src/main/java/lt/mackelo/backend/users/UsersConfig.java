package lt.mackelo.backend.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UsersConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UsersRepository repository) {
        return args -> {
            Users h = new Users(
                    "h@mackelo.lt",
                    "LabasH"
            );

            Users b = new Users(
                    "b@mackelo.lt",
                    "LabasB"
            );

            repository.saveAll(
                    List.of(h, b)
            );
        };


    }
}
