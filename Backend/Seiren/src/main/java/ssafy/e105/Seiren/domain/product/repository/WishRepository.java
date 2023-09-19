package ssafy.e105.Seiren.domain.product.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.e105.Seiren.domain.product.entity.Product;
import ssafy.e105.Seiren.domain.product.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByUser_IdAndProduct_ProductId(Long userId, Long productId);

    List<Product> findByUser_Id(Long id);
}
