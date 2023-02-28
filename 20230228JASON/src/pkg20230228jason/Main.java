package pkg20230228jason;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonWriter;

/**
 *
 * @author Marcos Miranda
 */
public class Main {

    public static void main(String[] args) throws IOException {
         
              
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()            //creamos el objectBuilder
                .add("nombre", "Sara")                                       //añadimos 
                .add("apellido", "Garcia")
                .add("edad", 21)
                .add("direccion", Json.createObjectBuilder()
                        .add("calle", "Uria 12")
                        .add("ciudad", "Avilés")
                        .add("provincia", "Asturias")
                        .add("codigo", "33401"))
                .add("telefono", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("tipo", "casa")
                                .add("numero", "985-213344"))
                        .add(Json.createObjectBuilder()
                                .add("tipo", "fax")
                                .add("numero", "985-213355")));
        
        // aqui podríamos seguir añadiendo.... porque no hemos hecho el build();
                
        JsonObject value = jsonObjectBuilder.build();           //pegamos. lo hacemos en dos partes.
        
        // ejemplo con obtetos mutables  (builders)
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objeto1 = Json.createObjectBuilder().add("nombre", "Sara");
        JsonObjectBuilder objeto2 = Json.createObjectBuilder().add("nombre", "Carmen");
        JsonArray jsonArray = arrayBuilder.add(objeto1).add(objeto2).build();
        
        //mostramos datos
        System.out.println(value.toString());

        //creamos otro objectBuilder
        JsonObjectBuilder jsonObjectBuilder2= Json.createObjectBuilder()
                .add("nombre", "Maria")
                .add("apellido", "Pérez")
                .add("edad", 21)
                .add("direccion", Json.createObjectBuilder()
                        .add("calle", "Covadonga 2")
                        .add("ciudad", "Oviedo")
                        .add("provincia", "Asturias")
                        .add("codigo", 33011))
                .add("telefono", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("tipo", "casa")
                                .add("numero", "984-213344"))
                        .add(Json.createObjectBuilder()
                                .add("tipo", "fax")
                                .add("numero", "984-213355")));
              
        JsonObject value2 = jsonObjectBuilder2.build();
        
        System.out.println(value2.toString());
        
        JsonObjectBuilder jsonObjectBuilder3 = Json.createObjectBuilder()      //creamos otro objeto
        .add("nombre", "Marcos")
        .add("apellido", "Miranda")
        .add("edad", 46)
        .add("direccion", Json.createObjectBuilder()    //rreamos objeto porque es la direcccion
        .add("calle", "Avenida de la Vega")
        .add("ciudad", "El Entrego")
        .add("provincia", "Asturias")
        .add("codigo postal", 33940));
        
        JsonObject value3 = jsonObjectBuilder3.build();                 // pegamos..construimos
        
        System.out.println(value3.toString());
        



        // ejemplo acceso al "nombre" de un JsonObject
       
      
        
        JsonString nombre = value.getJsonString("nombre");
        System.out.println("nombre:"+nombre.toString());
        
        
        JsonNumber edad = value.getJsonNumber("edad");
        
        System.out.println("edad:"+edad.toString());
        

        // ejemplo acceso a "ciudad"
        
        String ciudad = value.getJsonObject("direccion").getString("ciudad");
        System.out.println(ciudad);

        // Crear un Array de Json        
        JsonArray arrayJson = Json.createArrayBuilder().add(value)
                .add(value2)
                .build();
        


        // Escribe el fichero Json
        FileWriter ficheroSalida = new FileWriter("salida.json");
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();

        // Ejemplo entrada
        FileReader entrada = new FileReader("salida.json");
        JsonReader jsonReader = Json.createReader(entrada);
        JsonArray readedArray = jsonReader.readArray();

         // Ver todos los nombres del fichero leido
        for (int i = 0; i < readedArray.size(); i++) {

            String nombreLeido = readedArray.getJsonObject(i).getString("nombre");
            System.out.println(i + ":nombre: " + nombreLeido);
        }


    }
        
    }
    

