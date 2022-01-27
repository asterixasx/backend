package lt.mackelo.backend.security;

import lt.mackelo.backend.person.Person;
import lt.mackelo.backend.person.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private PersonRepository personRepository;

    public UserPrincipalDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = this.personRepository.findPersonByEmail(username);
        UserPrincipal userPrincipal = new UserPrincipal(person.get());

        return userPrincipal;
    }
}
