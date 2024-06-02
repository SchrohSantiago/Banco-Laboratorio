package ar.edu.utn.frbb.tup.persistence;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBaseDao {
    protected static Map<String, Map<Long, Object>> poorMansDatabase = new HashMap<>();
    protected abstract String getEntityName(); // Este metodo abstracto debe ser implementado en cada clase hija de AbstractBaseDao para indicar el nombre de la entidad que representa... Cliente, Cuenta o Movimiento 

    protected Map<Long, Object> getInMemoryDatabase() {
        if (poorMansDatabase.get(getEntityName()) == null) {  // Si no existe una base de datos para la entidad que representa esta clase hija, la creamos y la guardamos en poorMansDatabase
            poorMansDatabase.put(getEntityName(),new HashMap<>()); 
        }
        return poorMansDatabase.get(getEntityName());
    }
}