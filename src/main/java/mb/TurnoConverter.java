package mb;

import dao.DaoFactory;
import dominio.Turno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "turnoConverter")
public class TurnoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Turno turno;
            DaoFactory daofactory = new DaoFactory();
            turno = daofactory.getTurnoDao().findById(Integer.valueOf(value));
            return turno;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Turno) {

            Turno turno = (Turno) value;

            return String.valueOf(turno.getId());
        }
        return "";
    }
}
