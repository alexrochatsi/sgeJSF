package mb;

import dao.DaoFactory;
import dominio.Estagiario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "estagiarioConverter")
public class EstagiarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Estagiario estagiario;
            DaoFactory daofactory = new DaoFactory();
            estagiario = daofactory.getEstagiarioDao().findById(Integer.valueOf(value));
            return estagiario;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Estagiario) {

            Estagiario estagiario = (Estagiario) value;

            return String.valueOf(estagiario.getId());
        }
        return "";
    }
}
