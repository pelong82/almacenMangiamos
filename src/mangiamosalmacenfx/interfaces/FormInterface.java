/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.interfaces;

/**
 *
 * @author Administrator
 */
public interface FormInterface <AnyObject> {
    
    /**
     * Limpiar La Informacion del Formulario
     */
    public void clearForm();
    
    /**
     * Desactiva los Botones de Editar Remover y Guardar
     */
    public void disableBtsDeleteSave();
    
    /**
     * Desactiva el boton de nuevo elemento
     */
    public void disableBtNew();
    
    /**
     * Activa Botones de Eliminar Y Guardar
     */
    public void enableBtsDeleteSave();
    
    /**
     * activa el boton de Nuevo elemento
     */
    public void enableBtNew();
    
    /**
     * Remueve un objeto
     */
    public void remove();
    
    /**
     * Guarda un objeto
     */
    public void save();
    
    /**
     * Muestra la informacion en el Formulario
     */
    public void showDetails(AnyObject object);
    
    /**
     * Muestra Mensajes De error
     */
    public void showMessageError(String text);
    
    /**
     * Muestra Mensajes De exito
     */
    public void showMessageOk(String text);
    
    /**
     * Verifica la informaciondel Del formulario
     */
    public void valiDataForm();
   
}
