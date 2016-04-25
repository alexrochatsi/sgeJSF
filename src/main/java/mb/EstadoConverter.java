package mb;

import dao.DaoFactory;
import dominio.Estado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Estado estado;
            DaoFactory daofactory = new DaoFactory();
            estado = daofactory.getEstadoDao().findById(Integer.valueOf(value));
            return estado;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Estado) {

            Estado estado = (Estado) value;

            return String.valueOf(estado.getId());
        }
        return "";
    }
}
