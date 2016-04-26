package mb;

import dao.DaoFactory;
import dominio.Curso;
import dominio.EstadoCivil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "estadoCivilConverter")
public class EstadoCivilConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            EstadoCivil estadoCivil;
            DaoFactory daofactory = new DaoFactory();
            estadoCivil = daofactory.getEstadoCivilDao().findById(Integer.valueOf(value));
            return estadoCivil;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof EstadoCivil) {

            EstadoCivil estadoCivil = (EstadoCivil) value;

            return String.valueOf(estadoCivil.getId());
        }
        return "";
    }
}
