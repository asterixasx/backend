package lt.mackelo.backend.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getBlog() {
        return blogService.getUserBlogs();
    }

    @PostMapping
    public void registerNewBlog(@RequestBody Blog blog) {
        blogService.addNewBlog(blog);
    }

    @PutMapping(path = "{blogId}")
    public void updateBlog(
            @PathVariable("blogId") Long blogId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String text) {
        blogService.updateUserBlog(blogId, title, text);
    }

    @DeleteMapping(path = "{blogId}")
    public void deleteBlog(@PathVariable("blogId") Long blogId) {
        blogService.deleteUserBlog(blogId);
    }
}
