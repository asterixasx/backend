package lt.mackelo.backend.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlog() {
        return blogRepository.findAll();
    }

    public void addNewBlog(Blog blog) {
        Optional<Blog> blogOptional = blogRepository.findBlogByTitle(blog.getTitle());
        if(blogOptional.isPresent()) {
            throw new IllegalStateException("title with this name exists");
        }
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
}
