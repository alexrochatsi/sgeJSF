package mb;

import dao.DaoFactory;
import dominio.Coordenacao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "coordenacaoConverter")
public class CoordenacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Coordenacao coordenacao;
            DaoFactory daofactory = new DaoFactory();
            coordenacao = daofactory.getCoordenacaoDao().findById(Integer.valueOf(value));
            return coordenacao;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Coordenacao) {

            Coordenacao coordenacao = (Coordenacao) value;

            return String.valueOf(coordenacao.getId());
        }
        return "";
    }
}
