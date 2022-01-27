package lt.mackelo.backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void addNewUsers(Users users) {
        Optional<Users> usersOptional = usersRepository.findUsersByEmail(users.getEmail());
        if(usersOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        usersRepository.save(users);
    }

    public void deleteUsers(Long usersId) {
        boolean exists = usersRepository.existsById(usersId);
        if(!exists) {
            throw new IllegalStateException("user with id " + usersId + " does not exists");
        }
        usersRepository.deleteById(usersId);
    }
}
