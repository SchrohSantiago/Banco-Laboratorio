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
        getInMemoryDatabase().put(entity.getId(), entity);
    }

    @Override
    protected String getEntityName() {
        return "CUENTA";
    }
}