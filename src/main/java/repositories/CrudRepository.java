package repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

public interface CrudRepository<T, id> {
	List<Document> findAll();

	Boolean save(Document entity);

	Optional<Document> findById(String id);

	Boolean delete(String dni);
	
}
