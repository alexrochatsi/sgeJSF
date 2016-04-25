package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="empresa")

@NamedQueries({
    @NamedQuery(name = "Empresa.empresasAprovadas",
            query = "select e from Empresa e where e.statusAprovacao = :statusAprovado")
})
public class Empresa implements Serializable {
    @Id
    @SequenceGenerator(name = "empresa_id_gen",
            sequenceName = "empresa_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "empresa_id_gen")
    @Column(name="empresa_id")
    private Integer id;
    @Column(name = "razao_social", length = 200, nullable = false)
    private String razaoSocial;
    @Column(name = "cnpj", length = 40, nullable = false)
    private String cnpj;
    @Column(name = "nome_fantasia", length = 200, nullable = false)
    private String nomeFantasia;
    @Column(name = "inscricao_estadual", length = 30, nullable = false)
    private String inscricaoEstadual;
    @Column(name = "gerente_rh", length = 80, nullable = false)
    private String gerenteRh;
    @Column(name = "atividade_principal", length = 600, nullable = false)
    private String atividadePrincipal;
    @Column(name = "email", length = 150, nullable = false)
    private String email;
    @Column(name="status_aprovacao")
    private Boolean statusAprovacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dada_cadastro")
    private Date dataCadastro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "porte_id", nullable = true)
    private PorteEmpresa porte;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ramoatividade_id", nullable = true)
    private RamoAtividade ramoAtividade;
    
    //outra forma de fazer é usando @Embedded, só que mescla a tabela endereço com a instituicao
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Telefone telefone;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private RepresentanteEmpresa representanteLegalEmpresa;
    
    @OneToMany(mappedBy = "empresa", targetEntity = VagaEstagio.class, fetch = FetchType.EAGER)
    private List<VagaEstagio> vagas;
    
    @OneToMany(mappedBy = "empresa", targetEntity = Supervisor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Supervisor> supervisores;
    
    @OneToMany(mappedBy = "empresa", targetEntity = Estagio.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Estagio> estagios;
    
    public Empresa() {
        endereco = new Endereco();
        telefone = new Telefone();
    }
    
    public void setStatusAprovacao(Boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public Boolean getStatusAprovacao() {
        return statusAprovacao;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public String getGerenteRh() {
        return gerenteRh;
    }

    public void setGerenteRh(String gerenteRh) {
        this.gerenteRh = gerenteRh;
    }

    public PorteEmpresa getPorte() {
        return porte;
    }

    public void setPorte(PorteEmpresa porte) {
        this.porte = porte;
    }

    public String getAtividadePrincipal() {
        return atividadePrincipal;
    }

    public void setAtividadePrincipal(String atividadePrincipal) {
        this.atividadePrincipal = atividadePrincipal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) obj;
        if(id == null) {
            if (other.id != null){
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public RepresentanteEmpresa getRepresentanteLegalEmpresa() {
        return representanteLegalEmpresa;
    }

    public void setRepresentanteLegalEmpresa(RepresentanteEmpresa representanteLegalEmpresa) {
        this.representanteLegalEmpresa = representanteLegalEmpresa;
    }

    public List<VagaEstagio> getVagas() {
        return vagas;
    }

    public void setVagas(List<VagaEstagio> vagas) {
        this.vagas = vagas;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }
    
}
