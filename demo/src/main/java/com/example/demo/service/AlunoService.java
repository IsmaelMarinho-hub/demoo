package com.example.demo.service;

import com.example.demo.dito.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repositoty.AlunoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepositoty alunoRepositoty;

    public List<AlunoDTO> findAllAlunos(){
        return alunoRepositoty.findAll()
                .stream()
                .map(this::toDTO)
                .toList();

    }

    public Optional<AlunoDTO> findAlunoById(Long id){
        return alunoRepositoty.findById(id)
                .map(this::toDTO);
    }

    public AlunoDTO saveAluno(Aluno aluno){
        Aluno alunoSaved = alunoRepositoty.save(aluno);
        return toDTO(alunoSaved);
    }

    public void deleteAluno(Long id){
        alunoRepositoty.deleteById(id);
    }

    public AlunoDTO updateAluno (Long id, Aluno updateAluno){
        return alunoRepositoty.findById(id)
                .map(aluno -> {
                    aluno.setNome(updateAluno.getNome());
                    aluno.setEmail(updateAluno.getEmail());
                    aluno.setDataNasc(updateAluno.getDataNasc());
                    aluno.setSenha(updateAluno.getSenha());

                    Aluno alunoSaved = alunoRepositoty.save(aluno);
                    return toDTO(alunoSaved);
                }).orElseThrow(() -> new RuntimeException("Alunno não existe!"));
    }

    private AlunoDTO toDTO(Aluno aluno){
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }

}
