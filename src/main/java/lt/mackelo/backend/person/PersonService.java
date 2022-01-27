package lt.mackelo.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists) {
            throw new IllegalStateException("person with id " + personId + " does not exists");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String email, String password) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "person with id " + " does not exists"));

        if(email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if(personOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            person.setEmail(email);
        }

        if(password != null && password.length() > 0 && !Objects.equals(person.getPassword(),password)) {
            person.setPassword(password);
        }

    }
}
