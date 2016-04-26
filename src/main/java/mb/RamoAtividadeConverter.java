package mb;

import dao.DaoFactory;
import dominio.RamoAtividade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "ramoAtividadeConverter")
public class RamoAtividadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            RamoAtividade ramoAtividade;
            DaoFactory daofactory = new DaoFactory();
            ramoAtividade = daofactory.getRamoAtividadeDao().findById(Integer.valueOf(value));
            return ramoAtividade;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof RamoAtividade) {

            RamoAtividade ramoAtividade = (RamoAtividade) value;

            return String.valueOf(ramoAtividade.getId());
        }
        return "";
    }
}
