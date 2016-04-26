package mb;

import dao.DaoFactory;
import dominio.Aluno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "alunoConverter")
public class AlunoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Aluno aluno;
            DaoFactory daofactory = new DaoFactory();
            aluno = daofactory.getAlunoDao().findById(Integer.valueOf(value));
            return aluno;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Aluno) {

            Aluno aluno = (Aluno) value;

            return String.valueOf(aluno.getId());
        }
        return "";
    }
}
