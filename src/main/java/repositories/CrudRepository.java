package repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

public interface CrudRepository<T, id> {
	List<Document> findAll();

	Boolean save(T entity);

	Boolean delete(T entity);

	Boolean save(Document entity);

	Boolean delete(Document entity);

	Optional<Document> findById(String id);
}
