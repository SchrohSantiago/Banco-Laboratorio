package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.operation.Movimiento;
import ar.edu.utn.frbb.tup.persistence.entity.MovimientoEntity;

public class MovimientoDao extends AbstractBaseDao {

   @Override
   protected String getEntityName() {
        return "MOVIMIENTO";
   }

   public Movimiento find(long id) {
        if (getInMemoryDatabase().get(id) == null) {
            return null;
        }
        return ((MovimientoEntity) getInMemoryDatabase().get(id)).toMovimiento(null);
    }

}
