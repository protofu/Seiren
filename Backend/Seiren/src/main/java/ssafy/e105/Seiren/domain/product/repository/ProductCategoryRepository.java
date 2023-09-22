package ssafy.e105.Seiren.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.e105.Seiren.domain.product.entity.Product;
import ssafy.e105.Seiren.domain.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findAllByProduct(Product product);
}