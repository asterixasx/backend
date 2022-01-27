package lt.mackelo.backend.db;

import lt.mackelo.backend.person.Person;
import lt.mackelo.backend.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String[] args) {
        // Delete all
        this.personRepository.deleteAll();

        // Create users
        Person admin = new Person("admin@admin.com",passwordEncoder.encode("Admin123+"),"ADMIN");

        List<Person> users = Arrays.asList(admin);

        // Save to db
        this.personRepository.saveAll(users);
    }
}
