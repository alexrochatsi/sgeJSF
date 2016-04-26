package mb;

import dao.DaoFactory;
import dominio.Supervisor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "supervisorConverter")
public class SupervisorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Supervisor supervisor;
            DaoFactory daofactory = new DaoFactory();
            supervisor = daofactory.getSupervisorDao().findById(Integer.valueOf(value));
            return supervisor;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Supervisor) {

            Supervisor supervisor = (Supervisor) value;

            return String.valueOf(supervisor.getId());
        }
        return "";
    }
}
