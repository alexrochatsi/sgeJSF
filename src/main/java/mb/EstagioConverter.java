package mb;

import dao.DaoFactory;
import dominio.Estagio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "estagioConverter")
public class EstagioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Estagio estagio;
            DaoFactory daofactory = new DaoFactory();
            estagio = daofactory.getEstagioDao().findById(Integer.valueOf(value));
            return estagio;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Estagio) {

            Estagio estagio = (Estagio) value;

            return String.valueOf(estagio.getId());
        }
        return "";
    }
}
