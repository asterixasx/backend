package lt.mackelo.backend.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT u FROM Blog u WHERE u.title = ?1")
    Optional<Blog> findBlogByTitle(String title);
}
