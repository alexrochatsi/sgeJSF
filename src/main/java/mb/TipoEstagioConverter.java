package mb;

import dao.DaoFactory;
import dominio.TipoEstagio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "tipoEstagioConverter")
public class TipoEstagioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            TipoEstagio tipoEstagio;
            DaoFactory daofactory = new DaoFactory();
            tipoEstagio = daofactory.getTipoEstagioDao().findById(Integer.valueOf(value));
            return tipoEstagio;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TipoEstagio) {

            TipoEstagio tipoEstagio = (TipoEstagio) value;

            return String.valueOf(tipoEstagio.getId());
        }
        return "";
    }
}
