package data.repo;

import data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
