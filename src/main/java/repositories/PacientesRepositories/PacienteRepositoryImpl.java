package repositories.PacientesRepositories;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import db.MongoDB;

public class PacienteRepositoryImpl implements PacienteRepository {

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("TrabajoMongo");
	MongoCollection<Document> collection = database.getCollection("Pacientes");
	String dni = "Dni", nombre = "Nombre", apellidos = "Apellido", fechaNacimiento = "Fecha_Nacimiento", sexo = "Sexo",
			lugarNacimiento = "Lugar_Nacimiento", altura = "Altura", peso = "Peso", grupo_Sanguineo = "Grupo_Sanguineo",
			enfermedad = "Enfermedad", tipo = "Tipo";

	@Override
	public List<Document> findAll() {

		Bson projectionFields = Projections.fields(Projections.include(dni, nombre, enfermedad),
				Projections.excludeId());
		MongoCursor<Document> cursor = collection.find().projection(projectionFields).iterator();

		List<Document> documentList = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				documentList.add(document);
			}
		} finally {
			cursor.close();
		}

		return documentList;
	}

	@Override
	public Optional<Document> findById(String id) {
		Bson filter = eq(dni, id);
		Bson projectionFields = Projections.fields(Projections.include(dni, nombre, enfermedad),
				Projections.excludeId());
		Document result = collection.find(filter).projection(projectionFields).first();
		return Optional.ofNullable(result);
	}

	@Override
	public Boolean save(Document entity) {
		try {
			collection.insertOne(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean delete(String dni) {
		try {
			Bson filter = eq(dni, dni);
			collection.deleteOne(filter);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Document> findByNombre(String nombre) {
		Bson filter = eq("Nombre", nombre);
		Bson projectionFields = Projections.excludeId();
		List<Document> results = collection.find(filter).projection(projectionFields).into(new ArrayList<>());
		return results;
	}

}
