import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.MonedaConstructor;

public class GenerarArchivo {
    public void generadorJson(MonedaConstructor moneda) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("Conversiones" + ".json");
        escritura.write(gson.toJson(moneda));
        escritura.close();
    }
}

