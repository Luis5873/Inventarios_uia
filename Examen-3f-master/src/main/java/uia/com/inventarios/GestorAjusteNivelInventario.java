package uia.com.inventarios;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorAjusteNivelInventario extends ReporteAjusteNivelInventario
    
{
    List<SolicitudAjusteNivelInventario> items = new ArrayList<SolicitudAjusteNivelInventario>();


    public GestorAjusteNivelInventario(IAjusteNivelInventario inventario)
    {
        super(inventario);
    }

    public GestorAjusteNivelInventario(String nombre)
    {
        super();
        //this.gestorApartado = new GestorApartado();
    }

    public GestorAjusteNivelInventario() {

    }
    public List<SolicitudAjusteNivelInventario> getItems()
    {
        return this.items;
    }


    public void setItems(List<SolicitudAjusteNivelInventario> items) {
        this.items = items;
    }


    public SolicitudAjusteNivelInventario agregaSolicitudSalidaMaterial(String nomArchivo, String solicitante, String categoria) throws IOException {
        String id = nomArchivo+"_"+String.valueOf(this.getItems().size()+1)+".json";
        String existencia = "";
        IAjusteNivelInventario inventario=null;
        SolicitudAjusteNivelInventario newSSM = new SolicitudAjusteNivelInventario(super.inventario, id, solicitante, categoria);
        return newSSM;
    }

    @Override
    public void print() {

    }


}
