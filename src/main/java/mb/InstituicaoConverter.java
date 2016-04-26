package mb;

import dao.DaoFactory;
import dominio.Instituicao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "instituicaoConverter")
public class InstituicaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Instituicao instituicao;
            DaoFactory daofactory = new DaoFactory();
            instituicao = daofactory.getInstituicaoDao().findById(Integer.valueOf(value));
            return instituicao;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Instituicao) {

            Instituicao instituicao = (Instituicao) value;

            return String.valueOf(instituicao.getId());
        }
        return "";
    }
}
