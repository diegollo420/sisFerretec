
package Datos;
 
public class Dpersona {
   
private int cod_persona;
private String nombre_persona  ;
private String direccion  ;
private String telefono  ;
private String email  ;

    public Dpersona() {
    }

    public Dpersona(int cod_persona, String nombre_persona, String direccion, String telefono, String email) {
        this.cod_persona = cod_persona;
        this.nombre_persona = nombre_persona;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getCod_persona() {
        return cod_persona;
    }

    public void setCod_persona(int cod_persona) {
        this.cod_persona = cod_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    
    
}
