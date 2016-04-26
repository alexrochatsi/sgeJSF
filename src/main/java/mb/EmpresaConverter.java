package mb;

import dao.DaoFactory;
import dominio.Empresa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "empresaConverter")
public class EmpresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Empresa empresa;
            DaoFactory daofactory = new DaoFactory();
            empresa = daofactory.getEmpresaDao().findById(Integer.valueOf(value));
            return empresa;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Empresa) {

            Empresa empresa = (Empresa) value;

            return String.valueOf(empresa.getId());
        }
        return "";
    }
}
