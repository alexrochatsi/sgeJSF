package mb;

import dao.DaoFactory;
import dominio.VagaEstagio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "vagaEstagioConverter")
public class VagaEstagioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            VagaEstagio vagaEstagio;
            DaoFactory daofactory = new DaoFactory();
            vagaEstagio = daofactory.getVagaEstagioDao().findById(Integer.valueOf(value));
            return vagaEstagio;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof VagaEstagio) {

            VagaEstagio vagaEstagio = (VagaEstagio) value;

            return String.valueOf(vagaEstagio.getId());
        }
        return "";
    }
}
