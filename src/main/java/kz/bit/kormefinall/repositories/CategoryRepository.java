package kz.bit.kormefinall.repositories;
        import org.springframework.data.jpa.repository.JpaRepository;
        import kz.bit.kormefinall.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}



