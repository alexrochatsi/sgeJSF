package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="aluno")
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Aluno extends Pessoa {
    @Column(name = "naturalidade", length = 200, nullable = true)
    private String naturalidade;
    @Column(name = "filiacao_pai", length = 80, nullable = true)
    private String filiacaoPai;
    @Column(name = "filiacao_mae", length = 80, nullable = true)
    private String filiacaoMae;
    @Column(name = "deficiencia", length = 80, nullable = true)
    private String deficiencia;
    @Column(name = "rg", length = 20, nullable = false)
    private String rg;
    @Column(name = "matricula", length = 30, nullable = false)
    private String matricula;
    @Column(name = "modulo_atual", length = 2, nullable = false)
    private Integer moduloAtual;
    @Column(name = "ano_entrada", length = 7, nullable = false)
    private String anoEntrada;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="supervisor_id",nullable = true)
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curso_id",nullable = false)
    private Curso curso;
    
    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getFiliacaoPai() {
        return filiacaoPai;
    }

    public void setFiliacaoPai(String filiacaoPai) {
        this.filiacaoPai = filiacaoPai;
    }

    public String getFiliacaoMae() {
        return filiacaoMae;
    }

    public void setFiliacaoMae(String filiacaoMae) {
        this.filiacaoMae = filiacaoMae;
    }


    public String getDeficiencia() {
        return deficiencia;
    }

    public void setPortadorDeficiencia(String deficiencia) {
        this.setDeficiencia(deficiencia);
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getModuloAtual() {
        return moduloAtual;
    }

    public void setModuloAtual(Integer moduloAtual) {
        this.moduloAtual = moduloAtual;
    }
    
    public String getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(String anoEntrada) {
        this.anoEntrada = anoEntrada;
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the supervisor
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

}
