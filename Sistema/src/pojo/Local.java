package pojo;

/**
 *
 * @author gabri
 */
public class Local {
    private String nome_local;
    private String rua;
    private String bairro;
    private String cidade_local;
    private int numero_local;
    private int local_id;
    private int cep_local;

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero_local() {
        return numero_local;
    }

    public void setNumero_local(int numero_local) {
        this.numero_local = numero_local;
    }
    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }
    
    public String getCidade_local() {
        return cidade_local;
    }

    public void setCidade_local(String cidade_local) {
        this.cidade_local = cidade_local;
    }

    public int getCep_Local() {
        return cep_local;
    }

    public void setCep_Local(int cep_local) {
        this.cep_local = cep_local;
    }
    
    @Override
    public String toString(){
        return "Id: "+local_id+"=> ["+nome_local+"]\nCidade: "+cidade_local+"\nBairro: "+bairro+"\nRua "+rua+"\nNº "+numero_local+"\nCEP: "+cep_local;
    }
    public String toStringList(){
        return nome_local+"\nCidade: "+cidade_local+"\nBairro: "+bairro+"\nRua "+rua+"\nNº "+numero_local+"\nCEP: "+cep_local;
    }
    
    public Local(String nome_local,String cidade_local,String bairro,String rua,int numero_local,int cep_local){
        this.nome_local = nome_local;
        this.cidade_local = cidade_local;
        this.bairro = bairro;
        this.rua = rua;
        this.numero_local = numero_local;
        this.cep_local = cep_local;        
    }
    public Local(int local_id,String nome_local,String cidade_local,String bairro,String rua,int numero_local,int cep_local){
        this.local_id = local_id;
        this.nome_local = nome_local;
        this.cidade_local = cidade_local;
        this.bairro = bairro;
        this.rua = rua;
        this.numero_local = numero_local;
        this.cep_local = cep_local;
    }
}
