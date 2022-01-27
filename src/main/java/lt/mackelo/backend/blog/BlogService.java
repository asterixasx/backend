package lt.mackelo.backend.blog;

import lt.mackelo.backend.person.Person;
import lt.mackelo.backend.person.PersonRepository;
import lt.mackelo.backend.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, PersonRepository personRepository) {
        this.blogRepository = blogRepository;
        this.personRepository = personRepository;
    }

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public void addNewBlog(Blog blog) {
        Optional<Blog> blogOptional = blogRepository.findBlogByTitle(blog.getTitle());
        if(blogOptional.isPresent()) {
            throw new IllegalStateException("title with this name exists");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        blog.setPerson(optionalPerson.get());
        blogRepository.save(blog);
    }

    public void deleteBlog(Long blogId) {
        boolean exists = blogRepository.existsById(blogId);
        if(!exists) {
            throw new IllegalStateException("blog with id " + blogId + " does not exists");
        }
        blogRepository.deleteById(blogId);
    }

    @Transactional
    public void updateBlog(Long blogId, String title, String text) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalStateException(
                        "blog with id " + " does not exists"));

        if(title != null && title.length() > 0 && !Objects.equals(blog.getTitle(), title)) {
            Optional<Blog> blogOptional = blogRepository.findBlogByTitle(title);
            if(blogOptional.isPresent()) {
                throw new IllegalStateException("title with this name exists");
            }
            blog.setTitle(title);
        }

        if(text != null && text.length() > 0 && !Objects.equals(blog.getText(),text)) {
            blog.setText(text);
        }
    }

    public List<Blog> getUserBlogs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        List<Blog> list = blogRepository.findAll();

        List<Blog> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if(optionalPerson.get().getId().equals(list.get(i).getPerson().getId())) {
                newList.add(list.get(i));
            }
        }

        return newList;
    }

    public void updateUserBlog(Long blogId, String title, String text) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalStateException(
                        "blog with id " + " does not exists"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        if(!blog.getPerson().getId().equals(optionalPerson.get().getId())) {
            throw new IllegalStateException("you do not have right permissions to do that");
        }

        if(title != null && title.length() > 0 && !Objects.equals(blog.getTitle(), title)) {
            Optional<Blog> blogOptional = blogRepository.findBlogByTitle(title);
            if(blogOptional.isPresent()) {
                throw new IllegalStateException("title with this name exists");
            }
            blog.setTitle(title);
        }

        if(text != null && text.length() > 0 && !Objects.equals(blog.getText(),text)) {
            blog.setText(text);
        }
    }

    public void deleteUserBlog(Long blogId) {
        boolean exists = blogRepository.existsById(blogId);
        if(!exists) {
            throw new IllegalStateException("blog with id " + blogId + " does not exists");
        }

        Optional<Blog> blog = blogRepository.findById(blogId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Person> optionalPerson = personRepository.findPersonByEmail(currentPrincipalName);

        if(!blog.get().getPerson().getId().equals(optionalPerson.get().getId())) {
            throw new IllegalStateException("you do not have right permissions to do that");
        }

        blogRepository.deleteById(blogId);
    }
}
