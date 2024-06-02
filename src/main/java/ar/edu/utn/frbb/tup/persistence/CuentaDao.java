package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.persistence.entity.CuentaEntity;

public class CuentaDao  extends AbstractBaseDao{
    public Cuenta find(Long numeroCuenta) {
        if (getInMemoryDatabase().get(numeroCuenta) == null) {
            return null;
        }
        return ((CuentaEntity) getInMemoryDatabase().get(numeroCuenta)).toCuenta();
    }

    public void save(Cuenta cuenta) {
        CuentaEntity entity = new CuentaEntity(cuenta);
        // Asegúrate de que la entidad tenga un ID único
        entity.setId(cuenta.getNumeroCuenta());
        getInMemoryDatabase().put(cuenta.getNumeroCuenta(), entity);
    }

    @Override
    protected String getEntityName() {
        return "CUENTA";
    }
}