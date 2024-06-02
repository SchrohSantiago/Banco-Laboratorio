package ar.edu.utn.frbb.tup.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.persistence.entity.ClienteEntity;


public class ClienteDao extends AbstractBaseDao{

    public Cliente find(long dni) {
        if (getInMemoryDatabase().get(dni) == null) {
            return null;
        } else {
            return ((ClienteEntity) getInMemoryDatabase().get(dni)).toCliente();
        }
    }

    public void save(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity(cliente);
        entity.setId(cliente.getDni());
        getInMemoryDatabase().put(cliente.getDni(), entity);
    }

    public List<Cliente> getAll() {
        Map<Long, Object> database = getInMemoryDatabase();
        List<Cliente> clientes = new ArrayList<>();
        for (Object entity : database.values()) {
            clientes.add(((ClienteEntity) entity).toCliente());
        }
        return clientes;
    }

    @Override
    protected String getEntityName() {
        return "CLIENTE";
    }
}