package pojo;

/**
 *
 * @author gabri
 */
public class PartEvento {
    private int participante_id;
    private int evento_id;
    
    public int getParticipante_id() {
        return participante_id;
    }
    public int getEvento_id() {
        return evento_id;
    }
   
    public PartEvento(int evento_id,int participante_id){
        this.evento_id = evento_id;
        this.participante_id = participante_id;
    }
}
