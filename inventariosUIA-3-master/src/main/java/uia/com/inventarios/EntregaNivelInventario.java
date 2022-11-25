package uia.com.inventarios;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudEntregaMaterial.class, name = "SEM")
})

public class EntregaNivelInventario implements IEntregaNivelInventario
{
    protected NivelInventario inventario;
    protected SolicitudEntregaMaterial sem;

    public EntregaNivelInventario(IEntregaNivelInventario inventario)
    {
        super();
        this.inventario = (NivelInventario) new NivelInventario();
    }

    public EntregaNivelInventario()
    {
    }

    @Override
    public void cargaSolicitudEntrega(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sem =  mapper.readValue(new FileInputStream(nombre), SolicitudEntregaMaterial.class );
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        this.sem.getInventario().print();
    }

    @Override
    public List<InfoItem> busca(int id, String descripcion, String categoria, String cantidad, String idPartida, String idSubpartida, String idCategoria)
    {
        return inventario.busca(id, descripcion, categoria, cantidad, idPartida, idSubpartida, idCategoria);
    }

    @Override
    public void serializa()
    {


    }

    @Override
    public void print() {

    }

    @Override
    public SolicitudEntregaMaterial nuevaSolicitudEntrega(String s)
    {
        return null;
    }


    public void agrega(String idPartida, String descPartida, int cantidad, String idSubpartida, String descSubpartida, String idCat,
           String descCat, String idLote, String lote, String fechaIngreso,
           String idProveedor, String proveedor)
    {
        InfoItem item = new InfoItem("Item", idPartida, descPartida, descCat, String.valueOf(cantidad), idPartida, idSubpartida, idCat,
                idLote, lote, fechaIngreso, idProveedor, proveedor);
    }

    @Override
    public void cargaSolicitudEntregaToInventario() {
       /*inventario.agrega(sem.getId(),sem.getDescripcion(),sem.getId(),null,null,null,null,null);
       */
        //Intente esto pero no me salio
       // inventario.agrega(sem.getId(),sem.getDescripcion(),sem.id(),null,null,null,null,null);



    }

    public void cargaInventario(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            inventario =  mapper.readValue(new FileInputStream(nombre), NivelInventario.class );
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.inventario.print();

    }
}
