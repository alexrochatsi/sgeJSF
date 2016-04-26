package mb;

import dao.DaoFactory;
import dominio.Telefone;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "telefoneConverter")
public class TelefoneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Telefone telefone;
            DaoFactory daofactory = new DaoFactory();
            telefone = daofactory.getTelefoneDao().findById(Integer.valueOf(value));
            return telefone;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Telefone) {

            Telefone telefone = (Telefone) value;

            return String.valueOf(telefone.getId());
        }
        return "";
    }
}
