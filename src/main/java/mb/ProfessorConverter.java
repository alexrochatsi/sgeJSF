package mb;

import dao.DaoFactory;
import dominio.Professor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "professorConverter")
public class ProfessorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Professor professor;
            DaoFactory daofactory = new DaoFactory();
            professor = daofactory.getProfessorDao().findById(Integer.valueOf(value));
            return professor;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Professor) {

            Professor professor = (Professor) value;

            return String.valueOf(professor.getId());
        }
        return "";
    }
}
