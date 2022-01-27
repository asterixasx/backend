package lt.mackelo.backend.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPerson() {
        return personService.getPerson();
    }

    @PostMapping
    public void registerNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
    }

    @PutMapping(path = "{personId}")
    public void updatePerson(
            @PathVariable("personId") Long personId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String roles) {
        personService.updatePerson(personId, email, password, roles);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }
}
