package mb;

import dao.DaoFactory;
import dominio.Curso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "cursoConverter")
public class CursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Curso curso;
            DaoFactory daofactory = new DaoFactory();
            curso = daofactory.getCursoDao().findById(Integer.valueOf(value));
            return curso;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Curso) {

            Curso curso = (Curso) value;

            return String.valueOf(curso.getId());
        }
        return "";
    }
}
