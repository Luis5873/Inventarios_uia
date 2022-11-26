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
import java.util.*;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudAjusteNivelInventario.class, name = "SRM")
})

public class ReporteAjusteNivelInventario implements IAjusteNivelInventario
{
    protected NivelInventario inventario;
    protected SolicitudAjusteNivelInventario sem;

    public ReporteAjusteNivelInventario(IAjusteNivelInventario inventario)
    {
        super();
        this.inventario = new NivelInventario();
    }

    public ReporteAjusteNivelInventario() {
        super();
    }


    public void cargaSolicitudRetiro(String nombre)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sem =  mapper.readValue(new FileInputStream(nombre), SolicitudAjusteNivelInventario.class );
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
    public void agrega(String idPartida, String descPartida, String idSubpartida, String descSubpartida, String idCat, String descCat,
                       Lote lote, int minimoNivel, String fechaActualizacion)
    {
        InfoItem item = new InfoItem("Item", idPartida, descPartida, descCat,  idPartida, idSubpartida, idCat,
                lote, minimoNivel, fechaActualizacion);
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
    public void serializaNivelInventario(String nombre) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(nombre), this);
    }

    public NivelInventario inventario() {
        return inventario;
    }

    public SolicitudAjusteNivelInventario sem() {
        return sem;
    }

    public void cargaSolicitudAjusteNivelInventario() {
        Iterator<Map.Entry<String, InfoItem>> partida = sem.getInventario().getItems().entrySet().iterator();
        Iterator<Map.Entry<String, InfoItem>> subpartida;
        Iterator<Map.Entry<String, InfoItem>> minimoNivel;
        Iterator<Map.Entry<String, InfoItem>> fechaActualizacion ;
        Iterator<Map.Entry<String, InfoItem>> cantidad;



      while (partida.hasNext()) {

          Map.Entry<String, InfoItem> value = partida.next();
          subpartida = sem.getInventario().getItems().get(value.getKey()).getItems().entrySet().iterator();
          System.out.println(value.getKey());


          while (subpartida.hasNext()) {
              Map.Entry<String, InfoItem> value2 = subpartida.next();
              minimoNivel = sem.getInventario().getItems().get(value.getKey()).getItems().entrySet().iterator();
              System.out.println(value2.getValue());

              while (minimoNivel.hasNext()) {
                  Map.Entry<String, InfoItem> value3= subpartida.next();
                  minimoNivel = sem.getInventario().getItems().get(value.getKey()).getItems().entrySet().iterator();
                  System.out.println(value3.getValue());

              }

          }
      }












    }

}
