package lt.mackelo.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        if(optionalPerson.isEmpty() || optionalPerson.get().getRoles() == null ||
                !optionalPerson.get().getRoles().equals("ADMIN")) {
            throw new IllegalStateException("you can't access that information");
        }

        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        String encoded = passwordEncoder.encode(person.getPassword());
        person.setPassword(encoded);
        person.setRoles("USER");

        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists) {
            throw new IllegalStateException("person with id " + personId + " does not exists");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        if(optionalPerson.isEmpty() || optionalPerson.get().getRoles() == null ||
                !optionalPerson.get().getRoles().equals("ADMIN")) {
            throw new IllegalStateException("you do not have right permissions to do that");
        }

        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String email, String password, String roles) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "person with id " + " does not exists"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);
        if(optionalPerson.isEmpty() || optionalPerson.get().getRoles() == null ||
                !optionalPerson.get().getRoles().equals("ADMIN")) {
            throw new IllegalStateException("you do not have right permissions to do that");
        }

        if(email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if(personOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            person.setEmail(email);
        }

        if(password != null && password.length() > 0 && !Objects.equals(person.getPassword(),password)) {
            person.setPassword(passwordEncoder.encode(password));
        }

        if(roles != null && roles.length() > 0 && !Objects.equals(person.getRoles(),roles)) {
            person.setRoles(roles);
        }
    }
}
